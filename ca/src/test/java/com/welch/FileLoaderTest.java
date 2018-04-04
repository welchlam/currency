package com.welch;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by pdclwc on 30/03/2018.
 */
public class FileLoaderTest {

    private FileLoader fileLoader = null;

    @Before
    public void before() {
        this.fileLoader = new FileLoader(null);
    }

    @Test
    public void loadCurrencyTest() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource("currency.txt");
        System.out.println(url.getFile());
        ConcurrentHashMap<String, BigDecimal> concurrentHashMap = new ConcurrentHashMap<>();
        this.fileLoader.loadCurrency(url.getFile(), concurrentHashMap);
        Assert.assertTrue(concurrentHashMap.containsKey("CNY"));
        Assert.assertTrue(concurrentHashMap.containsKey("HKD"));
        Assert.assertTrue(concurrentHashMap.containsKey("USD"));
        Assert.assertFalse(concurrentHashMap.containsKey("AUD"));
        Assert.assertTrue(concurrentHashMap.get("CNY").equals(new BigDecimal("2000")));
        Assert.assertFalse(concurrentHashMap.get("CNY").equals(new BigDecimal("1000")));
    }

    @Test
    public void loadFxRateTest() {
        HashMap<String, FxRate> fxRates = fileLoader.loadFxRate();
        Assert.assertTrue(fxRates.containsKey("CNY"));
        Assert.assertTrue(fxRates.containsKey("HKD"));
        Assert.assertFalse(fxRates.containsKey("AUD"));
        Assert.assertFalse(fxRates.get("CNY").getRate().equals("1.0"));
        Assert.assertTrue(fxRates.get("CNY").getRate().equals("0.1594"));
    }

}
