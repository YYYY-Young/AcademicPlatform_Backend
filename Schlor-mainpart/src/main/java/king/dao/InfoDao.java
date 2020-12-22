package king.dao;


import king.entity.Info;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InfoDao extends JpaRepository<Info,Integer> {
    Info findById(int id);
    List<Info> findAllByType(String type);
    List<Info> findAllBySenderid(int id);
    List<Info> findAllByRecipientid(int id);
    List<Info> findAllByRecipientidOrSenderid(int rid,int sid);
    List<Info> findAllByRecipientidAndSenderid(int rid,int sid);
}

