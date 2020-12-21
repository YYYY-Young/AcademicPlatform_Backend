package king.service;

/**
 * @Classname search
 * @Description TODO
 * @Date 2020/12/19 12:43
 * @Created by lrf
 */

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import result.Result;
import result.ResultFactory;

@FeignClient("search-part")
public interface SearchService {
    @GetMapping("/api/author/search/id/{id}")
    public Result getAuthorbyId(@PathVariable("id") String id);

    @GetMapping("/api/author/search/keyword/{keyword}/{num}/{size}/{order}")
    public Result getAuthorbykeyword(@PathVariable("keyword") String keyword, @PathVariable("num") int num,
                                     @PathVariable("size") int size, @PathVariable("order") int order);

    @GetMapping("/api/author/search/name/{name}/{num}/{size}")
    public Result getAuthorbyname(@PathVariable("name") String name, @PathVariable("num") int num,
                                  @PathVariable("size") int size);

    @GetMapping("/api/author/search/tags/{name}/{num}/{size}")
    public Result getAuthorbytags(@PathVariable("name") String name, @PathVariable("num") int num,
                                  @PathVariable("size") int size);


    @GetMapping("/api/paper/search/keyword/{keyword}/{num}/{size}/{order}")
    public Result getPaperbykeyword(@PathVariable("keyword")String keyword, @PathVariable("num")int num,
                                    @PathVariable("size")int size, @PathVariable("order")int order);


    @GetMapping("/api/paper/search/id/{id}")
    public Result getPaperbyId(@PathVariable("id")String id);

    @GetMapping("/api/venue/search/id/{id}")
    public Result getVenuerbyId(@PathVariable("id")String id);

    @GetMapping("/api/venue/search/keyword/{keyword}/{num}/{size}")
    public Result getVenuebykeyword(@PathVariable("keyword")String keyword,@PathVariable("num")int num,
                                    @PathVariable("size")int size);
}

