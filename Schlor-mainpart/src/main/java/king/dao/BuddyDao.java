package king.dao;

import king.entity.Buddy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuddyDao extends JpaRepository<Buddy,Integer> {
    Buddy findById(int id);
}
