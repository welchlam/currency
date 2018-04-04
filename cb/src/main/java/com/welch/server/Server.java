package com.welch.server;

import com.welch.bean.Currency;
import com.welch.bean.FxRate;
import com.welch.util.CurrencyCalculator;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by pdclwc on 02/04/2018.
 */
public class Server {
    private Selector selector;
    private ConcurrentHashMap<String, Currency> concurrentHashMap;
    private HashMap<String, FxRate> fxRates;
    //private ArrayList<SocketChannel> clients = new ArrayList<>();

    public void initServer(int port, ConcurrentHashMap<String, Currency> concurrentHashMap, HashMap<String, FxRate> fxRates) throws IOException {
        this.concurrentHashMap = concurrentHashMap;
        this.fxRates = fxRates;
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        this.selector = Selector.open();
        serverSocketChannel.register(this.selector, SelectionKey.OP_ACCEPT);
        System.out.println("Server started at port 8888, client can connect server now.");
    }

    public void listen() throws IOException {
        while(true){
            selector.select();
            Iterator iterator = this.selector.selectedKeys().iterator();
            while(iterator.hasNext()){
                SelectionKey selectionKey = (SelectionKey) iterator.next();
                iterator.remove();
                if(selectionKey.isAcceptable()){
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(this.selector, SelectionKey.OP_READ);
                    //this.clients.add(socketChannel);
                }else if(selectionKey.isReadable()){
                    this.read(selectionKey);
                }
            }
        }
    }

    private void read(SelectionKey selectionKey) throws IOException {
        SocketChannel channel = (SocketChannel) selectionKey.channel();
        ByteBuffer buffer = ByteBuffer.allocate(126);
        try{
            channel.read(buffer);
        }catch (Exception e){
            //this.closeClient(channel);
        }
        byte[] data = buffer.array();
        String msg = new String(data).trim();
        if(msg.equalsIgnoreCase("show")){
            this.write(selectionKey);
        }else if(msg!=null && !msg.trim().equals("")) {
            CurrencyCalculator.calculate(this.concurrentHashMap, msg, this.fxRates);
        }
    }

    public void write(SelectionKey selectionKey) {
        SocketChannel channel = (SocketChannel) selectionKey.channel();
        String msg ="";
        for (Currency currency: this.concurrentHashMap.values()){
            msg += currency.getInfo();
            msg += "\n";
        }
        try{
            channel.write(ByteBuffer.wrap(msg.getBytes()));
        }catch (Exception e){
            System.out.println("Client exception, client closed.");
        }
    }

    /*private void closeClient(SocketChannel channel) throws IOException {
        this.clients.remove(channel);
        channel.close();
    }*/
}
