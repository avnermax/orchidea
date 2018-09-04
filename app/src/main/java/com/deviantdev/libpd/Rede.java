package com.deviantdev.libpd;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by Eduardo on 25/07/2015.
 */
public class Rede {

    private static final int BUFFER_SIZE = 4096;
    private final Context context;
    protected int MULTICAST_PORT;
    private MulticastSocket socket;

    public Rede(Context context, int multicastPort) throws IllegalArgumentException {
        if(context == null || multicastPort <= 1024 || multicastPort > 49151)
            throw new IllegalArgumentException();

        this.context = context;
        MULTICAST_PORT = multicastPort;
    }

    public boolean sendMessage(String message) throws IllegalArgumentException, IOException {
        if(message == null || message.length() == 0)
            throw new IllegalArgumentException();

        // Check for WiFi connectivity
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if(mWifi == null || !mWifi.isConnected())
        {
            return false;
        }

        //se conecta no grupo em uma determinada faixa de ip e envia a mensagem usando uma thread
        InetAddress group = InetAddress.getByName("228.5.6.7");
        socket = new MulticastSocket(6789);
        socket.joinGroup(group);
        DatagramPacket packet;
        byte data[] = message.getBytes();

        packet = new DatagramPacket(data, data.length,group, MULTICAST_PORT);

        chamaThread(packet);

        return true;
    }

    public void chamaThread(final DatagramPacket pacote){

        new Thread(new Runnable(){
            public void run() {
                try {
                    socket.send(pacote);
                } catch (IOException e) {
                    //Log.d(DEBUG_TAG, "There was an error sending the UDP packet. Aborted.");
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void receiveMenssage() {
        new rodarBackGround(context).execute();
    }
}
