package com.welch;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by pdclwc on 30/03/2018.
 */
public class CommandReaderTest {
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
    public void testRunWithValidInput() throws InterruptedException {
        InputStream is = new ByteArrayInputStream("CNY 100\nquit\n".getBytes());
        Scanner scanner = new Scanner(is).useDelimiter("\n");
        CommandReader commandReader = new CommandReader(concurrentHashMap, consoleWriter, scanner, false);
        commandReader.start();
        Thread.sleep(500);
        Assert.assertTrue(this.concurrentHashMap.containsKey("CNY"));
        Assert.assertTrue(this.concurrentHashMap.get("CNY").equals(new BigDecimal("100")));
    }

    @Test
    public void testRunWithInValidInput() throws InterruptedException {
        InputStream is = new ByteArrayInputStream("CNYA 100\nquit\n".getBytes());
        Scanner scanner = new Scanner(is).useDelimiter("\n");
        CommandReader commandReader = new CommandReader(concurrentHashMap, consoleWriter, scanner, false);
        commandReader.start();
        Thread.sleep(500);
        Assert.assertFalse(this.concurrentHashMap.containsKey("CNYA"));
    }
}
