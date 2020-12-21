package king.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.validation.constraints.NotNull;

/**
 * @Classname Venue
 * @Description TODO
 * @Date 2020/12/15 20:06
 * @Created by lrf
 */
@Data
@Document(indexName = "venue",type = "_doc")
@ToString
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Venue {
    @Id
    @NotNull(message = "id 不能为 null")
    private String id;

    @Field(analyzer = "ik_max_word",type = FieldType.Keyword)
    String journalId;

    @Field(analyzer = "ik_max_word",type = FieldType.Keyword)
    String conferenceId;

    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    String displayName;

    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    String normalizedName;
}
