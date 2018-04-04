package com.welch.server;

import com.welch.bean.Currency;
import com.welch.bean.FxRate;
import com.welch.util.FileLoader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

/**
 * Created by pdclwc on 02/04/2018.
 */
public class ServerMain {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in).useDelimiter(Pattern.compile("[\\r\\n;]+"));
        ConcurrentHashMap<String, Currency> currencies = new ConcurrentHashMap();
        HashMap<String, FxRate> fxRates = FileLoader.loadFxRate();
        FileLoader.optionalLoadCurrency(currencies, fxRates, scanner);
        Server server = new Server();
        server.initServer(8888, currencies, fxRates);
        server.listen();
    }
}
