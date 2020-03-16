package ua.epam.resthomework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.epam.resthomework.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  User findUserByFirstNameAndLastName(String firstName, String lastName);
}
