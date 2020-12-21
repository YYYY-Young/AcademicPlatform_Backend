package king.service;

import king.dao.Venuedao;
import king.entity.Paper;
import king.entity.Venue;
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
 * @Classname Venueservice
 * @Description TODO
 * @Date 2020/12/16 8:43
 * @Created by lrf
 */
@Service
public class Venueservice {
    @Autowired
    Venuedao venuedao;

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;
    public Venue findbyid(String id){
        return venuedao.findVenueById(id);
    }

    public List<Venue> searchvenue(String keyword, int pagenum, int pagesize) {
            NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                    .withQuery(QueryBuilders.queryStringQuery(keyword))
                    .withPageable(PageRequest.of(pagenum, pagesize))
                    .withSort(SortBuilders.fieldSort("displayName").order(SortOrder.DESC))
                    .build();

            return elasticsearchTemplate.queryForList(nativeSearchQuery, Venue.class);

    }
    public List<Venue>findbyname(String name){
        return venuedao.findVenueByDisplayNameLike(name);
    }
}
