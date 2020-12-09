package king;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import king.dao.PaperDao;
import king.entity.Paper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
            paper.setTitle(jsonObject.getString("title"));

        }

        //close
        System.out.println("dayinle"+i);
        inputStream.close();
        bufferedReader.close();
    }
}
