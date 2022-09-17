package com.example.booksstore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    Connection connection;
    String connectionresult = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void GetTextFromSql(View v)
    {
        TextView ID =findViewById(R.id.txtID);
        TextView Name =findViewById(R.id.txtName);
        TextView Avtor =findViewById(R.id.txtAvtor);
        TextView Age =findViewById(R.id.txtAge);
        TextView Publisher =findViewById(R.id.txtPublisher);
        TextView Genre =findViewById(R.id.txtGenre);

        try
        {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connection = connectionHelper.connectionClass();

            if(connection!=null)
            {
                String query = "Select * from Books";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next())
                {
                    ID.setText(resultSet.getString(1));
                    Name.setText(resultSet.getString(2));
                    Avtor.setText(resultSet.getString(3));
                    Age.setText(resultSet.getString(4));
                    Publisher.setText(resultSet.getString(5));
                    Genre.setText(resultSet.getString(6));
                }
            }
            else
            {
                connectionresult = "Check Connection";
            }
        }
        catch (Exception ex)
        {

        }
    }
}