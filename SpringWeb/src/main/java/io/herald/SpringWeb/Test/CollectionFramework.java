package io.herald.SpringWeb.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class CollectionFramework {

    public static void main(String[] args){

        //List --> ArrayList, LinkedList, Vector --> All are same, minor performance difference

        //List<Integer> intList = new Vector<>();

        //Set --> HashSet, LinkedHashSet, TreeSet --> No duplicate data

        //Map --> HashMap, LinkedHashMap, TreeMap

        Map<Integer, String> map = new HashMap<>();
        map.put(1,"apple");
        map.put(2,"pineapple");

        System.out.println(map);

    }
}
