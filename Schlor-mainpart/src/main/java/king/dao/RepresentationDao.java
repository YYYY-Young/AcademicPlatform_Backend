package king.dao;

import king.entity.Representation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepresentationDao extends JpaRepository<Representation,Integer> {
    Representation findById(int id);
    List<Representation> findAllByUserid(int id);
    List<Representation> findAllByStateIsFalse();
    List<Representation> findAllByStateIsTrue();
    List<Representation> findAllByReviewerid(int id);
    List<Representation> findAllByUseridAndStateIsFalse(int id);
    List<Representation> findAllByUseridAndStateIsTrue(int id);
    void deleteById(int id);
    List<Representation> findAll();
}
