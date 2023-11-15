package be.ehb.petstore.repository;
import be.ehb.petstore.model.Address;
import be.ehb.petstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.username = :username")
    public User getUserByUsername(@Param("username") String username);

    @Query("SELECT u FROM User u WHERE u.googleNameId = :googleNameId AND u.emailAddress = :email")
    public User getUserByGoogleNameIdAndEmailAddress(@Param("googleNameId") String googleNameId, @Param("email") String email);

    @Query("SELECT u FROM User u WHERE u.googleNameId = :googleNameId")
    Optional<User> getUserByGoogleNameId(@Param("googleNameId") String googleNameId);
}