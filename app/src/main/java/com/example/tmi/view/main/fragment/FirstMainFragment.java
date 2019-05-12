package com.example.tmi.view.main.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.tmi.R;
import com.example.tmi.view.main.MainViewModel;


public class FirstMainFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    private MainViewModel activityViewModel;

    @BindView(R.id.editText) EditText editText;

    @OnClick(R.id.ibCamera) void onClickCamera() {
        activityViewModel.setStep(Integer.valueOf(editText.getText().toString()));
        mListener.onVideo();
    }

    @OnClick(R.id.ibFolder) void onClickFolder() {
        activityViewModel.setStep(Integer.valueOf(editText.getText().toString()));
        mListener.onFolder();
    }

    public FirstMainFragment(MainViewModel mainViewModel) {
        // Required empty public constructor
        this.activityViewModel = mainViewModel;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first_main, container, false);
    }

    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                                               + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onVideo();
        void onFolder();
    }
}
