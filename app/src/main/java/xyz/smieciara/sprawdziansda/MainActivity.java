package xyz.smieciara.sprawdziansda;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final ArrayList<String> books = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        books.add("test, test");
        ListView listView = findViewById(R.id.bookList);
        ArrayAdapter arrayAdapter = new ArrayAdapter(getBaseContext(), android.R.layout.simple_list_item_1, books);
        listView.setAdapter(arrayAdapter);
        Button submitButton = findViewById(R.id.submitButton);
        EditText titleText = findViewById(R.id.titleText);
        EditText authorText = findViewById(R.id.authorText);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(titleText.getText().toString() == null || titleText.getText().toString().equals("") || authorText.getText().toString() == null || authorText.getText().toString().equals("")){
                    Toast.makeText(getBaseContext(), "Enter data", Toast.LENGTH_SHORT).show();
                    return;
                }
                String titleString = titleText.getText().toString().toString();
                String authorString = authorText.getText().toString().toString();
                books.add(String.format("%s, %s", titleString, authorString));
                arrayAdapter.notifyDataSetChanged();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), String.format("You clicked: %s", books.get((int) id)), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getBaseContext(), DetailedView.class);
                intent.putExtra("details", books.get((int) id));
                startActivity(intent);
            }
        });
    }
}