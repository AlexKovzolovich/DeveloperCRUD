package ua.epam.resthomework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.epam.resthomework.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByFirstNameAndLastName(String firstName, String lastName);

    @Modifying
    @Query(value = "UPDATE user SET first_name = :firstName, lastName = :lastName, email = :email WHERE id = :id")
    int update(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("email") String email, @Param("id") Long id);
}
