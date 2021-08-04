package sg.edu.rp.c346.id20025732.p09_ndpthemesongcompilation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {

    EditText etTitle, etSinger, etYear, etID;
    Button btnUpdate, btnDelete, btnCancel;
    RatingBar rb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        etTitle = findViewById(R.id.etname);
        etSinger = findViewById(R.id.etDescription);
        etYear = findViewById(R.id.etYArea);
        etID = findViewById(R.id.etID);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);
        rb = findViewById(R.id.rBarStarNew);

        Intent i = getIntent();
        final Song currentSong = (Song) i.getSerializableExtra("song");
        etID.setText(currentSong.get_id() + "");
        etTitle.setText(currentSong.getTitle());
        etSinger.setText(currentSong.getSingers());
        etYear.setText(currentSong.getYear());
        rb.setRating(currentSong.getStars());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                currentSong.setTitle((etTitle.getText().toString().trim()));
                currentSong.setSingers(etSinger.getText().toString().trim());
                int year = Integer.valueOf(etYear.getText().toString().trim());
                currentSong.setYear(year);

                currentSong.setStars((int) (rb.getRating()));
                int result = dbh.updateSong(currentSong);
                if (result < 0) {
                    Toast.makeText(ThirdActivity.this, "Song updated", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent();
                    setResult(RESULT_OK);
                    finish();
                } else {
                    Toast.makeText(ThirdActivity.this, "Update Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(ThirdActivity.this);

                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to delete " + etTitle + "?");
                myBuilder.setCancelable(false);

                myBuilder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBHelper dbh = new DBHelper(ThirdActivity.this);
                        int result = dbh.deleteSong(currentSong.get_id());
                        finish();
                    }
                });

                myBuilder.setPositiveButton("Cancel",null);

                AlertDialog myDialog = myBuilder.create();
                myDialog.show();

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(ThirdActivity.this);

                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to discard the changes?");
                myBuilder.setCancelable(false);

                myBuilder.setNegativeButton("discard", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                myBuilder.setPositiveButton("Do not discard",null);

                AlertDialog myDialog = myBuilder.create();
                myDialog.show();

            }
        });
    }
}

