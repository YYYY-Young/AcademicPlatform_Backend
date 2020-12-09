package king.service;

import com.alibaba.fastjson.JSONObject;
import king.dao.PaperDao;
import king.entity.Paper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Classname PaperService
 * @Description TODO
 * @Date 2020/12/9 15:44
 * @Created by lrf
 */
@Service
public class PaperService {
    @Autowired
    PaperDao paperDao;
    public void save(){
        Paper paper=new Paper();
        paper.setId(1);
        paper.setTitle("77777");
        paperDao.save(paper);
    }
}
