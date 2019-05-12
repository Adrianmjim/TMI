package com.example.tmi.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.tmi.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class LoadDialogFragment extends DialogFragment {

    @BindView(R.id.loadImage) ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.load_dialog_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        Glide.with(getActivity())
                .load(R.drawable.load_icon)
                .placeholder(R.drawable.load_icon)
                .into(imageView);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() != null) {
            if(getDialog().getWindow() != null) {
                getDialog().getWindow().setLayout(WRAP_CONTENT, WRAP_CONTENT);
            }
            getDialog().setCanceledOnTouchOutside(false);
        }
    }

}
