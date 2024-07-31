package com.isa.Bankersi2k24.DAO;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.isa.Bankersi2k24.models.Entity;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Convert the object into JSON using ObjectMapper class of Jackson API.
 */
public class Serializable<T extends Entity>{
    private final ObjectMapper objectMapper;
    private FileService fileService;
    private final Class<T> objectType;

    public Serializable(FileName fileName, Class<T> objectType) {
        this.objectType = objectType;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        this.fileService = new FileService(fileName.getName());
    }

    public void save(T obj){
        List<T> pojos = this.deserialize(this.fileService.read(), objectType);
        if(obj.getId() == null)
            obj.setId(getNewId());
        pojos.add((T) obj);
        this.fileService.saveJson(this.serialize(pojos));
    }

    public String serialize(List<T> pojos){
        /**
         * method that converts an object list to JSON entity
         * @param object to be converted to JSON
         * @return String containing JSON entity, if exception, return null, otherwise an empty string
         *
         */
        try{
            return this.objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(pojos);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<T> deserialize(String jsonArray, Class<T> tClass){
        if(jsonArray.isEmpty()) return new ArrayList<T>();

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

    public List<T> fetchAllObjects(){
        return this.deserialize(this.fileService.read(), this.objectType);
    }

    public <T extends Entity> BigInteger getNewId(){
        List<T> objects = (List<T>) fetchAllObjects();
        Comparator<T> comparator = (Comparator<T>) Comparator.comparing(T::getId);
        return (objects.isEmpty()) ? BigInteger.ZERO : Collections.max(objects, comparator).getId().add(BigInteger.ONE);
    }
}
