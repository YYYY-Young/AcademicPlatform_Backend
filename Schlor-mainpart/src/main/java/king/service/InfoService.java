package king.service;

import king.dao.InfoDao;
import king.dao.UserDao;
import king.entity.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class InfoService {
    @Autowired
    InfoDao infoDao;

    @Autowired
    UserDao userDao;
    public boolean sendInfo(Info info){
        java.sql.Timestamp ctime =new java.sql.Timestamp(new java.util.Date().getTime());
        info.setTime(ctime);
        info.setSendername(userDao.findById(info.getSenderid()).getName());
        info.setRecipientname(userDao.findById(info.getRecipientid()).getName());
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
    public List<Info> getABInfo(int sid,int rid){
        List<Info> ans=infoDao.findAllByRecipientidAndSenderid(rid,sid);
        return ans;
    }

    public List<Info> getAandBInfo(int sid,int rid){
        List<Info> ans1=infoDao.findAllByRecipientidAndSenderid(rid,sid);
        List<Info> ans2=infoDao.findAllByRecipientidAndSenderid(sid,rid);
        List<Info> infos=new ArrayList<Info>();
        infos.addAll(ans1);
        infos.addAll(ans2);
        Collections.sort(infos, new Comparator<Info>() {
            @Override
            public int compare(Info o1, Info o2) {
                return Integer.valueOf(o1.getId()).compareTo(Integer.valueOf(o2.getId()));
            }
        });
        return infos;
    }
    public boolean deleteInfo(int id){
        Info info=infoDao.findById(id);
        if(info==null){
            return false;
        }
        infoDao.delete(info);
        return true;
    }


}
