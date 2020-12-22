package king.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import king.entity.AuthorApplication;
import king.entity.Expertnetwork;
import king.entity.VenueApplication;
import king.service.AuthorApplicationService;
import king.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import result.Result;
import result.ResultFactory;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/api/expernetwork/{aid}")
    public Result expertnetwork(@PathVariable("aid") String aid) throws JsonProcessingException {
        List<Expertnetwork> eslist=new ArrayList<Expertnetwork>();
        int flag;
        Result mainexper =searchService.getAuthorbyId(aid);
        Object res = mainexper.getResult();
        if(res==null){
            return ResultFactory.buildFailResult("没有合作者！");
        }
        ObjectMapper mapper =new ObjectMapper();
        String jsonstr="";
        jsonstr=mapper.writeValueAsString(res);
        JSONObject l=JSONObject.parseObject(jsonstr);
        if(l.get("pubs")==null){
            return ResultFactory.buildFailResult("没有合作者！");
        }
        JSONArray js= JSON.parseArray(l.get("pubs").toString());
        ObjectMapper mapper2 =new ObjectMapper();
        String jsonstr2="";
        jsonstr2=mapper2.writeValueAsString(js);
        JSONArray array=JSONArray.parseArray(jsonstr2);
        for(int i=0;i<array.size();++i){
            JSONObject json= (JSONObject) array.get(i);
            String pid =json.get("i").toString();
            Result paper =searchService.getPaperbyId(pid);
            Object paperobj =paper.getResult();
            if(paperobj==null){
                continue;
            }else{
                ObjectMapper mapper3 =new ObjectMapper();
                String jsonstr3="";
                jsonstr3=mapper3.writeValueAsString(paperobj);
                JSONObject p=JSONObject.parseObject(jsonstr3);
                if(p.get("authors")!=null){
                    JSONArray pl=JSONArray.parseArray(p.get("authors").toString());
                    for(int j=0;j<pl.size();j++){
                        JSONObject jsonObject=(JSONObject) pl.get(j);
                        if(jsonObject.get("id")!=null){
                            String auther=jsonObject.get("id").toString();
                            if(auther.equals(aid)) continue;
                            flag=0;
                            for(int z=0;z<eslist.size();z++){
                                if(eslist.get(z).getId().equals(auther)){
                                    eslist.get(z).setNum(eslist.get(z).getNum()+1);
                                    flag=1;
                                }
                            }
                            if(flag==0){
                                Result aresult=searchService.getAuthorbyId(auther);
                                if(aresult.getResult()!=null){
                                    Expertnetwork a=new Expertnetwork();
                                    a.setId(auther);
                                    a.setNum(1);
                                    eslist.add(a);
                                }
                            }
                        }
                    }
                }
            }
        }
        if(eslist.size()==0){
            return ResultFactory.buildFailResult("没有合作者！");
        }else return ResultFactory.buildSuccessResult(eslist);
    }

    @GetMapping("/api/hotpots")
    public Result hotspots(){
        List<String> s=new ArrayList<String>();
        s.add("big data");
        s.add("artificial intelligence");
        return ResultFactory.buildSuccessResult(s);
    }
}
