package com.example.booksstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.Statement;

public class Bookcreate extends AppCompatActivity {

    Connection connection;
    String connectionresult = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookcreate);
    }

    public void addClick(View v){
        EditText nametx = findViewById(R.id.nametxt);
        EditText authortx = findViewById(R.id.authortxt);
        EditText agetx = findViewById(R.id.agetxt);
        EditText publishertx = findViewById(R.id.publishertxt);
        EditText genretx = findViewById(R.id.genretxt);
        String NameT = nametx.getText().toString();
        String AuthorT = authortx.getText().toString();
        String AgeT = agetx.getText().toString();
        String PublisherT = publishertx.getText().toString();
        String GenreT = genretx.getText().toString();


        try
        {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connection = connectionHelper.connectionClass();
            if(connection!=null) {
                String query = "INSERT INTO Books (Name,Author,Year,Publisher,Genre) VALUES ('" + nametx.getText() + "','" + authortx.getText() + "','" + agetx.getText() + "','" + publishertx.getText() + "','" + genretx.getText() + "')";
                Statement statement = connection.createStatement();
                statement.executeUpdate(query);
                startActivity(new Intent(this, MainActivity.class));
            }
            else
            {
                connectionresult = "Check Connection";
            }
        }
        catch (Exception ex)
        {
            Toast.makeText(Bookcreate.this,"Что-то пошло не так", Toast.LENGTH_LONG).show();
        }
    }
}