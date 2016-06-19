package zaafranigabriel.recipydesign.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;

import zaafranigabriel.recipydesign.Class.Core.Recipy;
import zaafranigabriel.recipydesign.Class.RowListView.GoogleSingleRow;
import zaafranigabriel.recipydesign.R;

/**
 * Created by zaafranigabriel on 23/06/2016.
 */
public class GoogleListAdapter extends BaseAdapter {

    ArrayList<GoogleSingleRow> googleList = new ArrayList<>();
    Context context;

    public GoogleListAdapter(Context c,LinkedList<Recipy> list){
        context = c;

        for(Recipy r : list){
            googleList.add(new GoogleSingleRow(r.getImg(),r.getTitle(),r.getContent()));
        }

    }

    @Override
    public int getCount() {
        return googleList.size();
    }

    @Override
    public Object getItem(int position) {
        return googleList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.google_single_row,parent,false);

        TextView txtTitle = (TextView)row.findViewById(R.id.GoogleTitle);
        TextView txtContent = (TextView)row.findViewById(R.id.GoogleContent);
        ImageView imgv = (ImageView)row.findViewById(R.id.GoogleimageView);

        GoogleSingleRow temp = googleList.get(position);

        txtTitle.setText(temp.getTitle());
        txtContent.setText(temp.getDescription());
        imgv.setImageResource(temp.getImage());

        return  row;
    }
}
