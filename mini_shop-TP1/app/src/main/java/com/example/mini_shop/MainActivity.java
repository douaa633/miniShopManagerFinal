package com.example.listedespays;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main),
                (v, insets) -> {

                    Insets systemBars =
                            insets.getInsets(WindowInsetsCompat.Type.systemBars());

                    v.setPadding(
                            systemBars.left,
                            systemBars.top,
                            systemBars.right,
                            systemBars.bottom
                    );

                    return insets;
                }
        );

        ListView listViewPays = findViewById(R.id.listViewPays);

        String[] countries = getResources().getStringArray(R.array.countries);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                countries
        );

        listViewPays.setAdapter(adapter);

        listViewPays.setOnItemClickListener((parent, view, position, id) -> {

            String pays = countries[position];

            Toast.makeText(
                    this,
                    "Pays sélectionné : " + pays,
                    Toast.LENGTH_SHORT
            ).show();
        });
    }
}