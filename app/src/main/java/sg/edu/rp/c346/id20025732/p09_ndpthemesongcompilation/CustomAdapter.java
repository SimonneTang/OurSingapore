package sg.edu.rp.c346.id20025732.p09_ndpthemesongcompilation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    Context parent_context;
    int layout_id;
    ArrayList<Song> SongList;

    public CustomAdapter(@NonNull Context context, int resource,
                         ArrayList<Song> objects) {
        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        SongList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(layout_id,parent,false);
        TextView tvName =rowView.findViewById(R.id.textViewTitle);
        TextView tvyearReleased = rowView.findViewById(R.id.textViewyearReleased);
        TextView tvSinger = rowView.findViewById(R.id.textViewSingers);
        ImageView Ivnew = rowView.findViewById(R.id.imageView);
        RatingBar Rbar = rowView.findViewById(R.id.rBar);

        Song currentVersion = SongList.get(position);
        tvName.setText(currentVersion.getTitle());
        tvyearReleased.setText(currentVersion.getYear() + "");
        tvSinger.setText(currentVersion.getSingers());
        Rbar.setRating(currentVersion.getStars());


        if(currentVersion.getYear() >= 2019){
            Ivnew.setImageResource(R.drawable.newsong);
        }else{
            Ivnew.setVisibility(View.INVISIBLE);
        }

        return rowView;
    }

}
