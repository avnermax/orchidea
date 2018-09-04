package com.deviantdev.libpd;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.puredata.android.io.AudioParameters;
import org.puredata.android.io.PdAudio;
import org.puredata.android.utils.PdUiDispatcher;
import org.puredata.core.PdBase;
import org.puredata.core.utils.IoUtils;

import java.io.File;
import java.io.IOException;

public class MainActivity extends ActionBarActivity {

	Button eButton;
	Button aButton;
	Button dButton;

	private void init_gui() {

		this.eButton = (Button) findViewById( R.id.button );
		this.eButton.setOnTouchListener( new View.OnTouchListener() {
			@Override
			public boolean onTouch( View v, MotionEvent event ) {
				if ( event.getAction() == MotionEvent.ACTION_DOWN ) {
					Intent i = new Intent(getApplicationContext(), MenuActivity.class);
					i.putExtra("opcao",1);
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
					Intent i = new Intent(getApplicationContext(), MenuActivity.class);
					i.putExtra("opcao",2);
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
					Intent i = new Intent(getApplicationContext(), MenuActivity.class);
					i.putExtra("opcao",3);
					startActivity(i);
				}
				return false;
			}
		} );
	}



	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		this.init_gui();
	}

	@Override
	protected void onResume() {
		super.onResume();
		PdAudio.startAudio(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		PdAudio.stopAudio();
	}

	@Override
	public boolean onCreateOptionsMenu( Menu menu ) {
		getMenuInflater().inflate( R.menu.menu_menu, menu );
		return true;
	}

}
