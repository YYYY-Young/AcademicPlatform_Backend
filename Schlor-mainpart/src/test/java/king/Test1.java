package king;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.scripts.JO;
import king.dao.PaperDao;
import king.dao.UserDao;
import king.entity.Paper;
import king.entity.User;
import king.service.SearchService;
import king.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jooq.JooqTest;
import org.springframework.boot.test.context.SpringBootTest;
import result.Result;
import result.ResultFactory;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * @Classname Test1
 * @Description TODO
 * @Date 2020/12/9 18:27
 * @Created by lrf
 */
@SpringBootTest
public class Test1 {
    @Autowired
    private PaperDao paperDao;
    @Autowired
    UserDao userDao;
    @Test
    public void test1() throws IOException {
        FileInputStream inputStream = new FileInputStream("C:\\Users\\lrf\\Desktop\\mag_papers_2\\mag_papers_10.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        int i=0;
        String str = null;
        while((str = bufferedReader.readLine()) != null)
        {
            JSONObject jsonObject= JSON.parseObject(str);
            Paper paper=new Paper();
            paper.setPaperid(jsonObject.getInteger("id"));
            paper.setTitle(jsonObject.getString("title"));
            paper.setAuthors(jsonObject.getString("authors"));
            paper.setVenue(jsonObject.getString("venue"));
            paper.setYear(jsonObject.getInteger("year"));
            paper.setN_citation(jsonObject.getInteger("n_citation"));
            paper.setPage_start(jsonObject.getString("page_start"));
            paper.setPage_end(jsonObject.getString("page_end"));
            paper.setDoc_type(jsonObject.getString("doc_type"));
            paper.setPublisher(jsonObject.getString("publisher"));
            paper.setVolume(jsonObject.getString("volume"));
            paper.setIssue(jsonObject.getString("issue"));
            System.out.println(i);
            i++;

            paperDao.save(paper);
        }

        //close
        System.out.println("dayinle"+i);
        inputStream.close();
        bufferedReader.close();
    }
    @Autowired
    SearchService searchService;
    @Test
    public void test3(){
        System.out.println(searchService.getAuthorbyname("david",0,10));
    }
    @Test
    public void test2() throws JsonProcessingException {
        Result r=searchService.getAuthorbyname("david",0,5);
        Object res=r.getResult();
        ObjectMapper mapper =new ObjectMapper();
        String jsonstr="";
        jsonstr=mapper.writeValueAsString(r);
        System.out.println(jsonstr);
        JSONObject l=JSONObject.parseObject(jsonstr);
        System.out.println(l.get("result"));
        JSONArray array=JSONObject.parseArray(l.get("result").toString());
        Result a=ResultFactory.buildSuccessResult(array);
        System.out.println(a);

//        JSONArray l =JSONObject.parseArray(jsonstr);
//        for(int i=0;i<l.size();++i){
//            JSONObject json= (JSONObject) l.get(i);
//            System.out.println(json.get("message"));
//        }
    }

    @Test
    public void  testanetwork() throws JsonProcessingException {
        String aid="56061d5245ce1e595e92c80f";
        Result mainexper =searchService.getAuthorbyId(aid);
        Object res = mainexper.getResult();
        ObjectMapper mapper =new ObjectMapper();
        String jsonstr="";
        jsonstr=mapper.writeValueAsString(res);
        JSONObject l=JSONObject.parseObject(jsonstr);
        JSONArray js= JSON.parseArray(l.get("pubs").toString());
        System.out.println(js);
        ObjectMapper mapper2 =new ObjectMapper();
        String jsonstr2="";
        jsonstr2=mapper2.writeValueAsString(js);
        JSONArray array=JSONArray.parseArray(jsonstr2);
        for(int i=0;i<array.size();++i){
            JSONObject json= (JSONObject) array.get(i);
            String pid =json.get("i").toString();
            System.out.println(pid);
            Result paper =searchService.getPaperbyId(pid);
            Object paperobj =paper.getResult();
            if(paperobj==null){
                continue;
            }else{
                ObjectMapper mapper3 =new ObjectMapper();
                String jsonstr3="";
                jsonstr3=mapper3.writeValueAsString(paperobj);
                JSONObject p=JSONObject.parseObject(jsonstr3);
                System.out.println(p);
            }
        }

    }
}
