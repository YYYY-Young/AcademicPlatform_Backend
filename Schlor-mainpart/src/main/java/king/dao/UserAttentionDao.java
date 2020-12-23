package king.dao;

import king.entity.UserAttention;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAttentionDao extends JpaRepository<UserAttention,Integer> {
    UserAttention findById(int id);
    List<UserAttention> findAllByUserid(int uid);
    List<UserAttention> findAllByAuthorid(String aid);
    UserAttention findByUseridAndAuthorid(int uid, String aid);
}
