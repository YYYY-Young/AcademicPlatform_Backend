package king.service;

import king.dao.BuddyDao;
import king.dao.UserDao;
import king.entity.Buddy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuddyService {
    @Autowired
    BuddyDao buddyDao;
    @Autowired
    UserDao userDao;
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
            String user1name=userDao.findById(buddy.getUser1id()).getName();
            String user2name=userDao.findById(buddy.getUser2id()).getName();
            buddy1.setTime(ctime);
            buddy1.setUser1id(buddy.getUser1id());
            buddy1.setUser2id(buddy.getUser2id());
            buddy1.setUser1name(user1name);
            buddy1.setUser2name(user2name);
            buddyDao.save(buddy1);
            Buddy buddy2 = new Buddy();
            buddy2.setTime(ctime);
            buddy2.setUser1id(buddy.getUser2id());
            buddy2.setUser2name(user1name);
            buddy2.setUser2id(buddy.getUser1id());
            buddy2.setUser1name(user2name);
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
