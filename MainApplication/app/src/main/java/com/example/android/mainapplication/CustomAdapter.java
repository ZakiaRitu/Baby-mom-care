package com.example.android.mainapplication;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Nihal pd on 4/3/2016.
 */
public class CustomAdapter extends ArrayAdapter{

    private Cursor students;
    public CustomAdapter(Context context, Cursor data) {
        super(context, R.layout.single_list_row);
        students =data;
    }

    @Override
    public int getCount() {
        return students.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView ==null)
        {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.single_list_row,parent,false);
        }
        TextView name = (TextView) convertView.findViewById(R.id.st_name);
        TextView location = (TextView) convertView.findViewById(R.id.location);
        TextView comment = (TextView) convertView.findViewById(R.id.Comment);
        students.moveToPosition(position);
        int nameColumn = students.getColumnIndex("name");
        String stName = students.getString(nameColumn);
        name.setText(stName);
        int locationColumn = students.getColumnIndex("location");
        String stLocation = students.getString(locationColumn);
        location.setText(stLocation);
        int commentColumn = students.getColumnIndex("comment");
        Log.d("simul", String.valueOf(commentColumn));

        String stComment = students.getString(commentColumn);
        comment.setText(stComment);
        return convertView;
    }
}
