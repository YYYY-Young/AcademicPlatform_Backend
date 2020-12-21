package king.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Classname Paper
 * @Description TODO
 * @Date 2020/12/9 15:26
 * @Created by lrf
 */
@Data
@Document(indexName = "paper",type = "_doc")
@ToString
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Paper {
    @Id
    @NotNull(message = "id 不能为 null")
    private String id;

    @NotEmpty(message = "文章标题不能为空")
    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String title;
    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String authors;//json字符串
    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String venue;//json字符串

    @Field(type = FieldType.Integer)
    private int year;

    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String keywords;

    @Field(type = FieldType.Integer)
    private int n_citation;

    @Field(analyzer = "ik_smart",type = FieldType.Text)
    private String page_start;

    @Field(analyzer = "ik_smart",type = FieldType.Text)
    private String page_end;

    @Field(analyzer = "ik_smart",type = FieldType.Text)
    private String doc_type;


    @JSONField(name="lang")
    @Field(type = FieldType.Keyword)
    private String lang;

    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    @JSONField(name="publisher")
    private String publisher;

    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String volume;

    @JSONField(name="issue")
    @Field(analyzer = "ik_smart",type = FieldType.Text)
    private String issue;

    @JSONField(name="issn")
    @Field(type = FieldType.Keyword)
    private String issn;

    @JSONField(name="isbn")
    @Field(type = FieldType.Keyword)
    private String isbn;

    @JSONField(name="doi")
    @Field(type = FieldType.Keyword)
    private String doi;

    @JSONField(name="pdf")
    @Field(type = FieldType.Text)
    private String pdf;

    @JSONField(name="url")
    @Field(type = FieldType.Text)
    private String url;

    @JSONField(name="abstract")
    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String p_abstract;

}
