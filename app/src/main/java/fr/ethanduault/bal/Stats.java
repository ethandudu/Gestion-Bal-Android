package fr.ethanduault.bal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.Statement;

public class Stats extends AppCompatActivity {

    private String total_part_nb;
    private Integer total_part;
    private String total_abs_nb;
    private String total_pres_nb;

    private String total_def_nb;
    private Integer total_def;
    private String def_cancel_nb;
    private String def_payed_nb;
    private String def_auto_nb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        getSupportActionBar().hide();


        TextView total_part_lb = findViewById(R.id.total_part_lb);
        TextView total_abs_lb = findViewById(R.id.total_abs_lb);
        TextView total_pres_lb = findViewById(R.id.total_pres_lb);


        TextView total_def_lb = findViewById(R.id.total_def_lb);
        TextView def_cancel_lb = findViewById(R.id.def_cancel_lb);
        TextView def_payed_lb = findViewById(R.id.def_payed_lb);
        TextView def_auto_lb = findViewById(R.id.def_auto_lb);

        loadbdd();

        total_part_lb.setText(total_part_nb);
        total_abs_lb.setText(total_abs_nb);
        total_pres_lb.setText(total_pres_nb);


        total_def_lb.setText(total_def_nb);
        def_cancel_lb.setText(def_cancel_nb);
        def_payed_lb.setText(def_payed_nb);
        def_auto_lb.setText(def_auto_nb);

    }
    private void loadbdd(){

        // Nombre participants
        try {
            Fonctions fonc = new Fonctions();
            Statement st = fonc.connexionSQLBDD();

            //System.out.println(hashcode);
            String SQL = "SELECT count(*) FROM participants WHERE cancel = 'false'";

            //System.out.println(SQL);
            final ResultSet rs = st.executeQuery(SQL);
            rs.next();
            int temp;
            temp = rs.getInt(1);
            total_part_nb = Integer.toString(temp);
            total_part = temp;

        }catch(Exception e){
            e.printStackTrace();
            total_part_nb = "E";
        }

        // Nombre absents
        try {
            Fonctions fonc = new Fonctions();
            Statement st = fonc.connexionSQLBDD();

            //System.out.println(hashcode);
            String SQL = "SELECT count(*) FROM participants WHERE validated = 'Non'";

            //System.out.println(SQL);
            final ResultSet rs = st.executeQuery(SQL);
            rs.next();
            int temp;
            float t2 = rs.getFloat(1);
            temp = rs.getInt(1);
            double pc;
            pc = (t2 / total_part);
            pc = pc*100;
            pc = Math.round(pc);
            total_abs_nb = temp + " / " + Double.toString(pc) + "%";

        }catch(Exception e){
            e.printStackTrace();
            total_abs_nb = "E";
        }

        // Nombre présents
        try {
            Fonctions fonc = new Fonctions();
            Statement st = fonc.connexionSQLBDD();

            //System.out.println(hashcode);
            String SQL = "SELECT count(*) FROM participants WHERE validated = 'Oui'";

            //System.out.println(SQL);
            final ResultSet rs = st.executeQuery(SQL);
            rs.next();
            int temp;
            float t2 = rs.getFloat(1);
            temp = rs.getInt(1);
            double pc;
            pc = (t2 / total_part);
            pc = pc*100;
            pc = Math.round(pc);
            total_pres_nb = temp + " / " + Double.toString(pc) + "%";

        }catch(Exception e){
            e.printStackTrace();
            total_pres_nb = "E";
        }

        // Total défauts
        try {
            Fonctions fonc = new Fonctions();
            Statement st = fonc.connexionSQLBDD();

            //System.out.println(hashcode);
            String SQL = "SELECT count(*) FROM participants WHERE cancel = 'True' OR payed = 'Non' OR autorisation = 'Non'";

            //System.out.println(SQL);
            final ResultSet rs = st.executeQuery(SQL);
            rs.next();
            int temp;
            total_def = rs.getInt(1);
            float t2 = rs.getFloat(1);
            temp = rs.getInt(1);
            double pc;
            pc = (t2 / total_part);
            pc = pc*100;
            pc = Math.round(pc);
            total_def_nb = temp + " / " + Double.toString(pc) + "%";

        }catch(Exception e){
            e.printStackTrace();
            total_def_nb = "E";
        }

        //  annulations
        try {
            Fonctions fonc = new Fonctions();
            Statement st = fonc.connexionSQLBDD();

            //System.out.println(hashcode);
            String SQL = "SELECT count(*) FROM participants WHERE cancel = 'True'";

            //System.out.println(SQL);
            final ResultSet rs = st.executeQuery(SQL);
            rs.next();
            int temp;
            float t2 = rs.getFloat(1);
            temp = rs.getInt(1);
            double pc;
            pc = (t2 / total_def);
            pc = pc*100;
            pc = Math.round(pc);
            def_cancel_nb = temp + " / " + Double.toString(pc) + "%";

        }catch(Exception e){
            e.printStackTrace();
            def_cancel_nb = "E";
        }

        // Paiement
        try {
            Fonctions fonc = new Fonctions();
            Statement st = fonc.connexionSQLBDD();

            //System.out.println(hashcode);
            String SQL = "SELECT count(*) FROM participants WHERE payed = 'Non'";

            //System.out.println(SQL);
            final ResultSet rs = st.executeQuery(SQL);
            rs.next();
            int temp;
            float t2 = rs.getFloat(1);
            temp = rs.getInt(1);
            double pc;
            pc = (t2 / total_def);
            pc = pc*100;
            pc = Math.round(pc);
            def_payed_nb = temp + " / " + Double.toString(pc) + "%";

        }catch(Exception e){
            e.printStackTrace();
            def_payed_nb="E";
        }

        // Autorisations
        try {
            Fonctions fonc = new Fonctions();
            Statement st = fonc.connexionSQLBDD();

            //System.out.println(hashcode);
            String SQL = "SELECT count(*) FROM participants WHERE autorisation = 'Non'";

            //System.out.println(SQL);
            final ResultSet rs = st.executeQuery(SQL);
            rs.next();
            int temp;
            float t2 = rs.getFloat(1);
            temp = rs.getInt(1);
            double pc;
            pc = (t2 / total_def);
            pc = pc*100;
            pc = Math.round(pc);
            def_auto_nb = temp + " / " + Double.toString(pc) + "%";

        }catch(Exception e){
            e.printStackTrace();
            def_auto_nb = "E";
        }
    }
}