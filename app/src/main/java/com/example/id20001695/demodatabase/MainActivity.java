package com.example.id20001695.demodatabase;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnGetTasks;
    TextView tvResults;
    EditText etName, etDate;
    ListView lv;
    ArrayList<Task> al;
    ArrayAdapter<Task> aa;
    boolean ascTrue = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.buttonInsert);
        btnGetTasks = findViewById(R.id.buttonGetTasks);
        tvResults = findViewById(R.id.textViewResults);
        etName = findViewById(R.id.editTextName);
        etDate = findViewById(R.id.editTextDate);
        lv = findViewById(R.id.llstViewResults);

        al = new ArrayList<>();
        aa = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, al);
        lv.setAdapter(aa);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(MainActivity.this);
                dbh.insertTask(etName.getText().toString(), etDate.getText().toString());
            }
        });

        btnGetTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(MainActivity.this);
                ArrayList<String> tasks = dbh.getTaskContent();
                dbh.close();

                String text = "";
                for (int i = 0; i < tasks.size(); i++) {
                    text += i + ". " + tasks.get(i) + "\n";
                }
                tvResults.setText(text);

                al.clear();
                al.addAll(dbh.getTasks(ascTrue));
                ascTrue = !ascTrue;
                aa.notifyDataSetChanged();
            }
        });
    }

}