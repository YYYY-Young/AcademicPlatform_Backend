package king.service;

import king.dao.InfoDao;
import king.entity.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Service
public class InfoService {

    @Autowired
    InfoDao infoDao;

    public boolean sendInfo(Info info){
        java.sql.Timestamp ctime =new java.sql.Timestamp(new java.util.Date().getTime());
        info.setTime(ctime);
        infoDao.save(info);
        return  true;
    }

    public List<Info> getSenderInfo(int userid){
        List<Info> ans = infoDao.findAllBySenderid(userid);
        return ans;
    }

    public List<Info> getRInfo(int userid){
        List<Info> ans = infoDao.findAllByRecipientid(userid);
        return  ans;
    }
    public List<Info> getallInfo(int uid){
        List<Info> ans=infoDao.findAllByRecipientidOrSenderid(uid,uid);
        return ans;
    }
    public boolean deleteInfo(Info info){
        if(infoDao.findById(info.getId())==null){
            return false;
        }
        infoDao.delete(info);
        return true;
    }

}
