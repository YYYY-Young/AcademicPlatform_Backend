package king.service;

import king.dao.RepresentationDao;
import king.entity.Representation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.net.RegisteredDomain;

import java.util.List;

@Service
public class RepresentationService {
    @Autowired
    RepresentationDao representationDao;

    public List<Representation> findAllByUserid(int id){
        return representationDao.findAllByUserid(id);
    }
    public List<Representation> findAllByStateIsFalse(){
        return representationDao.findAllByStateIsFalse();
    }
    public List<Representation> findAllByStateIsTrue(){
        return representationDao.findAllByStateIsTrue();
    }
    public List<Representation> findAllByReviewerid(int id){
        return representationDao.findAllByReviewerid(id);
    }
    public List<Representation> findAllByUseridAndStateIsFalse(int id){
        return representationDao.findAllByUseridAndStateIsFalse(id);
    }
    public List<Representation> findAllByUseridAndStateIsTrue(int id){
        return representationDao.findAllByUseridAndStateIsTrue(id);
    }
    public void addRepresentation(Representation representation){
        java.sql.Timestamp ctime =new java.sql.Timestamp(new java.util.Date().getTime());
        representation.setTime(ctime);
        representationDao.save(representation);
    }
    public boolean replyRepresentation(Representation representation){
        Representation r=representationDao.findById(representation.getId());
        if(r==null){
            return false;
        }
        r.setState(true);
        java.sql.Timestamp ctime =new java.sql.Timestamp(new java.util.Date().getTime());
        r.setTime(ctime);
        representationDao.save(r);
        return true;
    }
    public int delete(int id,int uid){
        Representation representation=representationDao.findById(id);
        if(uid!=representation.getUserid()&&uid!=representation.getReviewerid()){
            return 0;
        }
        representationDao.deleteById(id);
        return 1;
    }
    public List<Representation> findAll(){
        return representationDao.findAll();
    }
}
