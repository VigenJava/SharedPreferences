package com.example.vigen.sharedpreferences;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    EditText etText;
    Button save,load;
    SharedPreferences sPref;

    final  String SAVED_TEXT="saved_text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etText = (EditText) findViewById(R.id.etText);
        save = (Button) findViewById(R.id.save);
        save.setOnClickListener( this);
        load = (Button) findViewById(R.id.load);
        load.setOnClickListener( this);
        loadText();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.save:
                saveText();
                break;
            case R.id.load:
                loadText();
                break;
            default:break;
        }


    }



    private void saveText() {

        sPref=getSharedPreferences("MyPref",MODE_PRIVATE);
        SharedPreferences.Editor ed=sPref.edit();
        ed.putString(SAVED_TEXT,etText.getText().toString());
        ed.commit();
        Toast.makeText(MainActivity.this,"Text saved",Toast.LENGTH_SHORT).show();
    }
    private void loadText() {
        
        sPref=getPreferences(MODE_PRIVATE);
        String savedText=sPref.getString(SAVED_TEXT,"");
        etText.setText(savedText);
        Toast.makeText(MainActivity.this,"Load Text",Toast.LENGTH_SHORT).show();




    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveText();
    }
}
