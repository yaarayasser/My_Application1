package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    EditText txtIP;
    EditText txtPort;
    EditText txtMSG;
    Button button1;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtIP = (EditText) findViewById(R.id.txtIP);
        txtPort = (EditText) findViewById(R.id.txtPort);
        txtMSG = (EditText) findViewById(R.id.txtMSG);
        button1 = (Button) findViewById(R.id.button1);
        TextView txtView = (TextView) findViewById((R.id.textView10));

        button1.setOnClickListener(new View.OnClickListener() {



            public void onClick(View view) {

                Thread t = new Thread(new Runnable(){
                    @Override
                    public void run() {

                        try {
                            Socket s = new Socket(txtIP.getText().toString(),Integer.parseInt(txtPort.getText().toString()));
                            DataOutputStream x = new DataOutputStream(s.getOutputStream());
                            x.writeUTF(txtMSG.getText().toString());
                            DataInputStream in =new DataInputStream(s.getInputStream());
                            String str = in.readUTF();

                            txtView.setText(str);


                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                });
                t.start();
            }
        });




    }
}