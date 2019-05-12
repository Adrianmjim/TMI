package com.example.tmi.view.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tmi.R;
import com.example.tmi.model.entities.Tag;
import com.example.tmi.view.TranslationUtils;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Tag> face;

    public MyAdapter(List<Tag> face) {
        this.face = face;
    }
    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv) TextView textView;
        @BindView(R.id.tv2) TextView textView2;
        @BindView(R.id.tv3) TextView textView3;

        MyViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
    @NonNull @Override public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                                        .inflate(R.layout.my_view_holder, parent, false);
        return new MyViewHolder(v);
    }
    @Override public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView.setText("Cara "+String.valueOf(position+1));
        holder.textView2.setText(TranslationUtils.translate(face.get(position).getAttributes().getMood().getValue()));
        holder.textView3.setText("Coincidencia: "+face.get(position).getAttributes().getMood().getConfidence()+"%");
    }
    @Override public int getItemCount() {
        return face.size();
    }
}
