package fr.umontpellier.iut;

import java.util.HashMap;
import java.util.Map;

public class GestionDistances {

    private final static Map<String, Integer> distVilles;

    static {
        distVilles = new HashMap<>();
        distVilles.put("Montpellier",0);
        distVilles.put("Sète",36);
        distVilles.put("Sommières",30);
        distVilles.put("Nîmes",58);
        distVilles.put("Lunel",30);
        distVilles.put("Béziers",80);
    }

    public static int getDistance(String d){
        if(distVilles.containsKey(d)) {
            return distVilles.get(d);
        }
        else{
            return 0;
        }
    }
}
