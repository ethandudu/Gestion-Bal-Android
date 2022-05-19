package fr.ethanduault.bal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        Button btn_scan = findViewById(R.id.btn_scan);
        Button btn_list = findViewById(R.id.btn_list);
        Button btn_stats = findViewById(R.id.btn_stats);
        Button btn_cloak = findViewById(R.id.btn_cloak);

        btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, QRCodeScan.class); //QRCodeScan.class
                startActivity(intent);
            }
        });

        btn_stats.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                ldtxt();
                Intent intent = new Intent(MainActivity.this, Stats.class);
                startActivity(intent);
            }
        });

        btn_list.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, List.class);
                startActivity(intent);
            }
        });

        btn_cloak.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                fct_dis();
                //Intent intent = new Intent(MainActivity.this, QRCodeScan.class);
                //startActivity(intent);
            }
        });
    }
    private void fct_dis(){
        Toast.makeText(this, "Fonction non activée", Toast.LENGTH_SHORT).show();
    }

    private void ldtxt(){
        Toast.makeText(this, "Chargement ...", Toast.LENGTH_SHORT).show();
    }
}