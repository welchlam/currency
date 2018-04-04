package com.welch;

import java.io.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by pdclwc on 30/03/2018.
 */
public class FileLoader {

    private Scanner scanner = null;

    public FileLoader(Scanner scanner) {
        this.scanner = scanner;
    }

    public void optionalLoadCurrency(ConcurrentHashMap<String, BigDecimal> concurrentHashMap){
        System.out.println("Load currency file? Y - for YES, other inputs for NO");
        String input = scanner.next();
        if(input.equalsIgnoreCase("Y")){
            System.out.println("Please input the full path of currency file");
            input = scanner.next();
            if(new File(input).exists()){
                this.loadCurrency(input, concurrentHashMap);
            }else{
                System.out.println("File not found, no currencies loaded!");
            }
        }
    }

    ConcurrentHashMap<String, BigDecimal> loadCurrency(String file, ConcurrentHashMap<String, BigDecimal> concurrentHashMap) {
        try(InputStreamReader reader = new InputStreamReader(new FileInputStream(file)); BufferedReader br = new BufferedReader(reader)) {
            String line = "";
            String code;
            String amount;
            line = br.readLine();
            while (line != null) {
                code = line.split(" ")[0];
                amount = line.split(" ")[1];
                CurrencyCalculator.put(concurrentHashMap, code, amount);
                line = br.readLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return concurrentHashMap;
    }

    public HashMap<String, FxRate> loadFxRate(){
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
        return hashMap;
    }

}
