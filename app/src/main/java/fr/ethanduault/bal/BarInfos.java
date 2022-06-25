package fr.ethanduault.bal;

import androidx.appcompat.app.AppCompatActivity;

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

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;

public class BarInfos extends AppCompatActivity {

    // variables SQL
    private String name = "";
    private String firstname = "";
    private String classe = "";
    private Integer drinks = 0;
    private Integer snacks = 0;
    private final String hashcode = BarQR.getCodescan();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_bar_infos);
        Objects.requireNonNull(getSupportActionBar()).hide();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        final MediaPlayer oksound = MediaPlayer.create(this, R.raw.ok);
        final MediaPlayer nosound = MediaPlayer.create(this, R.raw.nope);
        final MediaPlayer warnsound = MediaPlayer.create(this, R.raw.warn);

        TextView hash = findViewById(R.id.hash);
        TextView name_lb = findViewById(R.id.name_lb);
        TextView firstname_lb = findViewById(R.id.firstname_lb);
        TextView classe_lb = findViewById(R.id.classe_lb);
        TextView snacks_lb = findViewById(R.id.snack);
        TextView drinks_lb = findViewById(R.id.drink);

        Button btn_scan = findViewById(R.id.btn_scan);
        Button btn_drink = findViewById(R.id.btn_drink);
        Button btn_snack = findViewById(R.id.btn_snack);

        loadbdd();

        name_lb.setText(name);
        firstname_lb.setText(firstname);
        classe_lb.setText(classe);
        drinks_lb.setText(drinks.toString());
        snacks_lb.setText(snacks.toString());


        // MISE EN FORME CONTEXTUELLE
        // Snacks
        if (snacks.equals(0)) {
            //snacks_lb.setTextColor(Color.RED);
            warnsound.start();
        } else {
            oksound.start();
            //snacks_lb.setTextColor(Color.GREEN);
        }

        if (drinks.equals(0)) {
            //drinks_lb.setTextColor(Color.RED);
            warnsound.start();
        } else {
            oksound.start();
            //drinks_lb.setTextColor(Color.GREEN);
        }


        btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BarInfos.this, BarQR.class); //QRCodeScan.class
                startActivity(intent);
                finish();
            }
        });

        btn_drink.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                but_drink();
            }
        });

        btn_snack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                but_snack();
            }
        });

        try {
            hash.setText(hashcode);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void loadbdd() {

        try {
            Fonctions fonc = new Fonctions();
            Statement st = fonc.connexionSQLBDD();

            String SQL = "SELECT count(*) FROM participants WHERE hash = '" + hashcode + "'";

            final ResultSet rs = st.executeQuery(SQL);
            rs.next();

            if (rs.getInt(1) == 0) {
                Toast.makeText(this, "❌ QRCode invalide", Toast.LENGTH_LONG).show();
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {

                    }
                };
                new Handler().postDelayed(runnable, 3000);
                Intent intent = new Intent(BarInfos.this, BarQR.class);
                startActivity(intent);
                finish();
            } else {
                String SQL2 = "SELECT * FROM participants WHERE hash = '" + hashcode + "'";
                Fonctions fonc2 = new Fonctions();
                Statement st2 = fonc2.connexionSQLBDD();
                final ResultSet rs2 = st2.executeQuery(SQL2);
                rs2.next();
                name = rs2.getString("name");
                firstname = rs2.getString("firstname");
                classe = rs2.getString("classe");
                drinks = rs2.getInt("drinks");
                snacks = rs2.getInt("snacks");
            }


        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "⚠ Erreur", Toast.LENGTH_LONG).show();
        }
    }

    private void but_drink() {
        try {
            Integer drinks_up;
            drinks_up = drinks - 1;
            Fonctions fonc = new Fonctions();
            Statement st = fonc.connexionSQLBDD();
            String SQL = "UPDATE `participants` SET `drinks`='" + drinks_up + "' WHERE `hash`='" + hashcode + "'";
            st.executeUpdate(SQL);

            Toast.makeText(this, "✅ Boisson consommée", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(BarInfos.this, BarQR.class);
            startActivity(intent);
            finish();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "⚠ Erreur, boisson non validée", Toast.LENGTH_LONG).show();
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    private void but_snack() {
        try {
            Integer snacks_up;
            snacks_up = snacks - 1;
            Fonctions fonc = new Fonctions();
            Statement st = fonc.connexionSQLBDD();
            String SQL = "UPDATE `participants` SET `snacks`='" + snacks_up + "' WHERE `hash`='" + hashcode + "'";
            st.executeUpdate(SQL);

            Toast.makeText(this, "✅ Snack consommé", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(BarInfos.this, BarQR.class);
            startActivity(intent);
            finish();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "⚠ Erreur, snack non validé", Toast.LENGTH_LONG).show();
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}