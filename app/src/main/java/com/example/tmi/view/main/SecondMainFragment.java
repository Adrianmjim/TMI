package com.example.tmi.view.main;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.example.tmi.R;
import com.example.tmi.model.entities.Mood;
import com.example.tmi.model.entities.Report;
import com.example.tmi.model.entities.Tag;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;


public class SecondMainFragment extends Fragment {

    @BindView(R.id.imageView1) ImageView imageView;
    @BindView(R.id.rvMainActivity) RecyclerView recyclerView;
    @BindView(R.id.chart) LineChart lineChart;
    @BindView(R.id.chart2) LineChart lineChart2;

    @BindColor(R.color.neutral) Integer neutral;
    @BindColor(R.color.anger) Integer anger;
    @BindColor(R.color.disgust) Integer disgust;
    @BindColor(R.color.fear) Integer fear;
    @BindColor(R.color.happiness) Integer happiness;
    @BindColor(R.color.sadness) Integer sadness;
    @BindColor(R.color.surprise) Integer surprise;

    @OnClick(R.id.button1) void onClick1() {
        viewModel.before();
    }

    @OnClick(R.id.button2) void onClick2() {
        viewModel.next();
    }
    private MainViewModel viewModel;
    private OnFragmentInteractionListener mListener;
    private MyAdapter adapter;

    private Report report;

    public SecondMainFragment(MainViewModel viewModel) {
        // Required empty public constructor
        this.viewModel = viewModel;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second_main, container, false);
    }

    /*@Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                                               + " must implement OnFragmentInteractionListener");
        }
    }*/

    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //recyclerView.addItemDecoration(new DividerItemDecoration(rv.getContext(), DividerItemDecoration.VERTICAL));
        viewModel.getSequence().observe(this, this::showReport);
        viewModel.getSequence2().observe(this, ignore -> {});
        viewModel.getHigherMoods().observe(this, this::showHigherMoods);
        viewModel.getCoincidencys().observe(this, this::showCoincidencies);
    }
    private void showCoincidencies(List<Mood> moods) {
        List<Entry> entries = new ArrayList<>();
        List<Integer> colors = new ArrayList<>();
        for (int i = 0; i < moods.size(); i++) {
            entries.add(new Entry(i, moods.get(i).getConfidence()));
            colors.add(getColor(moods.get(i).getValue()));
        }
        LineDataSet data = new LineDataSet(entries, "Prueba");
        LineData lineData = new LineData(data);
        lineChart.setData(lineData);
        lineChart.invalidate(); // refresh
    }
    private Integer getColor(String name) {
        switch(name) {
            case "neutral_mood":
                return neutral;
            case "anger":
                return anger;
            case "disgust":
                return disgust;
            case "fear":
                return fear;
            case "happiness":
                return happiness;
            case "sadness":
                return sadness;
            case "surprise":
                return surprise;
        }
        return 0;
    }
    private void showHigherMoods(List<Mood> moods) {

    }
    /*private void showBitmap(Bitmap bitmap) {
        Bitmap bitmap1 = drawFaceRectanglesOnBitmap(bitmap, report.getPhotos().get(0).getTags());
        imageView.setImageBitmap(bitmap1);

    }*/
    private void showReport(Report report) {
        Bitmap bitmap1 = drawFaceRectanglesOnBitmap(viewModel.getSequence2().getValue(), report.getPhotos().get(0).getTags());
        imageView.setImageBitmap(bitmap1);
        adapter = new MyAdapter(report.getPhotos().get(0).getTags());
        recyclerView.setAdapter(adapter);
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private Bitmap drawFaceRectanglesOnBitmap(
            Bitmap originalBitmap, List<Tag> tags) {
        Bitmap bitmap = originalBitmap.copy(Bitmap.Config.ARGB_8888, true);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(8);
        if (tags != null) {
            for (Tag tag : tags) {
                canvas.drawRect(
                        tag.getCenter().getX() + 2.0f,
                        tag.getCenter().getY() + 2.0f,
                        tag.getCenter().getX() + 2.0f,
                        tag.getCenter().getY() + 2.0f,
                        paint);
            }
        }
        return bitmap;
    }

}
