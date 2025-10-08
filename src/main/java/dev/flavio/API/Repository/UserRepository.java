package dev.flavio.API.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import dev.flavio.API.Entity.User;


@Repository
public interface UserRepository extends JpaRepository <User, Long> {

   
   Optional <UserDetails> findUserByEmail (String username);
   // UserDetails findby

}
