package pl.isa.dataAccess;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.IOException;
import java.util.*;

/**
 * Convert the object into JSON using ObjectMapper class of Jackson API.
 */
public class  ObjectToJson <T>{
    private final ObjectMapper objectMapper;

    public ObjectToJson() {
        this.objectMapper = new ObjectMapper();
    }

    public String serialize(T pojo){
        /**
         * method that converts an object to JSON entity
         * @param object to be converted to JSON
         * @return String containing JSON entity, if exception, return null, otherwise an empty string
         *
         */
        try{
            return this.objectMapper.writeValueAsString(pojo);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String serialize(List<T> pojos){
        /**
         * method that converts an object list to JSON entity
         * @param object to be converted to JSON
         * @return String containing JSON entity, if exception, return null, otherwise an empty string
         *
         */
        try{
            return this.objectMapper.writeValueAsString(pojos);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T> List<T> deserialize(String jsonArray, Class<T> tClass){
        List<T> pojoList;
        try {
            CollectionType objectType = this.objectMapper.getTypeFactory().constructCollectionType(List.class, tClass);
            pojoList = this.objectMapper.readValue(jsonArray, objectType);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return pojoList;
    }


}
