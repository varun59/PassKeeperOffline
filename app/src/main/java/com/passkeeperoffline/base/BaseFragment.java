package com.passkeeperoffline.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.MaterialToolbar;
import com.passkeeperoffline.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {
    private View view;
    private Unbinder unBinder;

    @LayoutRes
    public abstract int setLayout();

    public abstract String setTitle();


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(setLayout(), container, false);
        unBinder = ButterKnife.bind(this, view);
        return view;
    }

    public void changeFragment(Fragment fragment, Boolean addToBack) {
        if (getActivity() != null)
            ((BaseActivity) getActivity()).changeFragment(fragment, addToBack);
    }

    public void clearBackStack() {
        if (getActivity() != null)
            ((BaseActivity) getActivity()).clearBackStack();
    }

    public ImageView ivOverflow = null;
    public ConstraintLayout toolbar;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tvTitle;
        if (setTitle() != null) {
            toolbar = getActivity().findViewById(R.id.toolbar);
            tvTitle = toolbar.findViewById(R.id.tvTitle);
            ivOverflow = toolbar.findViewById(R.id.ivOverflow);
            tvTitle.setText(setTitle());
        }
        switch (setLayout()) {
            case R.layout.fragment_home:
                ivOverflow.setVisibility(View.VISIBLE);
                break;
            default:
                ivOverflow.setVisibility(View.GONE);
                break;


        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unBinder.unbind();

    }
}
