package com.example.tmi.view.main;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.StringSignature;
import com.example.tmi.R;
import com.example.tmi.model.entities.Report;

import java.util.List;


public class SecondMainFragment extends Fragment {

    @BindView(R.id.imageView1) ImageView imageView;
    @BindView(R.id.rvMainActivity) RecyclerView recyclerView;

    @OnClick(R.id.button1) void onClick1() {
        showReport(list.get(--i));
    }

    @OnClick(R.id.button2) void onClick2() {
        showReport(list.get(++i));
    }
    private MainViewModel viewModel;
    private OnFragmentInteractionListener mListener;
    private MyAdapter adapter;

    private int i = 0;
    private List<Report> list;

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

        viewModel.getSequence().observe(this, this::sequenceObserver);
    }
    private void sequenceObserver(List<Report> reports) {
        list = reports;
        showReport(list.get(i));
    }
    private void showReport(Report report) {
        Glide.with(getActivity())
                .load(report.getPhotos().get(0).getUrl())
                .animate(R.anim.abc_fade_in)
                .signature(new StringSignature(String.valueOf(System.currentTimeMillis())))
                .into(imageView);
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
}
