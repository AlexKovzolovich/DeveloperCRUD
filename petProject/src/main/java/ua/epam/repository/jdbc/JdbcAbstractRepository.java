package ua.epam.repository.jdbc;


import ua.epam.exceptions.PersistException;
import ua.epam.exceptions.WrongArgumentPersistentException;
import ua.epam.mapper.Mapper;
import ua.epam.model.BasicEntity;
import ua.epam.repository.GenericRepository;
import ua.epam.util.ConnectionUtil;

import java.sql.*;
import java.util.List;

public abstract class JdbcAbstractRepository<T extends BasicEntity> implements GenericRepository<T, Long> {
    private Mapper<T, ResultSet, PreparedStatement> mapper;

    private final String selectAllQuery;
    private final String selectByIdQuery;
    private final String insertQuery;
    private final String deleteQuery;
    private final String updateQuery;
    private final String getLastIdQuery;

    public JdbcAbstractRepository(Class clazz, Mapper<T, ResultSet, PreparedStatement> mapper) throws PersistException {
        this.mapper = mapper;
        QueryHandler queryHandler = QueryHandlerStorage.getQueryHandler(clazz);
        this.selectAllQuery = queryHandler.getSelectAllQuery();
        this.selectByIdQuery = queryHandler.getSelectByIdQuery();
        this.insertQuery = queryHandler.getInsertQuery();
        this.deleteQuery = queryHandler.getDeleteQuery();
        this.updateQuery = queryHandler.getUpdateQuery();
        this.getLastIdQuery = queryHandler.getLastIdQuery();
    }

    @Override
    public T getById(Long id) throws PersistException {
        if (id == null) {
            throw new WrongArgumentPersistentException("id must not be null");
        }
        List<T> entities;
        T result;
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectByIdQuery)) {

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            entities = mapper.map(resultSet);
            if (entities.size() != 1) {
                throw new PersistException("Wrong number of record's received: " + entities.size());
            } else {
                result = entities.iterator().next();
            }
        } catch (SQLException e) {
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    public List<T> getAll() throws PersistException{
        List<T> result;
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectAllQuery)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            result = mapper.map(resultSet);
        } catch (SQLException e) {
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    public void save(T entity)throws PersistException{
        if (entity == null) {
            throw new WrongArgumentPersistentException("object must not be null");
        }
        if (entity.getId() != null) {
            entity.setId(null);
        }
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            mapper.map(entity, preparedStatement);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new PersistException(e);
        }

        try (Connection connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(getLastIdQuery)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Long id = resultSet.getLong(1);
            entity.setId(id);
        } catch (SQLException e) {
            throw new PersistException(e);
        }
    }

    @Override
    public void delete(T entity) throws PersistException{
        Long id = entity.getId();
        if (id == null) {
            throw new PersistException("Not persists");
        }
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {

            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new PersistException(e);
        }
    }

    @Override
    public void update(T entity) throws PersistException {
        Long id = entity.getId();
        if (id == null) {
            throw new PersistException("Not persists");
        }
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

            mapper.map(entity, preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistException(e);
        }
    }
}
