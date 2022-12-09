package com.example.elasticsearchapp.document;

import com.example.elasticsearchapp.helper.Indices;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;


@NoArgsConstructor
@Getter
@Setter
@Data
@Document(indexName = Indices.PERSON_INDEX)
@Setting(settingPath = "static/es-settings.json")
public class Person {

    @Id
    @Field(type = FieldType.Keyword)
    private  String id;

    @Field(type = FieldType.Text)
    private  String name;


}
