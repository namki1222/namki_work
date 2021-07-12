package com.example.post;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button nt1,nt2;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText[] login = new EditText[5];
        nt2 = (Button) findViewById(R.id.button);




        nt1 = (Button) findViewById(R.id.register_button);
        login[0] = (EditText) findViewById(R.id.id);
        login[1] = (EditText) findViewById(R.id.pwd);
        nt1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),register.class);
                startActivity(intent);
            }

        });
        nt2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String[] st = new String[4];
                for(int a =0;a<4;a++){
                    st[a]=null;
                }

                st[0] = login[0].getText().toString();
                st[1] = login[1].getText().toString();

                new Thread() {
                    public void run() {
                        ServerOperation.post(st,"http://192.168.0.84:92/login.php");
                    }
                }.start();
            }

        });
    }
}



