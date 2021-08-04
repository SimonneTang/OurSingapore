package sg.edu.rp.c346.id20025732.p09_ndpthemesongcompilation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tvTitle, tvSinger, tvYear, tvStars;
    EditText etTitle, etSinger, etYear;
    Button btnInsert, btnLv;
    RatingBar ratingb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(getTitle().toString() + " ~ " + "Insert Song");


        etTitle = findViewById(R.id.etName);
        etSinger = findViewById(R.id.etDes);
        etYear = findViewById(R.id.etArea);
        btnInsert = findViewById(R.id.btnInsert);
        btnLv = findViewById(R.id.btnLv);
        ratingb = findViewById(R.id.rBarStar);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                DBHelper dbh = new DBHelper(MainActivity.this);
                int stars = getStars();
                String title = etTitle.getText().toString().trim();
                String singer = etSinger.getText().toString().trim();

                String year_str = etYear.getText().toString().trim();
                int year = Integer.valueOf(year_str);
                dbh.insertSongs(title, singer, year_str, stars);
                dbh.close();
                Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_SHORT).show();
                etTitle.setText("");
                etSinger.setText("");
                etYear.setText("");
                return;

            }

        });

        btnLv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });


    }

    private int getStars() {
        int stars = (int) ratingb.getRating();
        return stars;
    }


}
