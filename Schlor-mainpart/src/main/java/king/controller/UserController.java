package king.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import king.entity.User;
import king.entity.UserAttention;
import king.entity.UserFavorites;
import king.entity.VenueApplication;
import king.service.SearchService;
import king.service.UserAttentionService;
import king.service.UserFavoritesService;
import king.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import result.Result;
import result.ResultFactory;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    UserAttentionService userAttentionService;
    @Autowired
    UserFavoritesService userFavoritesService;
    @Autowired
    SearchService searchService;
    @PutMapping("/api/user/password")
    public Result resetPassword(@RequestBody @Valid User user) {
        User a=userService.resetPassword(user);
        if(a==null){
            return ResultFactory.buildFailResult("修改前后密码不能一样");
        }
        return ResultFactory.buildSuccessResult("重置密码成功");
    }

    @PutMapping("/api/user")
    public Result editUser(@RequestBody User requestUser) {
        return ResultFactory.buildSuccessResult(userService.editUser(requestUser));
    }
    @GetMapping("/api/getuser/{username}")
    public Result getuser(@PathVariable("username") String username){
        User u=userService.findByUsername(username);
        if(u==null){
            return ResultFactory.buildFailResult("没有这个用户");
        }
        return ResultFactory.buildSuccessResult(u);
    }

    @PostMapping("/api/user/attention/add")
    public Result addattention(@RequestBody UserAttention userAttention){
        int r=userAttentionService.addattention(userAttention);
        if(r==1){
            return ResultFactory.buildFailResult("用户不存在！");
        }else if(r==2){
            return ResultFactory.buildFailResult("您已经关注该人员！");
        }else if(r==3){
            return ResultFactory.buildFailResult("该人员不存在！");
        }else return ResultFactory.buildSuccessResult("关注成功");
    }
    @GetMapping("/api/user/attention/get/{uid}")
    public Result listattention(@PathVariable("uid") int uid) throws JsonProcessingException {
        List<UserAttention> userAttentions=userAttentionService.listmyattention(uid);
        List<Object> returns=new ArrayList<>();
        for(UserAttention userAttention:userAttentions) {
            String aid = userAttention.getAuthorid();
            Result result = searchService.getAuthorbyId(aid);
            Object res = result.getResult();
            ObjectMapper mapper =new ObjectMapper();
            String jsonstr="";
            jsonstr=mapper.writeValueAsString(res);
            JSONObject l=JSONObject.parseObject(jsonstr);
            returns.add(l);
        }
        return ResultFactory.buildSuccessResult(returns);
    }
    @PostMapping("/api/user/attention/delete")
    public Result cacelattention(@RequestBody UserAttention userAttention){
        int r=userAttentionService.cacelattention(userAttention);
        if(r==0){
            return ResultFactory.buildFailResult("你还没有关注该作者");
        }else {
            return ResultFactory.buildSuccessResult("已经取消关注");
        }
    }

    @PostMapping("/api/user/favorites/add")
    public Result collect(@RequestBody UserFavorites userFavorites){
        int r=userFavoritesService.collect(userFavorites);
        if(r==1){
            return ResultFactory.buildFailResult("用户不存在！");
        }else if(r==2){
            return ResultFactory.buildFailResult("您已经收藏该文章！");
        }else if(r==3){
            return ResultFactory.buildFailResult("该文章不存在！");
        }else return ResultFactory.buildSuccessResult("收藏成功");
    }
    @GetMapping("/api/user/favorites/get/{uid}")
    public Result listfavorites(@PathVariable("uid") int uid) throws JsonProcessingException {
        List<UserFavorites> userFavorites=userFavoritesService.listmyfavorites(uid);
        List<Object> returns=new ArrayList<>();
        for(UserFavorites userFavorite:userFavorites) {
            String pid = userFavorite.getPaperid();
            Result result = searchService.getPaperbyId(pid);
            Object res = result.getResult();
            ObjectMapper mapper =new ObjectMapper();
            String jsonstr="";
            jsonstr=mapper.writeValueAsString(res);
            JSONObject l=JSONObject.parseObject(jsonstr);
            returns.add(l);
        }
        return ResultFactory.buildSuccessResult(returns);
    }
    @PostMapping("/api/user/favorites/delete")
    public Result Unfavorite(@RequestBody UserFavorites userFavorites){
        int r=userFavoritesService.Unfavorite(userFavorites);
        if(r==0){
            return ResultFactory.buildFailResult("你还没有收藏该文章");
        }else {
            return ResultFactory.buildSuccessResult("已经取消收藏");
        }
    }
}
