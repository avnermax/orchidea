package com.deviantdev.libpd;

/**
 * Created by Eduardo on 22/07/2015.
 */

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class rodarBackGround extends AsyncTask<Object, Void, Boolean> {

    String messageText;
    private static final String DEBUG_TAG = "rodarBackGround";
    private static final int BUFFER_SIZE = 4096;
    private final Context context;
    int MULTICAST_PORT = 6789;
    ShakeActivity activity;
    PianoActivity activity1;

    public rodarBackGround(Context context) {
        this.context = context;
        if(this.context instanceof ShakeActivity)
            this.activity = (ShakeActivity) context;
        if(this.context instanceof PianoActivity)
            this.activity1 = (PianoActivity) context;
    }

    @Override
    protected Boolean doInBackground(Object... param) {

        WifiManager wim = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        if(wim != null) {
            WifiManager.MulticastLock mcLock = wim.createMulticastLock("tag");
            mcLock.acquire();
        }

        byte[] buffer = new byte[BUFFER_SIZE];
        DatagramPacket rPacket = new DatagramPacket(buffer, buffer.length);
        MulticastSocket rSocket = null;

        InetAddress group = null;
        try {
            group = InetAddress.getByName("228.5.6.7");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        try {
            rSocket = new MulticastSocket(6789);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            rSocket.joinGroup(group);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            rSocket.receive(rPacket);
        } catch (IOException e1) {
            //Log.d(DEBUG_TAG, "There was a problem receiving the incoming message.");
            e1.printStackTrace();
        }

        byte data[] = rPacket.getData();
        int i;
        for (i = 0; i < data.length; i++) {
            if (data[i] == '\0')
                break;
        }


        try {
            messageText = new String(data, 0, i, "UTF-8");
            Log.d("teste",messageText);
        } catch (UnsupportedEncodingException e) {
            //Log.d(DEBUG_TAG, "UTF-8 encoding is not supported. Can't receive the incoming message.");
            e.printStackTrace();
        }

    return true;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        String message[] = messageText.split("/");
        if(this.activity instanceof ShakeActivity)
            ((ShakeActivity) activity).tocar(message[0],(Float.parseFloat(message[1])));
        if(this.activity1 instanceof PianoActivity)
            ((PianoActivity) activity1).tocar(message[0],(Float.parseFloat(message[1])));
    }
}