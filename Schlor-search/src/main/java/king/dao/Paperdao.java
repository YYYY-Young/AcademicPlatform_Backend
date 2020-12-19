package king.dao;

import king.entity.Paper;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Classname Paperdao
 * @Description TODO
 * @Date 2020/12/14 16:40
 * @Created by lrf
 */

public interface Paperdao extends ElasticsearchRepository<Paper,String> {

    Paper findPaperById(String id);

}
