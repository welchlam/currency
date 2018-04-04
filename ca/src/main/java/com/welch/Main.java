package com.welch;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

/**
 * Created by pdclwc on 30/03/2018.
 */
public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in).useDelimiter(Pattern.compile("[\\r\\n;]+"));
        ConcurrentHashMap<String, BigDecimal> currencies = new ConcurrentHashMap<String, BigDecimal>();
        FileLoader fileLoader = new FileLoader(scanner);
        HashMap<String, FxRate> fxRates = fileLoader.loadFxRate();
        fileLoader.optionalLoadCurrency(currencies);
        ConsoleWriter consoleWriter = new ConsoleWriter(currencies, fxRates, 60 * 1000);
        CommandReader commandReader = new CommandReader(currencies, consoleWriter, scanner, true);
        commandReader.start();
        consoleWriter.start();
    }

}
