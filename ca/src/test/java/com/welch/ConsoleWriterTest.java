package com.welch;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by pdclwc on 30/03/2018.
 */
public class ConsoleWriterTest {
    private ConcurrentHashMap<String, BigDecimal> concurrentHashMap;
    private HashMap<String, FxRate> fxRates;
    private ConsoleWriter consoleWriter;

    @Before
    public void before(){
        concurrentHashMap = new ConcurrentHashMap<>();
        fxRates = new HashMap<>();
        consoleWriter = new ConsoleWriter(concurrentHashMap, fxRates, 1000);
    }

    @Test
    public void testRun() throws InterruptedException {
        concurrentHashMap.put("CNY", new BigDecimal("100"));
        consoleWriter.start();
        Thread.sleep(2000);
        consoleWriter.setStopped(true, false);
        Assert.assertTrue(concurrentHashMap.get("CNY").equals(new BigDecimal("100")));
    }

}
