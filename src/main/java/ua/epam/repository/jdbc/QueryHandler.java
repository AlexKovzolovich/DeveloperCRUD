package ua.epam.repository.jdbc;

public interface QueryHandler {
    String getSelectAllQuery();
    String getSelectByIdQuery();
    String getInsertQuery();
    String getDeleteQuery();
    String getUpdateQuery();
}
