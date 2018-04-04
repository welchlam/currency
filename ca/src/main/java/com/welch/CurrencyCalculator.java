package com.welch;

import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by pdclwc on 30/03/2018.
 */
public class CurrencyCalculator {
    public static ConcurrentHashMap<String, BigDecimal> put(ConcurrentHashMap<String, BigDecimal> concurrentHashMap, String code, String amount){
        if(concurrentHashMap.containsKey(code)){
            concurrentHashMap.put(code, concurrentHashMap.get(code).add(new BigDecimal(amount)));
        }else{
            concurrentHashMap.put(code, new BigDecimal(amount));
        }
        return concurrentHashMap;
    }
}
