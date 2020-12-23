package king.service;

import king.dao.UserDao;
import king.dao.VenueApplicationDao;
import king.entity.AuthorApplication;
import king.entity.User;
import king.entity.VenueApplication;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import result.Result;

import java.util.List;

@Service
public class VenueApplicationService {
    @Autowired
    VenueApplicationDao venueApplicationDao;
    @Autowired
    UserDao userDao;
    public int  applyVenue(VenueApplication venueApplication){
        java.sql.Timestamp ctime =new java.sql.Timestamp(new java.util.Date().getTime());
        venueApplication.setApptime(ctime);
        int uid=venueApplication.getUserid();
        String vid=venueApplication.getVenueid();
        User u=userDao.findById(uid);
        if(u==null){
            return 1;
        }
        VenueApplication a=venueApplicationDao.findByVenueidAndStateIsTrue(vid);
        VenueApplication b=venueApplicationDao.findByUseridAndStateIsTrue(uid);
        if(a!=null){
            return 2;
        }
        if(b!=null){
            return 3;
        }
        venueApplication.setState(true);
        venueApplicationDao.save(venueApplication);
        return 4;
    }

    public VenueApplication getMyVenue(int uid){
        return venueApplicationDao.findByUseridAndStateIsTrue(uid);
    }

}
