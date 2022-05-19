package fr.ethanduault.bal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class List extends AppCompatActivity {
    private Integer totalpart;

    private static String codescan="";
    private static EditText name, firstname;

    public static String getCodescan(){
        return codescan;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_list);
        getSupportActionBar().hide();

        Button btn_search = findViewById(R.id.btn_search);
        name = (EditText) findViewById(R.id.name);
        firstname = (EditText) findViewById(R.id.firstname);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search();
            }
        });
    }

    private void loadbdd(){
        try {
            Fonctions fonc = new Fonctions();
            Statement st = fonc.connexionSQLBDD();

            String s = '"' +name.getText().toString() + '"'+ " AND firstname=" + '"' + firstname.getText().toString() + '"';
            String SQL = "SELECT count(*) FROM participants WHERE name="+s;
            System.out.println(SQL);

            final ResultSet rs = st.executeQuery(SQL);
            rs.next();
            totalpart = rs.getInt(1);
            
            if (totalpart == 1) {

                String SQL2 = "SELECT * FROM participants WHERE name=" + s;

                Fonctions fonc2 = new Fonctions();
                Statement st2 = fonc2.connexionSQLBDD();
                ResultSet rs2 = st2.executeQuery(SQL2);
                rs2.next();
                codescan = rs2.getString("hash");
                NextActivity();

            }else{
                Toast.makeText(this, "Aucun participant trouvé", Toast.LENGTH_SHORT).show();
                }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private void NextActivity(){
        Intent intent = new Intent(List.this, Search.class);
        startActivity(intent);
        finish();
    }

    private void search(){
        Toast.makeText(this, "Recherche ...", Toast.LENGTH_LONG).show();
        loadbdd();
    }
}