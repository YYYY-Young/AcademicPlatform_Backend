package king.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import king.entity.AuthorApplication;
import king.entity.VenueApplication;
import king.service.AuthorApplicationService;
import king.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import result.Result;
import result.ResultFactory;

@RestController
public class AuthorController {
    @Autowired
    AuthorApplicationService authorApplicationService;
    @Autowired
    SearchService searchService;
    @PostMapping("/api/author/apply")
    public Result applyAuthor(@RequestBody AuthorApplication authorApplication){
        int  v=authorApplicationService.applyAuthor(authorApplication);
        if(v==1){
            return ResultFactory.buildFailResult("用户不存在");
        }else if(v==2){
            return ResultFactory.buildFailResult("该人员已经认证");
        }else if(v==3){
            return ResultFactory.buildFailResult("该用户已经认证");
        }
        else{
            return ResultFactory.buildSuccessResult("认证成功");
        }
    }

    @GetMapping("/api/author/get/{uid}")
    public Result showauthor(@PathVariable("uid") int uid) throws JsonProcessingException {
        AuthorApplication v=authorApplicationService.getMyAuthor(uid);
        String aid=v.getAuthorid();
        Result result=searchService.getAuthorbyId(aid);
        Object res=result.getResult();
        ObjectMapper mapper =new ObjectMapper();
        String jsonstr="";
        jsonstr=mapper.writeValueAsString(res);
        JSONObject l=JSONObject.parseObject(jsonstr);
        Result a=ResultFactory.buildSuccessResult(l);
        return a;
    }

//    @GetMapping("/api/expernetwork/{aid}")
//    public Result expertnetwork(@PathVariable("aid") String aid) throws JsonProcessingException {
//        Result mainexper =searchService.getAuthorbyId(aid);
//        Object res = mainexper.getResult();
//        ObjectMapper mapper =new ObjectMapper();
//        String jsonstr="";
//        jsonstr=mapper.writeValueAsString(res);
//        JSONObject l=JSONObject.parseObject(jsonstr);
//        JSONArray js= JSON.parseArray(l.get("pubs").toString());
//        ObjectMapper mapper2 =new ObjectMapper();
//        String jsonstr2="";
//        jsonstr2=mapper2.writeValueAsString(js);
//        JSONArray array=JSONArray.parseArray(jsonstr2);
//        for(int i=0;i<js.size();++i){
//            JSONObject json= (JSONObject) array.get(i);
//            String pid =json.get("i").toString();
//
//        }
//
//    }
}
