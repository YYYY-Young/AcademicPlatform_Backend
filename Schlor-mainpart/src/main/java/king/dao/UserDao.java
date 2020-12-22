package king.dao;

import king.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  UserDao extends JpaRepository<User,Integer> {
    User findById(int id);
    User findByName(String username);
    User findByNameAndPassword(String username,String password);
    void deleteById(int id);
}
