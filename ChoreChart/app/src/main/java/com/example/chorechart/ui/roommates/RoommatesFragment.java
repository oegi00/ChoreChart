package com.example.chorechart.ui.roommates;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.chorechart.R;

public class RoommatesFragment extends Fragment {

    private RoommatesViewModel roommatesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        roommatesViewModel =
                new ViewModelProvider(this).get(RoommatesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_roommates, container, false);
//        final TextView textView = root.findViewById(R.id.text_roommates);
//        roommatesViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }
}