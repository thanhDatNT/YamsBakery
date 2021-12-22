package com.thanhdat.yams.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.thanhdat.yams.Interfaces.OnClickInterface;
import com.thanhdat.yams.R;

import java.util.Objects;

public class RegisterFragment extends Fragment {
    TextInputEditText edtName, edtMail, edtPhone, edtPassword;
    AppCompatButton btnSingUp;
    TextView tvLogin;
    ProgressBar progressBar2;
    OnClickInterface onClickInterface;

    FirebaseAuth mAuth;
    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        edtName = view.findViewById(R.id.edtUserNameSignUp);
        edtPhone = view.findViewById(R.id.edtPhoneRegister);
        edtMail = view.findViewById(R.id.edtEmailRegister);
        edtPassword = view.findViewById(R.id.edtPasswordRegister);
        btnSingUp = view.findViewById(R.id.btnSignUp);
        tvLogin = view.findViewById(R.id.tvBackToLogin);
        progressBar2 = view.findViewById(R.id.progressBarRegister);

        addEvent();

        return view;
    }

    private void addEvent() {
        btnSingUp.setOnClickListener(v -> RegisterUser());
        tvLogin.setOnClickListener(v -> {
            onClickInterface = (OnClickInterface) getActivity();
            onClickInterface.setClick(0);
        });
    }

    //  REGISTER WITH EMAIL AND PASSWORD
    private void RegisterUser(){
        String mail = edtMail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        String userName = edtName.getText().toString().trim();
        String phone = edtPhone.getText().toString().trim();
        if(userName.isEmpty()){
            edtName.setError("Vui lòng điền đầy đủ thông tin");
            edtName.requestFocus();
            return;
        }
        if(phone.isEmpty()){
            edtPhone.setError("Vui lòng điền đầy đủ thông tin");
            edtPhone.requestFocus();
            return;
        }
        if(mail.isEmpty()){
            edtMail.setError("Vui lòng điền đầy đủ thông tin");
            edtMail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(mail).matches()){
            edtMail.setError("Vui lòng điền đầy đủ thông tin");
            edtMail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            edtPassword.setError("Vui lòng điền đầy đủ thông tin");
            edtPassword.requestFocus();
            return;
        }
        progressBar2.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(mail, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            progressBar2.setVisibility(View.GONE);
                            Toast.makeText(getContext(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();

                            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                            LoginFragment loginFragment = new LoginFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString("phone", phone);
                            bundle.putString("u_name", userName);
                            loginFragment.setArguments(bundle);
                            fragmentTransaction.replace(R.id.layoutLoginContainer, loginFragment);
                            fragmentTransaction.addToBackStack(null);
                            fragmentTransaction.commit();
                        }
                        else{
                            Toast.makeText(getContext(), "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                            progressBar2.setVisibility(View.GONE);
                        }
                    }
                });

    }
}