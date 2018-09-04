package com.deviantdev.libpd;

import android.app.Activity;
import android.content.Intent;
import android.net.wifi.WifiManager;
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
public class ShakeActivity extends Activity implements Instrumento {

    private PdUiDispatcher dispatcher;
    Button nota;
    Rede rede;
    int opcao;

    private void init_pd() throws IOException {
        // Configure the audio glue
        int sampleRate = AudioParameters.suggestSampleRate();
        PdAudio.initAudio(sampleRate, 0, 2, 8, true);
        // Create and install the dispatcher
        dispatcher = new PdUiDispatcher();
        PdBase.setReceiver(dispatcher);
    }

    private void load_pd_patch() throws IOException {
        File dir = getFilesDir();
        IoUtils.extractZipResource(getResources().openRawResource(R.raw.pdpatch), dir, true);
        File patchFile = new File( dir, "orquestra.pd" );
        PdBase.openPatch(patchFile.getAbsolutePath());
    }

    @Override
    public void initGui() {

        Intent myIntent = getIntent();
        opcao = myIntent.getIntExtra("opcao",0);

        if (opcao == 2) {
            rede = new Rede(this, 6789);
            rede.receiveMenssage();
        }

        this.nota = (Button) findViewById( R.id.nota_button);
        nota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notaButton(rede);
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

    public  void  notaButton(Rede rede){
        if (opcao == 1){
            tocarSolo("shake",1f);
        }
        else if (opcao == 2){
            try {
                rede.sendMessage("shake/1");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            //implementar o centralizado
        }
    }

    protected void onCreate( Bundle savedInstanceState ) {

        super.onCreate( savedInstanceState );
        setContentView(R.layout.activity_shake);

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
