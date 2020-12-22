package king.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;

/**
 * @Classname Author
 * @Description TODO
 * @Date 2020/12/15 14:18
 * @Created by lrf
 */
@Data
@Document(indexName = "author",type = "_doc")
@ToString
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Author {
    @Id
    @NotNull(message = "id 不能为 null")
    private String id;

    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String name;

    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String normalized_name;

    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String orgs;

    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String org;

    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String position;

    @Field(type = FieldType.Integer)
    private int n_pubs;

    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String pubs;

    @Field(type = FieldType.Integer)
    private int n_citation;

    @Field(analyzer = "ik_max_word",type = FieldType.Integer)
    private int h_index;

    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String tags;


}
