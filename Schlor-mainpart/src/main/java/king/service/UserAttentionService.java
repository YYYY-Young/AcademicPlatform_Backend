package king.service;

import king.dao.UserAttentionDao;
import king.dao.UserDao;
import king.entity.AuthorApplication;
import king.entity.User;
import king.entity.UserAttention;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import result.Result;

import java.util.List;

@Service
public class UserAttentionService {
    @Autowired
    UserAttentionDao userAttentionDao;

    @Autowired
    UserDao userDao;
    @Autowired
    SearchService searchService;
    public List<UserAttention> listmyattention(int uid){
        return userAttentionDao.findAllByUserid(uid);
    }

    public int addattention(UserAttention userAttention){
        int uid=userAttention.getUserid();
        String aid=userAttention.getAuthorid();
        User u=userDao.findById(uid);
        if(u==null){
            return 1;
        }
        UserAttention a=userAttentionDao.findByUseridAndAuthorid(uid,aid);
        if(a!=null){
            return 2;
        }
        Result result=searchService.getAuthorbyId(aid);
        if(result.getResult()==null){
            return 3;
        }
        userAttentionDao.save(userAttention);
        return 4;
    }

    public int cacelattention(UserAttention userAttention){
        int uid=userAttention.getUserid();
        String aid=userAttention.getAuthorid();
        UserAttention a=userAttentionDao.findByUseridAndAuthorid(uid,aid);
        if(a==null){
            return 0;
        }
        userAttentionDao.delete(a);
        return 1;
    }
}
