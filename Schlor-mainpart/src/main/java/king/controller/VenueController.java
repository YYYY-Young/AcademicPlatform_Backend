package king.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import king.entity.VenueApplication;
import king.service.SearchService;
import king.service.VenueApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import result.Result;
import result.ResultFactory;

@RestController
public class VenueController {
    @Autowired
    VenueApplicationService venueApplicationService;
    @Autowired
    SearchService searchService;
    @PostMapping("/api/venue/apply")
    public Result applyVenue(@RequestBody VenueApplication venueApplication){
        int  v=venueApplicationService.applyVenue(venueApplication);
        if(v==1){
            return ResultFactory.buildFailResult("用户不存在");
        }else if(v==2){
            return ResultFactory.buildFailResult("该门户已经被拥有");
        }else if(v==3){
            return ResultFactory.buildFailResult("该用户已经拥有门户");
        }
        else{
            return ResultFactory.buildSuccessResult("申请门户成功");
        }
    }

    @GetMapping("/api/venue/get/{uid}")
    public Result listVenue(@PathVariable("uid") int uid) throws JsonProcessingException {
        VenueApplication v=venueApplicationService.getMyVenue(uid);
        String vid=v.getVenueid();
        Result result=searchService.getVenuerbyId(vid);
        Object res=result.getResult();
        ObjectMapper mapper =new ObjectMapper();
        String jsonstr="";
        jsonstr=mapper.writeValueAsString(res);
        JSONObject l=JSONObject.parseObject(jsonstr);
        Result a=ResultFactory.buildSuccessResult(l);
        return a;
    }
}

