package com.example.elasticsearchapp.service;

import com.example.elasticsearchapp.document.Person;
import com.example.elasticsearchapp.helper.Indices;
import com.example.elasticsearchapp.helper.Utils;
import com.example.elasticsearchapp.repository.PersonRepository;
import jdk.jshell.execution.Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.xcontent.XContentType;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class IndexService {

    private final RestHighLevelClient restHighLevelClient;

    private final List<String> INDICES_TO_CREATE = List.of(Indices.VEHICLE_INDEX);

    @PostConstruct
    public void tryToCreateIndices(){

        final String settings = Utils.loadAsString("static/es-settings.json");
        for (final String index : INDICES_TO_CREATE){

            try{
                boolean isExists = restHighLevelClient.indices().exists(new GetIndexRequest(index), RequestOptions.DEFAULT);
                if(isExists){
                    continue;
                }

                final String mappings = Utils.loadAsString("static/mappings/"+ index + ".json");
                if(settings == null || mappings == null){
                    log.error("Failed to create index with name {}",index);
                }

                final CreateIndexRequest createIndexRequest = new CreateIndexRequest(index);
                createIndexRequest.settings(settings, XContentType.JSON);
                createIndexRequest.mapping(mappings, XContentType.JSON);

                restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);

            }catch (Exception e){
                log.error(e.getMessage(),e);
            }

        }


    }

}
