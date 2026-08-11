package io.herald.SpringWeb.Repository;

import io.herald.SpringWeb.Model.UserTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserTable, Integer>
{
    boolean existsByUsernameAndPassword(String un, String pwd);
    // existsBy function can be found already in our repo, but username and password can't be detected directly by existsBy function
    // Hence if our user table has columns named "username" and "password"
    // We can suggest our repository to look for it, if the value exists or not

    //Custom Syntaxes Signature

    UserTable findByUsername(String username);

}
