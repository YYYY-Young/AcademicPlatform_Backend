package king.controller;

import king.dao.PaperDao;
import king.entity.Paper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import result.Result;
import result.ResultFactory;

/**
 * @Classname Testcontroller
 * @Description TODO
 * @Date 2020/12/9 15:12
 * @Created by lrf
 */
@RestController
public class Testcontroller {
  @Autowired
  PaperDao paperDao;
  @GetMapping("/api/hello")
    public Result testhello(){
      return ResultFactory.buildSuccessResult("hello");
  }
  @GetMapping("/api/test2")
    public Result testcun(){
    Paper paper=new Paper();
    paper.setId(1);
    paper.setTitle("55555");
      return ResultFactory.buildSuccessResult(paperDao.save(paper));
  }
}
