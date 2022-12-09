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
public class Vehicle {

    private  String id;

    private  String number;

}
