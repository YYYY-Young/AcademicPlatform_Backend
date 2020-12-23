package king.service;

import king.entity.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfoService {
    @Autowired
    InfoService infoService;

    public List<Info> findAllBySenderid(int sid){
        return infoService.findAllBySenderid(sid);
    }
    public List<Info> findAllByRecipientid(int rid){
        return infoService.findAllByRecipientid(rid);
    }
    public List<Info> findAllByRecipientidOrSenderid(int rid,int sid){
        return infoService.findAllByRecipientidOrSenderid(rid,sid);
    }


}
