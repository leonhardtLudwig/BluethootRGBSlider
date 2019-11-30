package com.example.bluetooth1;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import java.io.IOException;
import java.io.OutputStreamWriter;

public class SubActivity extends AppCompatActivity {

    private BluetoothAdapter bluetoothAdapter;
    private final int REQUEST_ENABLE_BT = 1;

    public static String listElement;
    public static BluetoothDevice targetDevice;
    public static BluetoothSocket btSocket;
    public static OutputStreamWriter writer;
    public static int ConnessioneOK=-1;

    private static final int INTENT_CODE = 1;

    LinearLayout llCerchioRGB;
    SeekBar sbTrasparenzaRed;
    SeekBar SbTrasparenzaGreen;
    SeekBar SbTrasparenzaBlue;
    int red = 0;
    int green = 0;
    int blue = 0;
    String colore = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        llCerchioRGB = (LinearLayout) findViewById(R.id.llCerchioRGB);
        sbTrasparenzaRed = (SeekBar) findViewById(R.id.sbTrasparenzaRed);
        sbTrasparenzaRed.setOnSeekBarChangeListener(seekBarRed);
        SbTrasparenzaGreen = (SeekBar) findViewById(R.id.sbTrasparenzaGreen);
        SbTrasparenzaGreen.setOnSeekBarChangeListener(seekBarGreen);
        SbTrasparenzaBlue = (SeekBar) findViewById(R.id.sbTrasparenzaBlue);
        SbTrasparenzaBlue.setOnSeekBarChangeListener(seekBarBlue);
    }

    private OnSeekBarChangeListener seekBarRed = new OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
            GradientDrawable myCircle = (GradientDrawable) llCerchioRGB.getBackground();


        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            GradientDrawable myCircle = (GradientDrawable) llCerchioRGB.getBackground();
            mostraColori(myCircle);
        }
    };

    private OnSeekBarChangeListener seekBarGreen = new OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {


        }


        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            GradientDrawable myCircle = (GradientDrawable) llCerchioRGB.getBackground();
            mostraColori(myCircle);
        }
    };
    private OnSeekBarChangeListener seekBarBlue = new OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {


        }


        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            GradientDrawable myCircle = (GradientDrawable) llCerchioRGB.getBackground();
            mostraColori(myCircle);
        }
    };
    public void mostraColori(GradientDrawable myCircle){
        int color = Color.rgb(sbTrasparenzaRed.getProgress(),SbTrasparenzaGreen.getProgress(),SbTrasparenzaBlue.getProgress());
        String toSend = Integer.toHexString(sbTrasparenzaRed.getProgress())
                +Integer.toHexString(SbTrasparenzaGreen.getProgress())
                +Integer.toHexString(SbTrasparenzaBlue.getProgress());
        toSend=String.format("%02x%02x%02x",sbTrasparenzaRed.getProgress(),SbTrasparenzaGreen.getProgress(),SbTrasparenzaBlue.getProgress());

        myCircle.setColor(color);

        try {
            writer = new OutputStreamWriter(btSocket.getOutputStream());
            String trasmettere= Integer.toString(color);

            //writer.write(trasmettere);
            writer.write(toSend);

            Log.d("color",toSend);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
