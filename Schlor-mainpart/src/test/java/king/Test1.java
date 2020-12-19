package king;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import king.dao.PaperDao;
import king.entity.Paper;
import king.service.SearchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jooq.JooqTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;

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
}
