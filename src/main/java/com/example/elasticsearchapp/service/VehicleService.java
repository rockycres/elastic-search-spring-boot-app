package com.example.elasticsearchapp.service;

import com.example.elasticsearchapp.document.Person;
import com.example.elasticsearchapp.document.Vehicle;
import com.example.elasticsearchapp.helper.Indices;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.xcontent.XContentType;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class VehicleService {

    private final RestHighLevelClient client;
    private static final ObjectMapper MAPPER = new ObjectMapper();


    public boolean index(final Vehicle vehicle){
        try{
           final String vehicleAsString = MAPPER.writeValueAsString(vehicle);
           final IndexRequest request = new IndexRequest(Indices.VEHICLE_INDEX);
           request.id(vehicle.getId());
           request.source(vehicleAsString, XContentType.JSON);

           final IndexResponse response = client.index(request, RequestOptions.DEFAULT);

           return response != null && response.status().equals(RestStatus.OK);
        }catch (final Exception e){
            log.error(e.getMessage(), e);
            return false;
        }
    }

    public Vehicle getById(final String vehicleId){
        try{
            final GetResponse documentFields = client.get(
                    new GetRequest(Indices.VEHICLE_INDEX,vehicleId),
                    RequestOptions.DEFAULT);

            if(documentFields == null){
                return null;
            }
            return MAPPER.readValue(documentFields.getSourceAsString(), Vehicle.class);

        }catch (final Exception e){
            log.error(e.getMessage(), e);
            return null;
        }
    }

}
