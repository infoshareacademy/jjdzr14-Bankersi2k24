package pl.isa.dataAccess;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
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

    public String convertObjectToJson(T pojo){
        /**
         * method that converts an object to JSON entity
         * @param object to be converted to JSON
         * @return String containing JSON entity, if exception, return null, otherwise an empty string
         *
         */
        try{
            return this.objectMapper.writeValueAsString(pojo);

        } catch (JsonProcessingException e) {
            // TODO: do more logging in the nearest future, perhaps a separate dabug_log file
            e.printStackTrace();
            return null;
        }
    }

    public String convertObjectToJson(List<T> pojos){
        //TODO: method overloading, this is for sake of time
        /**
         * method that converts an object list to JSON entity
         * @param object to be converted to JSON
         * @return String containing JSON entity, if exception, return null, otherwise an empty string
         *
         */
        try{
            return this.objectMapper.writeValueAsString(pojos);

        } catch (JsonProcessingException e) {
            // TODO: do more logging in the nearest future, perhaps a separate dabug_log file
            e.printStackTrace();
            return null;
        }
    }

    public <T> List<T> convertJsonToObjectList(String jsonArray, Class<T> tClass){
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
