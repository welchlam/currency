package com.welch;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by pdclwc on 30/03/2018.
 */
public class CommandReader extends Thread{
    private ConcurrentHashMap<String, BigDecimal> concurrentHashMap;
    private ConsoleWriter consoleWriter;
    private Scanner scanner = null;
    private boolean quitForExit;

    public CommandReader(ConcurrentHashMap<String, BigDecimal> concurrentHashMap, ConsoleWriter consoleWriter, Scanner scanner, boolean quitForExit){
        this.concurrentHashMap = concurrentHashMap;
        this.consoleWriter = consoleWriter;
        this.scanner = scanner;
        this.quitForExit = quitForExit;
    }

    public void run(){
        String line = null;
        String code;
        String amount;
        while(true){
            line = scanner.next();
            if(line!=null && line.equals("quit")){
                consoleWriter.setStopped(true, quitForExit);
                scanner.close();
                break;
            }
            if(CurrencyValidator.validate(line)==null){
                code = line.split(" ")[0];
                amount = line.split(" ")[1];
                CurrencyCalculator.put(concurrentHashMap, code, amount);
            }
        }
    }

}
