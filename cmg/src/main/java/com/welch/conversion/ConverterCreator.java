package com.welch.conversion;

import com.welch.conversion.object.Rule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pdclwc on 08/09/2017.
 */
public class ConverterCreator {

    public static Converter createConverter() throws IOException {
        String host = InetAddress.getLocalHost().getHostName();
        String user = System.getProperty("user.name");
        String serverId = host.split("\\.")[0];
        String extension = serverId.substring(serverId.length()-4, serverId.length()) + user.substring(user.length()-2, user.length());
        List<Rule> ruleList = new ArrayList<Rule>();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try{
            inputStream = classLoader.getResourceAsStream("conversion_rules.csv");
            inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                line = line.replace("${EXTENSION}",extension)
                        .replace("${HOSTNAME}",host)
                        .replace("${USER}",user);
                String[] values = line.split(",");
                Rule rule = new Rule();
                rule.setTagName(values[0]);
                rule.setAttribute(values[1]);
                rule.setConverType(values[2]);
                rule.setOldValue(values[3].replace("${EMPTY}",""));
                rule.setNewValue(values[4].replace("${EMPTY}",""));
                ruleList.add(rule);
                /*System.out.println(rule.getTagName()+","+rule.getAttribute() + ","+rule.getConverType() + ","+rule.getOldValue() + ","+rule.getNewValue());*/
            }
        }finally {
            inputStream.close();
            inputStreamReader.close();
            bufferedReader.close();
        }
        return new Converter(ruleList);
    }

    /*public static void main(String[] args) throws IOException {
        ConverterCreator.createConverter();
    }*/
}
