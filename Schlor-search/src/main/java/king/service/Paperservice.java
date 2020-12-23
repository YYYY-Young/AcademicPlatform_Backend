package king.service;

import king.dao.Paperdao;
import king.entity.Author;
import king.entity.Paper;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname Paperservice
 * @Description TODO
 * @Date 2020/12/16 8:43
 * @Created by lrf
 */
@Service
public class Paperservice {
    @Autowired
    Paperdao paperdao;
    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    public Paper getbyid(String id) {
        return paperdao.findPaperById(id);
    }

    public Page<Paper> searchpaper(String keyword, int pagenum, int pagesize,int sort) {
        if(sort==0) {
            NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                    .withQuery(QueryBuilders.queryStringQuery(keyword))
                    .withPageable(PageRequest.of(pagenum, pagesize))
                    .withSort(SortBuilders.fieldSort("year").order(SortOrder.DESC))
                    .build();
        return paperdao.search(nativeSearchQuery);
        }else {
            NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                    .withQuery(QueryBuilders.queryStringQuery(keyword))
                    .withPageable(PageRequest.of(pagenum, pagesize))
                    .withSort(SortBuilders.fieldSort("n_citation").order(SortOrder.DESC))
                    .build();
            return paperdao.search(nativeSearchQuery);
        }
    }
    public Long findall(){
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .build();
        Page<Paper> papers=paperdao.search(nativeSearchQuery);
        return papers.getTotalElements();
    }
}
