package com.welch;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by pdclwc on 30/03/2018.
 */
public class ConsoleWriter extends Thread {
    private ConcurrentHashMap<String, BigDecimal> concurrentHashMap;
    private Boolean stopped = false;
    private HashMap<String, FxRate> fxRates;
    private int interval;

    public ConsoleWriter(ConcurrentHashMap<String, BigDecimal> concurrentHashMap, HashMap<String, FxRate> fxRates, int interval){
        this.concurrentHashMap = concurrentHashMap;
        this.fxRates = fxRates;
        this.interval = interval;
    }

    public void run(){
        try {
            while(!stopped){

                System.out.println("\n\n++++++++++++++++++ ALL CURRENCIES START ++++++++++++++++++");
                concurrentHashMap.keySet().stream().filter(key -> concurrentHashMap.get(key).compareTo(BigDecimal.ZERO)!=0).forEach(key -> {
                    String info = key + " " + concurrentHashMap.get(key);
                    if(fxRates.containsKey(key)){
                        info += " ("+fxRates.get(key).getTargetCode()+" "+ concurrentHashMap.get(key).multiply(new BigDecimal(fxRates.get(key).getRate())) +")";
                    }
                    System.out.println(info);
                });
                System.out.println("++++++++++++++++++ ALL CURRENCIES END ++++++++++++++++++++\n");
                Thread.sleep(interval);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized  void setStopped(boolean stopped, boolean exit) {
        this.stopped = stopped;
        if(exit){
            System.exit(0);
        }
    }
}
