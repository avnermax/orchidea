package com.deviantdev.libpd;

import android.content.Context;
import android.os.AsyncTask;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eduardo on 25/07/2015.
 */
public class BackGroundCentralizado extends AsyncTask<Object, Void, Boolean> {

    private Context context;
    JSONParser jsonParser = new JSONParser();


    public BackGroundCentralizado(Context context) {
        this.context = context;
    }

    @Override
    protected Boolean doInBackground(Object... param) {

        String instrumento = (String) param[0];
        Float nota = (Float) param[1];

        List<NameValuePair> valores = new ArrayList<NameValuePair>();
        valores.add(new BasicNameValuePair("instrumento", instrumento));
        valores.add(new BasicNameValuePair("nota",nota.toString()));
        JSONObject json = jsonParser.makeHttpRequest("http://192.168.0.7/reponline/get.php","GET",valores);

        return true;
    }

}
