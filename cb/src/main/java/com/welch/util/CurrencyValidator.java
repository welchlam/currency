package com.welch.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pdclwc on 30/03/2018.
 */
public class CurrencyValidator {
    @FunctionalInterface
    interface Validator{
        String validate(String input);
    }

    public static String validate(String input){
        List<Validator> validators = new ArrayList<>();

        validators.add(in -> {
            String msg = null;
            String[] data = in.split(" ");
            if(data.length!=2){
                msg = "Invalid input, no currency added. Please use space as delimiter for currency code and amount.";
                System.out.println(msg);
            }
            return msg;
        });

        validators.add(in -> {
            String msg = null;
            String[] data = in.split(" ");
            if(data[0].length()!=3 || !data[0].matches("[A-Z]{3}")){
                msg = "Invalid input, no currency added. Currency code should be 3 upper chars.";
                System.out.println(msg);
            }
            return msg;
        });

        validators.add(in -> {
            String msg = null;
            String[] data = in.split(" ");
            if(!isNumber(data[1])){
                msg = "Invalid input, no currency added. Invalid amount number.";
                System.out.println(msg);
            }
            return msg;
        });

        return validate(validators, input);
    }

    public static String validate(List<Validator> validators, String input){
        for(int i=0; i<validators.size(); i++){
            String msg = validators.get(i).validate(input);
            if(msg!=null){
                return msg;
            }
        }
        return null;
    }

    public static boolean isNumber(String input) {
        return input.matches("-?\\d+\\.\\d*") || input.matches("-?\\d+");
    }
}
