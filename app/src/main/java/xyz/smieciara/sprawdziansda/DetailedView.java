package xyz.smieciara.sprawdziansda;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Random;

public class DetailedView extends AppCompatActivity {

    ArrayList<Integer> integerArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detailed_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        integerArrayList.add(R.drawable.img_1);
        integerArrayList.add(R.drawable.img_2);
        integerArrayList.add(R.drawable.img_3);
        integerArrayList.add(R.drawable.img_4);
        integerArrayList.add(R.drawable.img_5);
        integerArrayList.add(R.drawable.img_6);
        integerArrayList.add(R.drawable.img_7);
        Intent intent = getIntent();
        if (intent.getStringExtra("details") == null){
            return;
        }
        String book_details = intent.getStringExtra("details");
        String[] book_details_split = book_details.split(", ");
        TextView authorText = findViewById(R.id.authorTextDetail);
        TextView titleText = findViewById(R.id.titleViewDetail);
        authorText.setText(book_details_split[1]);
        titleText.setText(book_details_split[0]);
        ImageView imageView = findViewById(R.id.imageView2);
        Random random = new Random();
        int random_int = random.nextInt(integerArrayList.size()-1);
        imageView.setImageResource(integerArrayList.get(random_int));
    }
}