package fr.ethanduault.bal;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Base64;

public class Connexion extends AppCompatActivity {

    private static EditText user, pass;
    private String hashed;

    private static String username = "";
    private static String userfirstname = "";

    public static String getusername(){
        return username;
    }
    public static String getuserfirstname() {
        return userfirstname;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_connexion);
        getSupportActionBar().hide();

        user = (EditText) findViewById(R.id.user);
        pass = (EditText) findViewById(R.id.pass);
        hashed = "";

        Button btn_send = (Button) findViewById(R.id.btn_send);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connexion();
            }
        });

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }
    public static String toHexString(byte[] hash)
    {
        // Convert byte array into signum representation
        BigInteger number = new BigInteger(1, hash);

        // Convert message digest into hex value
        StringBuilder hexString = new StringBuilder(number.toString(16));

        // Pad with leading zeros
        while (hexString.length() < 64)
        {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }

    public static byte[] getSHA(String input) throws NoSuchAlgorithmException
    {
        // Static getInstance method is called with hashing SHA
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // digest() method called
        // to calculate message digest of an input
        // and return array of byte
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    private void connexion(){

        Toast.makeText(this, "Connexion ...", Toast.LENGTH_SHORT).show();
        try {
            Fonctions fonc = new Fonctions();
            Statement st = fonc.connexionSQLBDD();

            String SQL = "SELECT `passwd` FROM members WHERE user = '"+user.getText().toString()+"'";

            final ResultSet rs = st.executeQuery(SQL);
            rs.next();

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            hashed = pass.getText().toString();
            hashed = toHexString(getSHA(hashed));

            if (rs.getString(1).equals(hashed.toString())){

                Fonctions fonc2 = new Fonctions();
                Statement st2 = fonc2.connexionSQLBDD();
                String SQL2 = "SELECT * FROM members where user = '"+user.getText().toString()+"'";
                final ResultSet rs2 = st2.executeQuery(SQL2);
                rs2.next();
                username = rs2.getString("name");
                userfirstname = rs2.getString("firstname");
                Toast.makeText(this, "Bienvenue "+userfirstname + " " + username, Toast.LENGTH_SHORT).show();

                NextActivity();
            }else{
               Toast.makeText(this, "❌ Identifiant/Mot de passe invalide", Toast.LENGTH_SHORT).show();
            }

        }catch(Exception e){
            e.printStackTrace();
            Toast.makeText(this, "⚠ Erreur", Toast.LENGTH_LONG).show();
        }
    }

    private void NextActivity(){
        Intent intent = new Intent(Connexion.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}