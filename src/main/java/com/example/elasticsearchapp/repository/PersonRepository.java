package com.example.elasticsearchapp.repository;

import com.example.elasticsearchapp.document.Person;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


public interface PersonRepository extends ElasticsearchRepository<Person,String> {


}
