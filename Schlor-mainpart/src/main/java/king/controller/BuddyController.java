package king.controller;

import king.entity.Buddy;
import king.service.BuddyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import result.Result;
import result.ResultFactory;

import java.util.List;


@RestController
public class BuddyController {
    @Autowired
    BuddyService buddyService;
    
    @PostMapping("api/buddy/add")
    public Result addbuddy(@RequestBody Buddy buddy) {
        boolean temp = buddyService.addBuddy(buddy);
        if (temp) {
            return ResultFactory.buildSuccessResult("添加成功");
        } else {
            return ResultFactory.buildFailResult("好友已存在");
        }
    }

    @PostMapping("api/buddy/delete")
    public Result deletebuddy(@RequestBody Buddy buddy){
        boolean temp = buddyService.deleteBuddy(buddy);
        if (temp) {
            return ResultFactory.buildSuccessResult("删除成功");
        } else {
            return ResultFactory.buildFailResult("不存在关系");
        }
    }

    @PostMapping("api/buddy/check")
    public Result checkbuddy(@RequestBody Buddy buddy){
        List<Buddy> temp = buddyService.checkBuddy(buddy.getUser1id());
        if (temp !=null){
            return ResultFactory.buildSuccessResult(temp);
        }
       return ResultFactory.buildFailResult("不存在关系");
    }
}
