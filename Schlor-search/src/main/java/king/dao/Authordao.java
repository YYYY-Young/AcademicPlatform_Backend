package king.dao;

import king.entity.Author;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.ArrayList;
import java.util.Optional;

/**
 * @Classname Authordao
 * @Description TODO
 * @Date 2020/12/15 20:05
 * @Created by lrf
 */
public interface Authordao extends ElasticsearchRepository<Author,String> {

    Author findAuthorById(String id);

    ArrayList<Author>findAuthorsByNameLike(String name, Pageable pageable);






}
