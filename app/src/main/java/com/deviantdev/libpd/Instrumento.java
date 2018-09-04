package com.deviantdev.libpd;

/**
 * Created by Eduardo on 23/07/2015.
 */
public interface Instrumento {

    public void initGui();
    public void tocar(String instrumento, Float nota);
    public void tocarSolo(String instrumento, Float nota);
}
