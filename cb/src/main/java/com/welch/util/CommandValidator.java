package com.welch.util;

/**
 * Created by pdclwc on 03/04/2018.
 */
public class CommandValidator {
    public static boolean isValidCommand(String input){
        if (input.equalsIgnoreCase("help")){
            String msg = "\nType 'help' to display help information.\n";
            msg += "Type 'show' to pull currencies from server\n";
            msg += "Type 'quit' to disconnect from server\n";
            msg += "Type 'Currency Amount' e.g. USD 100 to send currency to server\n";
            System.out.println(msg);
            return true;
        } else if (input.equalsIgnoreCase("show")){
            return true;
        } else if (input.equalsIgnoreCase("quit")){
            return true;
        } else{
            return false;
        }
    }

    public static boolean isIpValid(String ip){
        return ip.matches("^((25[0-5]|2[0-4]\\d|[1]{1}\\d{1}\\d{1}|[1-9]{1}\\d{1}|\\d{1})($|(?!\\.$)\\.)){4}$") || ip.equalsIgnoreCase("localhost");
    }
}
