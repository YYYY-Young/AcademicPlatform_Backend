package king.controller;

import king.entity.Venue;
import king.service.Venueservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import result.Result;
import result.ResultFactory;

/**
 * @Classname Venuecontroller
 * @Description TODO
 * @Date 2020/12/19 12:32
 * @Created by lrf
 */
@RestController
public class Venuecontroller {
    @Autowired
    Venueservice venueservice;
    @GetMapping("/api/venue/search/id/{id}")
    public Result getVenuerbyId(@PathVariable("id")String id){
        return ResultFactory.buildSuccessResult(venueservice.findbyid(id));
    }

    @GetMapping("/api/venue/search/keyword/{keyword}/{num}/{size}")
    public Result getVenuebykeyword(@PathVariable("keyword")String keyword,@PathVariable("num")int num,
                                     @PathVariable("size")int size){
        return ResultFactory.buildSuccessResult(venueservice.searchvenue(keyword,num,size));
    }

}
