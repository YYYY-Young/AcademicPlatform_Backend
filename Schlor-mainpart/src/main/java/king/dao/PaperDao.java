package king.dao;

import king.entity.Paper;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Classname PaperDao
 * @Description TODO
 * @Date 2020/12/9 15:43
 * @Created by lrf
 */
public interface PaperDao extends JpaRepository<Paper,Integer> {

}
