package com.welch.util;

import com.welch.bean.Currency;
import com.welch.bean.FxRate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by pdclwc on 30/03/2018.
 */
public class CurrencyCalculator {
    public static ConcurrentHashMap<String, Currency> calculate(ConcurrentHashMap<String, Currency> concurrentHashMap, String input, HashMap<String, FxRate> fxRates){
        String data[] = input.split(" ");
        String code = data[0];
        String amount = data[1];
        Currency currency;
        if(concurrentHashMap.containsKey(code)){
            currency = concurrentHashMap.get(code);
            currency.setAmount(currency.getAmount().add(new BigDecimal(amount)));
        }else{
            currency = new Currency();
            currency.setCode(code);
            currency.setAmount(new BigDecimal(amount));
        }
        String info = String.format("%s %s", currency.getCode(), currency.getAmount());
        if(fxRates.containsKey(code)){
            info = String.format("%s (USD %s)", info, currency.getAmount().multiply(new BigDecimal(fxRates.get(code).getRate())));
        }
        currency.setInfo(info);
        concurrentHashMap.put(code, currency);
        return concurrentHashMap;
    }
}
