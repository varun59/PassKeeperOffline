package com.passkeeperoffline.ui.authentication.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.passkeeperoffline.R;
import com.passkeeperoffline.base.BaseFragment;
import com.passkeeperoffline.ui.home.activity.HomeActivity;
import com.passkeeperoffline.utils.PreferencesHelper;
import com.passkeeperoffline.utils.Utils;

import java.util.Objects;
import java.util.concurrent.Executor;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginFragment extends BaseFragment {
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.ivFinger)
    ImageView ivFinger;
    @BindView(R.id.etPassword)
    TextInputEditText etPassword;
    @BindView(R.id.tilPassword)
    TextInputLayout tilPassword;

    @Override
    public int setLayout() {
        return R.layout.fragment_login;
    }

    @Override
    public String setTitle() {
        return "Pass Keeper";
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvName.setText(PreferencesHelper.getInstance().getName());
        init();
        if (PreferencesHelper.getInstance().getBiometric() == 1)
            setUpFp();
        else ivFinger.setVisibility(View.INVISIBLE);
    }

    private void init() {
        ivFinger.setOnClickListener(view -> setUpFp());
    }

    private void setUpFp() {
        if (BiometricManager.from(requireContext()).canAuthenticate() == BiometricManager.BIOMETRIC_SUCCESS) {
            getInstanceBiometric().authenticate(getPrompInfo());
        }
    }

    @OnClick({R.id.btNext})
    public void onClick(View view) {
        matchPassword(view);

    }

    private void matchPassword(View view) {
        String password = Utils.getInstance().decryptPassword(getContext(), PreferencesHelper.getInstance().getPassword());
        if (password.equals(etPassword.getText().toString())) {
            startActivity(new Intent(getActivity(), HomeActivity.class));
            getActivity().finish();
        } else tilPassword.setError("Wrong Password!");

    }


    private BiometricPrompt getInstanceBiometric() {
        Executor executor = ContextCompat.getMainExecutor(getContext());
        BiometricPrompt biometricPrompt = new BiometricPrompt(this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                startActivity(new Intent(getActivity(), HomeActivity.class));
                getActivity().finish();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
            }
        });
        return biometricPrompt;
    }

    private BiometricPrompt.PromptInfo getPrompInfo() {
        return new BiometricPrompt.PromptInfo.Builder().setTitle("Use fingerprint to login").setNegativeButtonText("Cancel").build();
    }
}