package king;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import king.dao.Authordao;
import king.dao.Paperdao;
import king.dao.Venuedao;
import king.entity.Author;
import king.entity.Paper;
import king.entity.Venue;
import king.service.Authorservice;
import king.service.Paperservice;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPOutputStream;

/**
 * @Classname test1
 * @Description TODO
 * @Date 2020/12/14 16:45
 * @Created by lrf
 */
@SpringBootTest
public class test1 {
    @Autowired
    Paperdao paperdao;
    @Autowired
    Authordao authordao;
    @Autowired
    Venuedao venuedao;
    @Test
    public void test11() throws IOException, InterruptedException {
        FileInputStream inputStream = new FileInputStream("H:\\aminer\\aminer_authors_0\\aminer_venues.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        int i=0;
        String str = null;
        ArrayList<Venue>authors=new ArrayList<>();
        while((str = bufferedReader.readLine()) != null)
        {
            Venue author =new Venue();
            try {
                author=JSON.parseObject(str,Venue.class);
            }catch (Exception e){
                System.out.println(i+"数据解析有问题");
            }
            System.out.println(i);
            i++;
            authors.add(author);
            if(authors.size()>=10000){
                venuedao.saveAll(authors);
                authors.clear();
            }

        }
        venuedao.saveAll(authors);
        authors.clear();


        //close
        System.out.println("dayinle"+i);
        inputStream.close();
        bufferedReader.close();
    }
    @Test
    public void re(){
        String str="{\"id\": \"53e99784b7602d9701f3e130\", \"title\": \"1.4-N\\u2192N\\u2032-Acylwanderung bei einem Diaminosteroid\", \"authors\": [{\"name\": \"G. Adam\", \"id\": \"5601754245cedb3395e59454\"}, {\"name\": \"K. Schreiber\", \"id\": \"5405e615dabfae450f3df380\"}], \"venue\": {\"raw\": \"Angewandte Chemie\", \"id\": \"5451a5c7e0cf0b02b5f3ac13\"}, \"year\": 1965, \"n_citation\": 0, \"page_start\": \"94\", \"page_end\": \"95\", \"lang\": \"en\", \"volume\": \"77\", \"issue\": \"2\", \"doi\": \"10.1002/ange.19650770204\", \"url\": [\"http://dx.doi.org/10.1002/ange.19650770204\"]}";
        Paper paper=JSON.parseObject(str,Paper.class);
        paperdao.save(paper);
    }
    @Test
    public void testauthordao(){
        ArrayList<Author>authors=new ArrayList<>();
        Pageable pageable= PageRequest.of(1, 20);
        authors=authordao.findAuthorsByNameLike("david", pageable);
        for(Author author:authors){
            System.out.println(author);
        }
    }

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    Paperservice paperservice;
    @Test
    public void testtest(){
        List<Paper>papers=paperservice.searchpaper("改革",0,20,0);
        for(Paper paper:papers){
            System.out.println(paper.getYear());
        }
    }
    @Autowired
    Authorservice authorservice;
    @Test
    public void authorsearchtest(){
        List<Author> authors = authorservice.searchauthor("computer", 0, 10, 0);

        System.out.println(authors.size());
        for(Author author:authors){
           JSONArray js= JSON.parseArray(author.getTags());
            for(int i=0;i<js.size();++i){
               JSONObject json= (JSONObject) js.get(i);
                System.out.println(json.get("t"));
            }
            System.out.println("-----------------------------");
        }
    }
}
