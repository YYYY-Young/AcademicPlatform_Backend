package king.controller;

import king.service.Paperservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import result.Result;
import result.ResultFactory;

/**
 * @Classname Papercontroller
 * @Description TODO
 * @Date 2020/12/19 12:29
 * @Created by lrf
 */
@RestController
public class Papercontroller {
    @Autowired
    Paperservice paperservice;
    @GetMapping("/api/paper/search/keyword/{keyword}/{num}/{size}/{order}")
    public Result getPaperbykeyword(@PathVariable("keyword")String keyword, @PathVariable("num")int num,
                                     @PathVariable("size")int size, @PathVariable("order")int order){
        return ResultFactory.buildSuccessResult(paperservice.searchpaper(keyword,num,size,order));
    }

    @GetMapping("/api/paper/search/id/{id}")
    public Result getPaperId(@PathVariable("id")String id){
        return ResultFactory.buildSuccessResult(paperservice.getbyid(id));
    }
}

