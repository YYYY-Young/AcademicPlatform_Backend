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

    @GetMapping("api/info/sinfo/{sid}")
    public Result check_sender_info(@PathVariable("sid") int sid) {
        List<Info> temp = infoService.getSenderInfo(sid);
        return ResultFactory.buildSuccessResult(temp);
    }


    @GetMapping("api/info/rinfo/{rid}")
    public Result check_r_info(@PathVariable("rid") int rid) {
        List<Info> temp = infoService.getRInfo(rid);
        return ResultFactory.buildSuccessResult(temp);
    }

    @GetMapping("api/info/allinfo/{uid}")
    public Result check_all_info(@PathVariable("uid") int uid) {
        List<Info> temp = infoService.getallInfo(uid);
        return ResultFactory.buildSuccessResult(temp);
    }

    @GetMapping("api/info/sendinfo/{sid}/{rid}")
    public Result check_AB_info(@PathVariable("sid") int sid,@PathVariable("rid") int rid) {
        List<Info> temp = infoService.getABInfo(sid,rid);
        return ResultFactory.buildSuccessResult(temp);
    }
    @GetMapping("api/info/all/{sid}/{rid}")
    public Result check_allAB_info(@PathVariable("sid") int sid,@PathVariable("rid") int rid) {
        List<Info> temp = infoService.getAandBInfo(sid,rid);
        return ResultFactory.buildSuccessResult(temp);
    }
    @PostMapping("api/info/delete")
    public Result deleteinfo(@RequestBody Info info) {
        boolean temp = infoService.deleteInfo(info.getId());
        if( temp ){
            return  ResultFactory.buildSuccessResult("删除成功");
        }
        return  ResultFactory.buildFailResult("该消息不存在");
    }
}
