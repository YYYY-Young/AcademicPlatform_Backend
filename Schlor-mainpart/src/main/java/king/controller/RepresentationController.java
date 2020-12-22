package king.controller;

import king.entity.Representation;
import king.entity.User;
import king.service.RepresentationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import result.Result;
import result.ResultFactory;

import javax.validation.Valid;
import java.util.List;

@RestController
public class RepresentationController {
    @Autowired
    RepresentationService representationService;
    @GetMapping("/api/representation/get/{uid}")
    public Result getRepbyuser(@PathVariable("uid") int uid) {
        List<Representation> res=representationService.findAllByUserid(uid);
        return ResultFactory.buildSuccessResult(res);
    }

    @GetMapping("/api/representation/get/{uid}/{states}")
    public Result getRepbyStatesAndUser(@PathVariable("uid") int uid,@PathVariable("states") boolean states){
        if(states==true){
            return ResultFactory.buildSuccessResult(representationService.findAllByUseridAndStateIsTrue(uid));
        }else if(states==false){
            return ResultFactory.buildSuccessResult(representationService.findAllByUseridAndStateIsFalse(uid));
        }
        return ResultFactory.buildFailResult("查询错误");
    }

    @GetMapping("/api/representation/admin/get/{states}")
    public Result getRepbyStates(@PathVariable("states") boolean states){
        if(states==true){
            return ResultFactory.buildSuccessResult(representationService.findAllByStateIsTrue());
        }else{
            return ResultFactory.buildSuccessResult(representationService.findAllByStateIsFalse());
        }
    }

    @GetMapping("/api/representation/admin/{rid}")
    public Result getRepbyReviewer(@PathVariable("rid") int rid){
        return ResultFactory.buildSuccessResult(representationService.findAllByReviewerid(rid));
    }

    @PostMapping("/api/representation/add")
    public Result addRep(@RequestBody @Valid Representation representation){
        representationService.addRepresentation(representation);
        return ResultFactory.buildSuccessResult("添加成功!");
    }
    @PostMapping("/api/representation/reply")
    public Result replyRep(@RequestBody @Valid Representation representation){
        if(representationService.replyRepresentation(representation)){
            return ResultFactory.buildSuccessResult("申述回复完成!");
        }
        return ResultFactory.buildFailResult("审批申述失败!");
    }

    @DeleteMapping("/api/representation/delete/{id}/{uid}")
    public Result delete(@PathVariable("id") int id,@PathVariable("uid") int uid){
        int res=representationService.delete(id,uid);
        if(res==0){
            return ResultFactory.buildSuccessResult("没有删除权限！");
        }else{
            return ResultFactory.buildSuccessResult("删除成功！");
        }
    }

    @GetMapping("/api/representation/getall")
    public Result getAll(){
        return ResultFactory.buildSuccessResult(representationService.findAll());
    }
}
