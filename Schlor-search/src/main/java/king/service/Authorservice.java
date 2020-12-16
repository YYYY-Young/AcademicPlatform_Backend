package king.service;

import king.dao.Authordao;
import king.entity.Author;
import king.entity.Paper;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname Authorservice
 * @Description TODO
 * @Date 2020/12/16 0:31
 * @Created by lrf
 */
@Service
public class Authorservice {

   @Autowired
   Authordao authordao;
   @Autowired
    ElasticsearchTemplate elasticsearchTemplate;
   public Author findbyid(String id){
       return authordao.findAuthorById(id);
   }
    public List<Author> searchauthor(String keyword, int pagenum, int pagesize, int sort) {
        if(sort==0) {
            NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                    .withQuery(QueryBuilders.queryStringQuery(keyword))
                    .withPageable(PageRequest.of(pagenum, pagesize))
                    .withSort(SortBuilders.fieldSort("n_pubs").order(SortOrder.DESC))
                    .build();

            return elasticsearchTemplate.queryForList(nativeSearchQuery, Author.class);
        }else {
            NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                    .withQuery(QueryBuilders.queryStringQuery(keyword))
                    .withPageable(PageRequest.of(pagenum, pagesize))
                    .withSort(SortBuilders.fieldSort("n_citation").order(SortOrder.DESC))
                    .build();
            return elasticsearchTemplate.queryForList(nativeSearchQuery, Author.class);
        }
    }

}
