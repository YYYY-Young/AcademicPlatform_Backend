package king.dao;

import king.entity.Venue;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.ArrayList;

/**
 * @Classname Venuedao
 * @Description TODO
 * @Date 2020/12/16 0:04
 * @Created by lrf
 */
public interface Venuedao extends ElasticsearchRepository<Venue,String> {
    Venue findVenueById(String id);

    ArrayList<Venue>findVenueByDisplayNameLike(String name);


}
