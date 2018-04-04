package com.welch.client;

import com.welch.util.CommandValidator;
import com.welch.util.CurrencyValidator;

import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by pdclwc on 02/04/2018.
 */
public class ClientMain {

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Please input server IP address.");
        Scanner scanner = new Scanner(System.in).useDelimiter(Pattern.compile("[\\r\\n;]+"));
        String ip = scanner.nextLine();
        if(!CommandValidator.isIpValid(ip)){
            System.out.println("Invalid IP address, application exited.");
            return;
        }

        Client client = new Client();
        SocketChannel socketChannel;
        try{
            socketChannel = client.initClient(ip, 8888);
        } catch (Exception e){
            System.out.println(String.format("Not able to connect to %s:8888", ip));
            return;
        }
        final SocketChannel channel = socketChannel;

        //Output Thread
        new Thread(()->{
            try {
                client.listen();
            } catch (IOException e) {
                System.out.println("Connection exception, connection closed.");
                System.exit(1);
            }
        }).start();
        Thread.sleep(3000);
        System.out.println("Client connected to server.");
        CommandValidator.isValidCommand("help");

        //Input Thread
        new Thread(()->{
            try {
                String line;
                while(true){
                    line = scanner.next();
                    if(CommandValidator.isValidCommand(line)){
                        if(line.equalsIgnoreCase("show")){
                            client.write(channel, line);
                        }else if(line.equalsIgnoreCase("quit")){
                            System.exit(0);
                        }
                    }else if(CurrencyValidator.validate(line)==null){
                        client.write(channel, line);
                    }
                }
            } catch (Exception e) {
                System.out.println("sth wrong");
            }
        }).start();
    }
}
