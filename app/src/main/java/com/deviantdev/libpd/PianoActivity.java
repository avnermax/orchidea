package com.deviantdev.libpd;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
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
public class PianoActivity extends ActionBarActivity implements Instrumento {

    private PdUiDispatcher dispatcher;
    Button nota;
    Button nota1;
    Button nota2;
    Button nota3;
    Button nota4;
    Button nota5;
    Button nota6;
    Button nota7;
    Button nota8;
    Button nota9;
    Button nota10;
    Button nota11;
    Button nota12;
    Button nota13;
    int opcao;
    Rede rede;

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

        if (opcao == 2) {
            rede = new Rede(this, 6789);
            rede.receiveMenssage();
        }

        this.nota = (Button)findViewById(R.id.C_btn);
        nota.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    notaButton(rede);
                }else if (event.getAction() == MotionEvent.ACTION_UP) {
                    tocarSolo("piano",0f);
                }
                return true;
            }
        });

        this.nota1 = (Button)findViewById(R.id.Csus_btn);
        nota1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v1, MotionEvent event1) {
                if (event1.getAction() == MotionEvent.ACTION_DOWN) {
                    nota1Button(rede);
                }else if (event1.getAction() == MotionEvent.ACTION_UP) {
                    tocarSolo("piano",0f);
                }
                return true;
            }
        });

        this.nota2 = (Button)findViewById(R.id.D_btn);
        nota2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v2, MotionEvent event2) {
                if (event2.getAction() == MotionEvent.ACTION_DOWN) {
                    nota2Button(rede);
                }else if (event2.getAction() == MotionEvent.ACTION_UP) {
                    tocarSolo("piano",0f);
                }
                return true;
            }
        });

        this.nota3 = (Button)findViewById(R.id.Dsus_btn);
        nota3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v3, MotionEvent event3) {
                if (event3.getAction() == MotionEvent.ACTION_DOWN) {
                    nota3Button(rede);
                }else if (event3.getAction() == MotionEvent.ACTION_UP) {
                    tocarSolo("piano",0f);
                }
                return true;
            }
        });

        this.nota4 = (Button)findViewById(R.id.E_btn);
        nota4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v4, MotionEvent event4) {
                if (event4.getAction() == MotionEvent.ACTION_DOWN) {
                    nota4Button(rede);
                }else if (event4.getAction() == MotionEvent.ACTION_UP) {
                    tocarSolo("piano",0f);
                }
                return true;
            }
        });

        this.nota5 = (Button)findViewById(R.id.F_btn);
        nota5.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v5, MotionEvent event5) {
                if (event5.getAction() == MotionEvent.ACTION_DOWN) {
                    nota5Button(rede);
                }else if (event5.getAction() == MotionEvent.ACTION_UP) {
                    tocarSolo("piano",0f);
                }
                return true;
            }
        });

        this.nota6 = (Button)findViewById(R.id.Fsus_btn);
        nota6.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v6, MotionEvent event6) {
                if (event6.getAction() == MotionEvent.ACTION_DOWN) {
                    nota6Button(rede);
                }else if (event6.getAction() == MotionEvent.ACTION_UP) {
                    tocarSolo("piano",0f);
                }
                return true;
            }
        });

        this.nota7 = (Button)findViewById(R.id.G_btn);
        nota7.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v7, MotionEvent event7) {
                if (event7.getAction() == MotionEvent.ACTION_DOWN) {
                    nota7Button(rede);
                }else if (event7.getAction() == MotionEvent.ACTION_UP) {
                    tocarSolo("piano",0f);
                }
                return true;
            }
        });

        this.nota8 = (Button)findViewById(R.id.Gsus_btn);
        nota8.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v8, MotionEvent event8) {
                if (event8.getAction() == MotionEvent.ACTION_DOWN) {
                    nota8Button(rede);
                }else if (event8.getAction() == MotionEvent.ACTION_UP) {
                    tocarSolo("piano",0f);
                }
                return true;
            }
        });

        this.nota9 = (Button)findViewById(R.id.A_btn);
        nota9.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v9, MotionEvent event9) {
                if (event9.getAction() == MotionEvent.ACTION_DOWN) {
                    nota9Button(rede);
                }else if (event9.getAction() == MotionEvent.ACTION_UP) {
                    tocarSolo("piano",0f);
                }
                return true;
            }
        });

        this.nota10 = (Button)findViewById(R.id.Asus_btn);
        nota10.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v10, MotionEvent event10) {
                if (event10.getAction() == MotionEvent.ACTION_DOWN) {
                    nota10Button(rede);
                }else if (event10.getAction() == MotionEvent.ACTION_UP) {
                    tocarSolo("piano",0f);
                }
                return true;
            }
        });

        this.nota11 = (Button)findViewById(R.id.B_btn);
        nota11.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v11, MotionEvent event11) {
                if (event11.getAction() == MotionEvent.ACTION_DOWN) {
                    nota11Button(rede);
                }else if (event11.getAction() == MotionEvent.ACTION_UP) {
                    tocarSolo("piano",0f);
                }
                return true;
            }
        });

        this.nota12 = (Button)findViewById(R.id.Cp_btn);
        nota12.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v12, MotionEvent event12) {
                if (event12.getAction() == MotionEvent.ACTION_DOWN) {
                    nota12Button(rede);
                }else if (event12.getAction() == MotionEvent.ACTION_UP) {
                    tocarSolo("piano",0f);
                }
                return true;
            }
        });

        this.nota13 = (Button)findViewById(R.id.Dsusp_btn);
        nota13.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v13, MotionEvent event13) {
                if (event13.getAction() == MotionEvent.ACTION_DOWN) {
                    nota13Button(rede);
                }else if (event13.getAction() == MotionEvent.ACTION_UP) {
                    tocarSolo("piano",0f);
                }
                return true;
            }
        });
    }

    public  void  notaButton(Rede rede){
        if (opcao == 1){
            tocarSolo("piano",60f);
        }
        else if (opcao == 2){
            try {
                rede.sendMessage("piano/60");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            //implementar o centralizado
        }
    }

    public  void  nota1Button(Rede rede){
        if (opcao == 1){
            tocarSolo("piano",61f);
        }
        else if (opcao == 2){
            try {
                rede.sendMessage("piano/61");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            //implementar o centralizado
        }
    }

    public  void  nota2Button(Rede rede){
        if (opcao == 1){
            tocarSolo("piano",62f);
        }
        else if (opcao == 2){
            try {
                rede.sendMessage("piano/62");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            //implementar o centralizado
        }
    }

    public  void  nota3Button(Rede rede){
        if (opcao == 1){
            tocarSolo("piano",63f);
        }
        else if (opcao == 2){
            try {
                rede.sendMessage("piano/63");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            //implementar o centralizado
        }
    }

    public  void  nota4Button(Rede rede){
        if (opcao == 1){
            tocarSolo("piano",64f);
        }
        else if (opcao == 2){
            try {
                rede.sendMessage("piano/64");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            //implementar o centralizado
        }
    }

    public  void  nota5Button(Rede rede){
        if (opcao == 1){
            tocarSolo("piano",65f);
        }
        else if (opcao == 2){
            try {
                rede.sendMessage("piano/65");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            //implementar o centralizado
        }
    }

    public  void  nota6Button(Rede rede){
        if (opcao == 1){
            tocarSolo("piano",66f);
        }
        else if (opcao == 2){
            try {
                rede.sendMessage("piano/66");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            //implementar o centralizado
        }
    }

    public  void  nota7Button(Rede rede){
        if (opcao == 1){
            tocarSolo("piano",67f);
        }
        else if (opcao == 2){
            try {
                rede.sendMessage("piano/67");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            //implementar o centralizado
        }
    }

    public  void  nota8Button(Rede rede){
        if (opcao == 1){
            tocarSolo("piano",68f);
        }
        else if (opcao == 2){
            try {
                rede.sendMessage("piano/68");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            //implementar o centralizado
        }
    }

    public  void  nota9Button(Rede rede){
        if (opcao == 1){
            tocarSolo("piano",69f);
        }
        else if (opcao == 2){
            try {
                rede.sendMessage("piano/69");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            //implementar o centralizado
        }
    }

    public  void  nota10Button(Rede rede){
        if (opcao == 1){
            tocarSolo("piano",70f);
        }
        else if (opcao == 2){
            try {
                rede.sendMessage("piano/70");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            //implementar o centralizado
        }
    }

    public  void  nota11Button(Rede rede){
        if (opcao == 1){
            tocarSolo("piano",71f);
        }
        else if (opcao == 2){
            try {
                rede.sendMessage("piano/71");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            //implementar o centralizado
        }
    }

    public  void  nota12Button(Rede rede){
        if (opcao == 1){
            tocarSolo("piano",72f);
        }
        else if (opcao == 2){
            try {
                rede.sendMessage("piano/72");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            //implementar o centralizado
        }
    }

    public  void  nota13Button(Rede rede){
        if (opcao == 1){
            tocarSolo("piano",73f);
        }
        else if (opcao == 2){
            try {
                rede.sendMessage("piano/73");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            //implementar o centralizado
        }
    }

    @Override
    public void tocar(String instrumento, Float nota) {
        PdBase.sendFloat(instrumento, nota);
        new rodarBackGround(this).execute();
    }

    public void tocarSolo(String instrumento, Float nota) {
        PdBase.sendFloat(instrumento, nota);
    }

    protected void onCreate( Bundle savedInstanceState ) {

        super.onCreate( savedInstanceState );
        setContentView(R.layout.activity_piano);

        try {
            init_pd();
            load_pd_patch();
        } catch ( IOException e ) {
            finish();
        }

        initGui();
        PdAudio.startAudio(this);
    }


    public boolean onCreateOptionsMenu( Menu menu ) {
        getMenuInflater().inflate(R.menu.menu_menu, menu);
        return true;
    }

}
