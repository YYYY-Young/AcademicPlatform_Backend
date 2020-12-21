package king.dao;

import king.entity.UserFavorites;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserFavoritesDao extends JpaRepository<UserFavorites,Integer> {
    UserFavorites findById(int id);
    UserFavorites findByUseridAndPaperid(int uid,String pid);
    List<UserFavorites> findAllByUserid(int uid);
    List<UserFavorites> findAllByPaperid(String pid);
}
