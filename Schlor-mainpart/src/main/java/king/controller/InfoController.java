package king.controller;

import king.entity.Info;
import king.entity.User;
import king.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import result.Result;
import result.ResultFactory;

import java.util.List;

@RestController
public class InfoController {
    @Autowired
    InfoService infoService;

    @PostMapping("api/info/send")
    public Result sendInfo(@RequestBody Info info){
        Boolean temp = infoService.sendInfo(info);
        if (temp){
            return ResultFactory.buildSuccessResult("发送成功");
        }
        else{
            return ResultFactory.buildFailResult("发送失败");
        }
    }

    @PostMapping("api/info/sinfo")
    public Result check_sender_info(@RequestBody User user) {
        List<Info> temp = infoService.getSenderInfo(user.getId());
        return ResultFactory.buildSuccessResult(temp);
    }


    @PostMapping("api/info/rinfo")
    public Result check_r_info(@RequestBody User user) {
        List<Info> temp = infoService.getRInfo(user.getId());
        return ResultFactory.buildSuccessResult(temp);
    }

    @PostMapping("api/info/allinfo")
    public Result check_all_info(@RequestBody User user ) {
        List<Info> temp = infoService.getallInfo(user.getId());
        return ResultFactory.buildSuccessResult(temp);
    }

        @PostMapping("api/info/delete")
    public Result deleteinfo(@RequestBody Info info) {
        boolean temp = infoService.deleteInfo(info);
        if( temp ){
            return  ResultFactory.buildSuccessResult("删除成功");
        }
        return  ResultFactory.buildFailResult("删除失败");
    }


}
