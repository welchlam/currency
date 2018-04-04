package com.welch.util;

import com.welch.bean.Currency;
import com.welch.bean.FxRate;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by pdclwc on 30/03/2018.
 */
public class FileLoader {

    public static void optionalLoadCurrency(ConcurrentHashMap<String, Currency> concurrentHashMap, HashMap<String, FxRate> fxRates, Scanner scanner){
        System.out.println("Load currency file? Y - for YES, other inputs for NO");
        String input = scanner.next();
        if(input.equalsIgnoreCase("Y")){
            System.out.println("Please input the full path of currency file");
            input = scanner.next();
            if(new File(input).exists()){
                loadCurrency(input, concurrentHashMap, fxRates);
            }else{
                System.out.println("File not found, no currencies loaded!");
            }
        }
    }

    public static ConcurrentHashMap<String, Currency> loadCurrency(String file, ConcurrentHashMap<String, Currency> concurrentHashMap, HashMap<String, FxRate> fxRates) {
        try(InputStreamReader reader = new InputStreamReader(new FileInputStream(file)); BufferedReader br = new BufferedReader(reader)) {
            String line = br.readLine();
            while (line != null) {
                CurrencyCalculator.calculate(concurrentHashMap, line, fxRates);
                line = br.readLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("Currency loaded successfully.");
        return concurrentHashMap;
    }

    public static HashMap<String, FxRate> loadFxRate(){
        HashMap<String, FxRate> hashMap = new HashMap<>();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream in = classLoader.getResourceAsStream("fxRate.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(" ");
                FxRate fxRate = new FxRate();
                fxRate.setSrcCode(data[0]);
                fxRate.setTargetCode(data[1]);
                fxRate.setRate(data[2]);
                hashMap.put(fxRate.getSrcCode(), fxRate);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("FX Rate loaded successfully.");
        return hashMap;
    }

}
