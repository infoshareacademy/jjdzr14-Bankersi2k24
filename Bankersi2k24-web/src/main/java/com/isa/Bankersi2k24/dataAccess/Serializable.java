package com.isa.Bankersi2k24.dataAccess;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Convert the object into JSON using ObjectMapper class of Jackson API.
 */
public class Serializable<T>{
    private final ObjectMapper objectMapper;
    private FileService fileService;
    private final Class<T> objectType;
    private Integer id;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Serializable(FileName fileName, Class<T> objectType) {
        this.objectType = objectType;
        this.objectMapper = new ObjectMapper();
        this.fileService = new FileService(fileName.toString());
    }

    public void save(){
        List<T> pojos = this.deserialize(this.fileService.read(), objectType);
        pojos.add((T) this);
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
    public static  <T extends Serializable<T>> Integer generateNewId(Class<T> tClass){
        Comparator<T> comparator = (Comparator<T>) Comparator.comparing(T::getId);
        try {
            T object = tClass.getDeclaredConstructor().newInstance();
            List<T> objects = (List<T>) object.fetchAllObjects();
            return (objects.isEmpty()) ? 0 : Collections.max(objects, comparator).getId()+1;
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
