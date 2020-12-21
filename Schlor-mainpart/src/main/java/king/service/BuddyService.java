package king.service;

import king.dao.BuddyDao;
import king.entity.Buddy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuddyService {
    @Autowired
    BuddyDao buddyDao;

    public List<Buddy> findByUser1id(int user1id){
        return buddyDao.findAllByUser1id(user1id);
    }

    public boolean addBuddy(Buddy buddy){
        if (buddyDao.findByUser1idAndUser2id(buddy.getUser1id(), buddy.getUser2id())!=null){
            return false;
        }
        else {
            java.sql.Timestamp ctime =new java.sql.Timestamp(new java.util.Date().getTime());
            Buddy buddy1 = new Buddy();
            buddy1.setTime(ctime);
            buddy1.setUser1id(buddy.getUser1id());
            buddy1.setUser2id(buddy.getUser2id());
            buddyDao.save(buddy1);
            Buddy buddy2 = new Buddy();
            buddy2.setTime(ctime);
            buddy2.setUser1id(buddy.getUser2id());
            buddy2.setUser2id(buddy.getUser1id());
            buddyDao.save(buddy2);
            return true;
        }
    }

    public Boolean deleteBuddy(Buddy buddy){
        if (buddyDao.findByUser1idAndUser2id(buddy.getUser1id(), buddy.getUser2id())==null){
            return false;
        }
        else{
            buddyDao.delete(buddyDao.findByUser1idAndUser2id(buddy.getUser1id(),buddy.getUser2id()));
            buddyDao.delete(buddyDao.findByUser1idAndUser2id(buddy.getUser2id(), buddy.getUser1id()));
            return true;
        }
    }

    public List<Buddy> checkBuddy(int user1id){
        List<Buddy> buddyList = buddyDao.findAllByUser1id(user1id);
        return buddyList;
    }
}
