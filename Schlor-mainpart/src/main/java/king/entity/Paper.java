package king.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mysql.cj.xdevapi.JsonString;
import lombok.Data;
import lombok.ToString;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * @Classname Paper
 * @Description TODO
 * @Date 2020/12/9 15:26
 * @Created by lrf
 */
@Data
@Entity
@Table(name = "paper")
@ToString
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Paper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull(message = "id 不能为 null")
    private int id;

    @NotEmpty(message = "文章标题不能为空")
    private String title;

    private String authors;//json字符串

    private String venue;//json字符串

    private int year;

    private int n_citation;

    private String page_start;

    private String page_end;

    private String doc_type;

    private String publisher;

    private String volume;

    private String issue;

}
