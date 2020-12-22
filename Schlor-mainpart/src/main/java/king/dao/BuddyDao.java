package king.dao;

import king.entity.Buddy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuddyDao extends JpaRepository<Buddy,Integer> {
    Buddy findById(int id);
    List<Buddy> findAllByUser1id(int user1id);
    Buddy findByUser1idAndUser2id(int user1id,int user2id);
}
