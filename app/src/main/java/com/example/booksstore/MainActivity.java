package com.example.booksstore;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
        /*TextView ID =findViewById(R.id.txtID);
        TextView Name =findViewById(R.id.txtName);
        TextView Avtor =findViewById(R.id.txtAvtor);
        TextView Age =findViewById(R.id.txtAge);
        TextView Publisher =findViewById(R.id.txtPublisher);
        TextView Genre =findViewById(R.id.txtGenre);
         */
        TableLayout Books =findViewById(R.id.tb_books);

        try
        {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connection = connectionHelper.connectionClass();

            if(connection!=null)
            {
                String query = "Select * from Books";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                Books.removeAllViews();
                while (resultSet.next())
                {
                    /* ID.setText(resultSet.getString(1));
                    Name.setText(resultSet.getString(2));
                    Avtor.setText(resultSet.getString(3));
                    Age.setText(resultSet.getString(4));
                    Publisher.setText(resultSet.getString(5));
                    Genre.setText(resultSet.getString(6));
                     */
                    TableRow dbOutputRow = new TableRow(this);
                    dbOutputRow.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);

                    /* TextView outputID = new TextView(this);
                    outputID.setText(resultSet.getString(1));
                    dbOutputRow.addView(outputID); */

                    TextView outputName = new TextView(this);
                    params.weight= 2.0f;
                    outputName.setLayoutParams(params);
                    outputName.setText(resultSet.getString(2));
                    dbOutputRow.addView(outputName);

                    TextView outputAutor = new TextView(this);
                    params.weight= 2.0f;
                    outputAutor.setLayoutParams(params);
                    outputAutor.setText(resultSet.getString(3));
                    dbOutputRow.addView(outputAutor);

                    Button changebtn = new Button(this);
                    params.weight= 1.0f;
                    changebtn.setLayoutParams(params);
                    changebtn.setText("Изменить");
                    changebtn.setBackgroundResource(R.drawable.gradient_btn);
                    changebtn.setTextColor(getResources().getColor(R.color.white));
                    changebtn.setId(Integer.parseInt(resultSet.getString(1)));
                    changebtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            EditText nameEdit = findViewById(R.id.NameTxt);
                            EditText authorEdit = findViewById(R.id.AuthorTxt);
                            EditText publisherEdit = findViewById(R.id.PublisherTxt);
                            EditText ageEdit = findViewById(R.id.AgeTxt);
                            EditText genreEdit = findViewById(R.id.GenreTxt);
                            Button changeBTN = findViewById(R.id.Change);
                            nameEdit.setVisibility(View.VISIBLE);
                            authorEdit.setVisibility(View.VISIBLE);
                            publisherEdit.setVisibility(View.VISIBLE);
                            ageEdit.setVisibility(View.VISIBLE);
                            genreEdit.setVisibility(View.VISIBLE);
                            Button refreshbtn = findViewById(R.id.Refresh);
                            Button addbtn = findViewById(R.id.add);
                            refreshbtn.setVisibility(View.INVISIBLE);
                            addbtn.setVisibility(View.INVISIBLE);
                            changeBTN.setVisibility(View.VISIBLE);
                            try {
                                Books.removeAllViews();
                                String query = "Select * from Books where ID = '"+changebtn.getId()+"'";
                                ResultSet resultSet1 = statement.executeQuery(query);
                                while (resultSet1.next()) {
                                    changeBTN.setId(Integer.parseInt(resultSet1.getString(1)));
                                    nameEdit.setText(resultSet1.getString(2));
                                    authorEdit.setText(resultSet1.getString(3));
                                    ageEdit.setText(resultSet1.getString(4));
                                    publisherEdit.setText(resultSet1.getString(5));
                                    genreEdit.setText(resultSet1.getString(6));
                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            changeBTN.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                      try {
                                          String query = "UPDATE Books SET Name = '" + nameEdit.getText() + "', Author = '" + authorEdit.getText() + "', Year = '" + ageEdit.getText() + "', Publisher = '" + publisherEdit.getText() + "', Genre = '" + genreEdit + "' where ID = '" + changeBTN.getId() + "'";
                                          statement.executeUpdate(query);

                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                    nameEdit.setVisibility(View.INVISIBLE);
                                    authorEdit.setVisibility(View.INVISIBLE);
                                    publisherEdit.setVisibility(View.INVISIBLE);
                                    ageEdit.setVisibility(View.INVISIBLE);
                                    genreEdit.setVisibility(View.INVISIBLE);
                                    changeBTN.setVisibility(View.INVISIBLE);
                                    refreshbtn.setVisibility(View.VISIBLE);
                                    addbtn.setVisibility(View.VISIBLE);
                                }
                            });
                        }
                    });
                    dbOutputRow.addView(changebtn);

                    Button deletebtn = new Button(this);
                    params.weight= 1.0f;
                    deletebtn.setLayoutParams(params);
                    deletebtn.setText("Удалить");
                    deletebtn.setBackgroundResource(R.drawable.gradient_btn);
                    deletebtn.setTextColor(getResources().getColor(R.color.white));
                    deletebtn.setId(Integer.parseInt(resultSet.getString(1)));
                    deletebtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                String query = "DELETE from Books where ID = '" + deletebtn.getId() + "'";
                                statement.executeUpdate(query);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    dbOutputRow.addView(deletebtn);

                    /*TextView outputAge = new TextView(this);
                    outputAge.setText(resultSet.getString(4));
                    dbOutputRow.addView(outputAge);

                    TextView outputPublisher = new TextView(this);
                    outputPublisher.setText(resultSet.getString(5));
                    dbOutputRow.addView(outputPublisher);

                    TextView outputGenre = new TextView(this);
                    params.weight= 2.0f;
                    outputGenre.setLayoutParams(params);
                    outputGenre.setText(resultSet.getString(6));
                    dbOutputRow.addView(outputGenre);*/

                    Books.addView(dbOutputRow);
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

    public void gotoAddPage(View v)
    {
        startActivity(new Intent(this, Bookcreate.class));
    }

    public void changeBook(View v)
    {

    }

}