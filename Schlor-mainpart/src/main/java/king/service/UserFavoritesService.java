package king.service;

import king.dao.UserDao;
import king.dao.UserFavoritesDao;
import king.entity.User;
import king.entity.UserAttention;
import king.entity.UserFavorites;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import result.Result;

import java.util.List;

@Service
public class UserFavoritesService {
    @Autowired
    UserFavoritesDao userFavoritesDao;
    @Autowired
    UserDao userDao;
    @Autowired
    SearchService searchService;

    public List<UserFavorites> listmyfavorites(int uid){
        return userFavoritesDao.findAllByUserid(uid);
    }

    public int collect(UserFavorites userFavorites){
        int uid=userFavorites.getUserid();
        String pid=userFavorites.getPaperid();
        User u=userDao.findById(uid);
        if(u==null){
            return 1;
        }
        UserFavorites a=userFavoritesDao.findByUseridAndPaperid(uid,pid);
        if(a!=null){
            return 2;
        }
        Result result=searchService.getPaperbyId(pid);
        if(result.getResult()==null){
            return 3;
        }
        userFavoritesDao.save(userFavorites);
        return 4;
    }

    public int Unfavorite(UserFavorites userFavorites){
        int uid=userFavorites.getUserid();
        String pid=userFavorites.getPaperid();
        UserFavorites a=userFavoritesDao.findByUseridAndPaperid(uid,pid);
        if(a==null){
            return 0;
        }
        userFavoritesDao.delete(a);
        return 1;
    }
}
