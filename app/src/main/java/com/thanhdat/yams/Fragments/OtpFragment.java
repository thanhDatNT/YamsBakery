package com.thanhdat.yams.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.thanhdat.yams.Activities.MainActivity;
import com.thanhdat.yams.Databases.UserDatabase;
import com.thanhdat.yams.Models.User;
import com.thanhdat.yams.R;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class OtpFragment extends Fragment {
    EditText inputCode1, inputCode2, inputCode3, inputCode4, inputCode5, inputCode6;
    AppCompatButton btnConfirm;
    ProgressBar progressBar;
    String phoneNumber, name, avatar, email;

    FirebaseAuth mAuth;
    FirebaseUser user;
    AuthCredential credential;

    public static final String TAG = OtpFragment.class.getSimpleName();

    public OtpFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth= FirebaseAuth.getInstance();
        credential = LoginFragment.credential;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_otp, container, false);
        inputCode1 = view.findViewById(R.id.edtCode1);
        inputCode2 = view.findViewById(R.id.edtCode2);
        inputCode3 = view.findViewById(R.id.edtCode3);
        inputCode4 = view.findViewById(R.id.edtCode4);
        inputCode5 = view.findViewById(R.id.edtCode5);
        inputCode6 = view.findViewById(R.id.edtCode6);
        btnConfirm = view.findViewById(R.id.btnVerifyOtp);
        progressBar = view.findViewById(R.id.progressBarOTP);

        getPhoneNumber();
        setupOTPInput();
        getOTP();
        return view;
    }

    private void getPhoneNumber() {
        Bundle bundle = getArguments();
        if(bundle != null){
            phoneNumber = bundle.getString("phone");
            name = bundle.getString("name");
            avatar = bundle.getString("photo");
            email =bundle.getString("email");
        }
    }

    private void getOTP(){
        progressBar.setVisibility(View.VISIBLE);
        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+84" + phoneNumber)       // Phone number to verify
                        .setTimeout(90L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(getActivity())                 // Activity (for callback binding)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                progressBar.setVisibility(View.GONE);
                                getInputCode(verificationId);
                            }

                            @Override
                            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                                progressBar.setVisibility(View.GONE);
                            }

                            @Override
                            public void onVerificationFailed(FirebaseException e) {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getContext(), e.getMessage(),Toast.LENGTH_SHORT).show();
                                Log.e(TAG, "onVerificationFailed: " + e.getMessage());
                            }
                        })
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void setupOTPInput() {
        inputCode1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    inputCode2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inputCode2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    inputCode3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inputCode3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    inputCode4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inputCode4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    inputCode5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inputCode5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    inputCode6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void getInputCode(String verificationID) {
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                if(inputCode1.getText().toString().trim().isEmpty() || inputCode2.getText().toString().trim().isEmpty() ||
                        inputCode3.getText().toString().trim().isEmpty() || inputCode4.getText().toString().trim().isEmpty() ||
                        inputCode5.getText().toString().trim().isEmpty() || inputCode6.getText().toString().trim().isEmpty()){
                    Toast.makeText(getContext(), "Vui lòng nhập đầy đủ mã OTP", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    String codeOTP= inputCode1.getText().toString() + inputCode2.getText().toString() + inputCode3.getText().toString() +
                            inputCode4.getText().toString() + inputCode5.getText().toString() + inputCode6.getText().toString();

                    PhoneAuthCredential authCredential= PhoneAuthProvider.getCredential(verificationID, codeOTP);
                    mAuth.signInWithCredential(authCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                mAuth.signOut();
                                changeAccount();
                                progressBar.setVisibility(View.GONE);
                            }
                            else{
                                Toast.makeText(getContext(), "Lỗi xác thực số điện thoại", Toast.LENGTH_LONG).show();
                                Log.e(TAG, task.getException().toString());
                                changeAccount();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });
                }
            }
        });
    }

    private void changeAccount() {
       mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
           @Override
           public void onComplete(@NonNull Task<AuthResult> task) {
               if(task.isSuccessful()){
                   UserDatabase userDatabase = new UserDatabase(getActivity());
                   boolean isSave = userDatabase.insertData(name, "0" + phoneNumber, email, avatar);
                   if(isSave)
                       Log.i(TAG, "User data was saved");
                   startActivity(new Intent(getContext(), MainActivity.class));
               }
               else {
                   Log.e(TAG, task.getException().toString());
               }
           }
       });
    }
}