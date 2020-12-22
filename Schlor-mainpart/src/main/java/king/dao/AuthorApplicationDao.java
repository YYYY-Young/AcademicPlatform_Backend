package king.dao;

import king.entity.Admin;
import king.entity.AuthorApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorApplicationDao extends JpaRepository<AuthorApplication,Integer> {
    AuthorApplication findById(int id);
    AuthorApplication findByUserid(int id);
    AuthorApplication findByAuthorid(int id);
    AuthorApplication findByAuthoridAndStateIsTrue(String uid);
    AuthorApplication findByUseridAndStateIsTrue(int uid);
}