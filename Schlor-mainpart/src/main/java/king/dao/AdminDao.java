package king.dao;

import king.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminDao extends JpaRepository<Admin,Integer> {
    Admin findById(int id);
    Admin findByName(String name);
}
