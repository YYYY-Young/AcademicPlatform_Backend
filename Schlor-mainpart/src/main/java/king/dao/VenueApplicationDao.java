package king.dao;

import king.entity.VenueApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VenueApplicationDao extends JpaRepository<VenueApplication,Integer> {
    VenueApplication findById(int id);
    List<VenueApplication> findAllByVenueid(int id);
    List<VenueApplication> findAllByVenueidAndUserid(int vid,int uid);
    VenueApplication findByUseridAndStateIsTrue(int uid);
    VenueApplication findByVenueidAndStateIsTrue(String vid);
}
