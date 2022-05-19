package fr.ethanduault.bal;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;

public class Search extends AppCompatActivity {

    // variables SQL
    private String name = "";
    private String firstname = "";
    private String birthdate = "";
    private String classe = "";
    private String payed = "";
    private String autorisation = "";
    private String mail = "";
    private String tel = "";
    private String urgence = "";
    private String age = "";
    private String validated = "";
    private String minor = "";
    private final String hashcode = List.getCodescan();
    private final String username = Connexion.getusername();
    private final String userfirstname = Connexion.getuserfirstname();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_infos);
        Objects.requireNonNull(getSupportActionBar()).hide();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        final MediaPlayer oksound = MediaPlayer.create(this, R.raw.ok);
        final MediaPlayer nosound = MediaPlayer.create(this, R.raw.nope);
        final MediaPlayer warnsound = MediaPlayer.create(this, R.raw.warn);

        TextView hash = findViewById(R.id.hash);
        TextView name_lb = findViewById(R.id.name_lb);
        TextView firstname_lb = findViewById(R.id.firstname_lb);
        TextView birthdate_lb = findViewById(R.id.birthdate_lb);
        TextView classe_lb = findViewById(R.id.classe_lb);
        TextView age_lb = findViewById(R.id.age_lb);
        TextView minor_lb = findViewById(R.id.minor_lb);
        TextView autorisation_lb = findViewById(R.id.autorisation_lb);
        TextView payed_lb = findViewById(R.id.payed_lb);
        TextView validated_lb = findViewById(R.id.validated_lb);
        TextView urgence_lb = findViewById(R.id.urgence_lb);

        Button btn_scan = findViewById(R.id.btn_scan);
        Button btn_ok = findViewById(R.id.btn_ok);
        Button btn_no = findViewById(R.id.btn_no);

        loadbdd();

        name_lb.setText(name);
        firstname_lb.setText(firstname);
        birthdate_lb.setText(birthdate);
        classe_lb.setText(classe);
        age_lb.setText(age);
        minor_lb.setText(minor);
        autorisation_lb.setText(autorisation);
        payed_lb.setText(payed);
        urgence_lb.setText(urgence);

        // MISE EN FORME CONTEXTUELLE
        // Paiement
        if (payed.equals("Oui")){
            payed_lb.setTextColor(Color.GREEN);
        }else{
            payed_lb.setTextColor(Color.RED);
            warnsound.start();
        }

        // Autorisation parentale
        if (autorisation.equals("Exempté(e)")){
            autorisation_lb.setTextColor(Color.GREEN);
        }else if (autorisation.equals("Oui")){
            autorisation_lb.setTextColor(Color.GREEN);
        }else {
            autorisation_lb.setTextColor(Color.RED);
            warnsound.start();
        }

        // Validité scan
        if (validated.equals("Oui")){
            validated_lb.setTextColor(Color.RED);
            validated_lb.setText("Non");
            nosound.start();
        }else{
            validated_lb.setTextColor(Color.GREEN);
            validated_lb.setText("Oui");
            oksound.start();
        }

        btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Search.this, QRCodeScan.class); //QRCodeScan.class
                startActivity(intent);
                finish();
            }
        });

        btn_ok.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                but_ok();
            }
        });

        btn_no.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                but_no();
            }
        });

        try {
            hash.setText(hashcode);
        }catch(Exception e){
            e.printStackTrace();
        }


    }

    private void loadbdd(){

        try {
            Fonctions fonc = new Fonctions();
            Statement st = fonc.connexionSQLBDD();
            String SQL;
            SQL = "SELECT * FROM participants WHERE hash = '"+hashcode+"'";
            final ResultSet rs = st.executeQuery(SQL);
            rs.next();
            name = rs.getString("name");
            firstname = rs.getString("firstname");
            birthdate = rs.getString("birthdate");
            classe = rs.getString("classe");
            age = rs.getString("age");
            minor = rs.getString("minor");
            autorisation = rs.getString("autorisation");
            payed = rs.getString("payed");
            urgence = rs.getString("urgence");
            validated = rs.getString("validated");



        }catch(Exception e){
            e.printStackTrace();
            Toast.makeText(this, "⚠ Erreur", Toast.LENGTH_LONG).show();
        }
    }

    private void but_ok(){
        try {
            Fonctions fonc = new Fonctions();
            Statement st = fonc.connexionSQLBDD();
            String SQL = "UPDATE `participants` SET `validated`='Oui', `validby`='"+userfirstname+" "+username+"' WHERE `hash`='"+hashcode+"'";
            st.executeUpdate(SQL);

            Toast.makeText(this, "✅ Entrée validée", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Search.this, List.class);
            startActivity(intent);
            finish();
        }catch(Exception e){
            e.printStackTrace();
            Toast.makeText(this, "⚠ Erreur", Toast.LENGTH_LONG).show();
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    private void but_no(){
        Toast.makeText(this, "❌ Entrée annulée", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(Search.this, List.class);
        startActivity(intent);
        finish();
    }
}