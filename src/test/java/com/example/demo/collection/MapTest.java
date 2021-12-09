package com.example.demo.collection;

import org.junit.jupiter.api.Test;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


import java.util.*;

public class MapTest {
    /**
     * MultiValueMap
     * 要实现1个key对应多个value,可以用apach提供的MultiValueMap,Spring的内部实现是LinkedMultiValueMap
     */
    @Test
    public void MultiValueMapTest(){
        MultiValueMap<String,String> multiValueMap = new LinkedMultiValueMap();
        multiValueMap.add("name","参数1");
        multiValueMap.add("name","参数2");
        multiValueMap.add("name","参数3");
        System.out.println(multiValueMap.get("name").get(2));
        List idList = new ArrayList();
        idList.add(UUID.randomUUID().toString().replaceAll("-",""));
        idList.add(UUID.randomUUID().toString().replaceAll("-",""));
        idList.add(UUID.randomUUID().toString().replaceAll("-",""));
        multiValueMap.put("id",idList);
        System.out.println(multiValueMap.get("id").get(2));
        for(String key : multiValueMap.keySet()){
            List<String> values = multiValueMap.get(key);
            for(String value : values){
                System.out.println(key +": "+value);
            }
        }

    }
}
