package king.service;

import king.dao.AuthorApplicationDao;
import king.dao.UserDao;
import king.entity.AuthorApplication;
import king.entity.User;
import king.entity.VenueApplication;
import org.apache.tomcat.util.buf.UDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorApplicationService {
    @Autowired
    AuthorApplicationDao authorApplicationDao;
    @Autowired
    UserDao userDao;
    public AuthorApplication findByUserID(int id){
        return authorApplicationDao.findByUserid(id);
    }
    public AuthorApplication findByAuthorID(int id){
        return authorApplicationDao.findByAuthorid(id);
    }

    public int  applyAuthor(AuthorApplication authorApplication){
        java.sql.Timestamp ctime =new java.sql.Timestamp(new java.util.Date().getTime());
        authorApplication.setApptime(ctime);
        int uid=authorApplication.getUserid();
        String aid=authorApplication.getAuthorid();
        User u=userDao.findById(uid);
        if(u==null){
            return 1;
        }
        AuthorApplication a=authorApplicationDao.findByAuthoridAndStateIsTrue(aid);
        AuthorApplication b=authorApplicationDao.findByUseridAndStateIsTrue(uid);
        if(a!=null){
            return 2;
        }
        if(b!=null){
            return 3;
        }
        authorApplication.setState(true);
        authorApplicationDao.save(authorApplication);
        return 4;
    }

    public AuthorApplication getMyAuthor(int uid){
        return authorApplicationDao.findByUseridAndStateIsTrue(uid);
    }
}
