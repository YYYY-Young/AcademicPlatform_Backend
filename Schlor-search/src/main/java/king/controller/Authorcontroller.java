package king.controller;

import king.service.Authorservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import result.*;

/**
 * @Classname Authorcontroller
 * @Description TODO
 * @Date 2020/12/16 10:01
 * @Created by lrf
 */
@RestController
public class Authorcontroller {
    @Autowired
    Authorservice authorservice;
    @GetMapping("/api/author/search/id/{id}")
    public Result getAuthorbyId(@PathVariable("id")String id){
        return ResultFactory.buildSuccessResult(authorservice.findbyid(id));
    }

    @GetMapping("/api/author/search/keyword/{keyword}/{num}/{size}/{order}")
    public Result getAuthorbykeyword(@PathVariable("keyword")String keyword,@PathVariable("num")int num,
                                     @PathVariable("size")int size,@PathVariable("order")int order){
        return ResultFactory.buildSuccessResult(authorservice.searchauthor(keyword,num,size,order));
    }
    @GetMapping("/api/author/search/name/{name}/{num}/{size}")
    public Result getAuthorbyname(@PathVariable("name")String name,@PathVariable("num")int num,
                                  @PathVariable("size")int size){
        return ResultFactory.buildSuccessResult(authorservice.findbyname(name,num,size));
    }
    @GetMapping("/api/author/search/tags/{name}/{num}/{size}")
    public Result getAuthorbytags(@PathVariable("name")String name,@PathVariable("num")int num,
                                  @PathVariable("size")int size){
        return ResultFactory.buildSuccessResult(authorservice.findbytags(name,num,size));
    }

}
