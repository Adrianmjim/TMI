package com.example.myapplication.view.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.model.entities.Photo;
import com.example.myapplication.model.entities.Tag;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Tag> face;

    public MyAdapter(List<Tag> face) {
        this.face = face;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView;
        public TextView textView2;
        public MyViewHolder(View v) {
            super(v);
            textView = v.findViewById(R.id.tv);
            textView2 = v.findViewById(R.id.tv2);
        }
    }
    @NonNull @Override public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext())
                                        .inflate(R.layout.my_view_holder, parent, false);
        return new MyViewHolder(v);
    }
    @Override public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView.setText("Cara "+String.valueOf(position+1));
        holder.textView2.setText(face.get(position).getAttributes().getMood().getValue());
    }
    @Override public int getItemCount() {
        return face.size();
    }
}
