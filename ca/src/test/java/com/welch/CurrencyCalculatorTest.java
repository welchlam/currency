package com.welch;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by pdclwc on 30/03/2018.
 */

public class CurrencyCalculatorTest {

    @Test
    public void testPut(){
        ConcurrentHashMap<String, BigDecimal> concurrentHashMap = new ConcurrentHashMap<>();
        String code = "CNY";
        String amount = "20";
        CurrencyCalculator.put(concurrentHashMap, code, amount);
        Assert.assertTrue(concurrentHashMap.get(code).equals(new BigDecimal(amount)));
    }

    @Test
    public void testPutWithIncrement(){
        ConcurrentHashMap<String, BigDecimal> concurrentHashMap = new ConcurrentHashMap<>();
        String code = "CNY";
        String amount = "20";
        String increment = "30";
        CurrencyCalculator.put(concurrentHashMap, code, amount);
        CurrencyCalculator.put(concurrentHashMap, code, increment);
        Assert.assertTrue(concurrentHashMap.get(code).equals(new BigDecimal(amount).add(new BigDecimal(increment))));
    }
}
