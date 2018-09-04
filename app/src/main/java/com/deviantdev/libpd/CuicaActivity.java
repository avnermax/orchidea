package com.deviantdev.libpd;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import org.puredata.android.io.AudioParameters;
import org.puredata.android.io.PdAudio;
import org.puredata.android.utils.PdUiDispatcher;
import org.puredata.core.PdBase;
import org.puredata.core.utils.IoUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by Eduardo on 23/07/2015.
 */
public class CuicaActivity extends Activity implements Instrumento {

    private PdUiDispatcher dispatcher;
    Button nota;
    Button nota1;
    int opcao;

    private void init_pd() throws IOException {
        // Configure the audio glue
        int sampleRate = AudioParameters.suggestSampleRate();
        PdAudio.initAudio(sampleRate, 0, 2, 8, true);
        // Create and install the dispatcher
        dispatcher = new PdUiDispatcher();
        PdBase.setReceiver(dispatcher);
    }

    /**
     * Load the prepared PureData patch from the resources (raw).
     * @throws IOException
     */
    private void load_pd_patch() throws IOException {
        File dir = getFilesDir();
        IoUtils.extractZipResource(getResources().openRawResource(R.raw.pdpatch), dir, true);
        File patchFile = new File( dir, "orquestra.pd" );
        PdBase.openPatch(patchFile.getAbsolutePath());
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void initGui() {

        Intent myIntent = getIntent();
        opcao = myIntent.getIntExtra("opcao",0);

        this.nota = (Button) findViewById( R.id.nota_button);
        this.nota.setOnTouchListener( new View.OnTouchListener() {
            @Override
            public boolean onTouch( View v, MotionEvent event ) {
                if ( event.getAction() == MotionEvent.ACTION_DOWN ) {
                    tocar("cuica",1f);
                }
                return false;
            }
        } );

        this.nota1 = (Button) findViewById( R.id.nota1_button);
        this.nota1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    tocar("cuica", 2f);// send volume (0 to 1)
                }
                return false;
            }
        });
    }

    @Override
    public void tocar(String instrumento, Float nota) {
        PdBase.sendFloat(instrumento, nota);
        new rodarBackGround(this).execute();
    }

    public void tocarSolo(String instrumento, Float nota) {
        PdBase.sendFloat(instrumento, nota);
    }

    public void chamaBackGround(String instrumento, float nota){

        new BackGroundCentralizado(this).execute(instrumento,nota);
    }

    protected void onCreate( Bundle savedInstanceState ) {

        super.onCreate( savedInstanceState );
        setContentView(R.layout.activity_cuica);

        try {
            init_pd();
            load_pd_patch();
        } catch ( IOException e ) {
            finish();
        }

        initGui();
        PdAudio.startAudio(this);
    }

}
