package com.deviantdev.libpd;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

/**
 * Created by Eduardo on 25/07/2015.
 */
public class MenuActivity extends ActionBarActivity {

    Button eButton;
    Button aButton;
    Button dButton;

    int opcao;
    private void init_gui() {

        Intent myIntent = getIntent();
        opcao = myIntent.getIntExtra("opcao",0);

        this.eButton = (Button) findViewById( R.id.button );
        this.eButton.setOnTouchListener( new View.OnTouchListener() {
            @Override
            public boolean onTouch( View v, MotionEvent event ) {
                if ( event.getAction() == MotionEvent.ACTION_DOWN ) {
                    Intent i = new Intent(getApplicationContext(), PianoActivity.class);
                    i.putExtra("opcao",opcao);
                    startActivity(i);
                }
                return false;
            }
        } );
        this.aButton = (Button) findViewById( R.id.button2 );
        this.aButton.setOnTouchListener( new View.OnTouchListener() {
            @Override
            public boolean onTouch( View v, MotionEvent event ) {
                if ( event.getAction() == MotionEvent.ACTION_DOWN ) {
                    Intent i = new Intent(getApplicationContext(), CuicaActivity.class);
                    i.putExtra("opcao",opcao);
                    startActivity(i);
                }
                return false;
            }
        } );
        this.dButton = (Button) findViewById( R.id.button3 );
        this.dButton.setOnTouchListener( new View.OnTouchListener() {
            @Override
            public boolean onTouch( View v, MotionEvent event ) {
                if ( event.getAction() == MotionEvent.ACTION_DOWN ) {
                    Intent i = new Intent(getApplicationContext(), ShakeActivity.class);
                    i.putExtra("opcao",opcao);
                    startActivity(i);
                }
                return false;
            }
        } );
    }



    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        this.init_gui();
    }

    public boolean onCreateOptionsMenu( Menu menu ) {
        getMenuInflater().inflate(R.menu.menu_menu, menu);
        return true;
    }


}
