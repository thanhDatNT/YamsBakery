package com.thanhdat.yams.Fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.thanhdat.yams.Activities.LoginActivity;
import com.thanhdat.yams.Activities.MainActivity;
import com.thanhdat.yams.Constants.Constant;
import com.thanhdat.yams.Interfaces.OnClickInterface;
import com.thanhdat.yams.R;

import java.util.Arrays;

public class LoginFragment extends Fragment {
    EditText edtEmail, edtPassword;
    TextView tvForgotPw, tvSignUp;
    AppCompatButton btnLogin;
    SignInButton btnGGLogin;
    LoginButton btnFBLogin;
    ProgressBar progressBar;
    OnClickInterface onClickInterface;

    private FirebaseAuth mAuth;
    private FirebaseUser user;
//    private GoogleSignInClient mGoogleSignInClient;
//    private final static int RC_SIGN_IN= 111;
//    CallbackManager callbackManager;
    public static AuthCredential credential;
    public static final String TAG = LoginFragment.class.getSimpleName();
    String phoneNumber ="", name ="User", photo ="https://i.ibb.co/LxJj5dx/ic-launcher.png";

    public LoginFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                if(currentAccessToken == null){
                    mAuth.signOut();
                }
            }
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        edtEmail = view.findViewById(R.id.edtEmailLogin);
        edtPassword = view.findViewById(R.id.edtPasswordLogin);
        tvForgotPw = view.findViewById(R.id.tvForgotPass);
        tvSignUp = view.findViewById(R.id.tvSignUpLogin);
        btnLogin = view.findViewById(R.id.btnLogin);
//        btnFBLogin = view.findViewById(R.id.fb_login_button);
//        btnGGLogin= view.findViewById(R.id.google_button);
        progressBar = view.findViewById(R.id.progressBarLogin);
        addEvents();
        return view;
    }

    private void addEvents() {
        Bundle bundle = getArguments();
        if(bundle != null){
            phoneNumber = bundle.getString("phone");
            name = bundle.getString("u_name");
        }
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickInterface = (OnClickInterface) getActivity();
                onClickInterface.setClick(1);
            }
        });
        btnLogin.setOnClickListener(v -> userLogin());
        tvForgotPw.setOnClickListener(v -> resetPassword());

    }


    //    SIGN IN WITH EMAIL AND PASSWORD

    private void userLogin() {
        String email= edtEmail.getText().toString().trim();
        String password= edtPassword.getText().toString().trim();
        if(email.isEmpty()){
            edtEmail.setError("Vui lòng điền đầy đủ thông tin");
            edtEmail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            edtPassword.setError("Vui lòng điền đầy đủ thông tin");
            edtPassword.requestFocus();
            return;
        }
        progressBar.setVisibility((View.VISIBLE));
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Log.i(TAG, "Verified");
                        }
                    });
                    credential = EmailAuthProvider.getCredential(email, password);
                    progressBar.setVisibility(View.GONE);
                    user= mAuth.getCurrentUser();
                    verifyPhoneNumber(user);
                }
                else{
                    Toast.makeText(getContext(), "Đăng nhập thất bại, xin thử lại!", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    Log.e(TAG, task.getException().toString());
                }
            }
        });
    }

    private void verifyPhoneNumber(FirebaseUser user) {
        if(user.getPhoneNumber() != null){
            phoneNumber = user.getPhoneNumber();
            goToOTP();
        }
        else if(phoneNumber.equals("")){
            Dialog dialog= new Dialog(getContext());
            dialog.setContentView(R.layout.dialog_get_phoneno);
            EditText edtPhone= dialog.findViewById(R.id.editTextPhone);
            Button btnOK= dialog.findViewById(R.id.buttonConfirmPhone);
            ImageButton btnCancel= dialog.findViewById(R.id.buttonCancelPhone);

            btnOK.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!edtPhone.getText().toString().trim().equals("") && edtPhone.getText().toString().charAt(0) != 0){
                        phoneNumber = edtPhone.getText().toString();
                        goToOTP();
                        dialog.dismiss();
                    }
                    else {
                        edtPhone.setError("Nhập số điện thoại của bạn");
                    }
                }
            });
            btnCancel.setOnClickListener(v -> dialog.dismiss());
            dialog.show();
        }
        else{
            if(phoneNumber.charAt(0) == '0' || phoneNumber.length() != 9){
                phoneNumber = "";
                verifyPhoneNumber(user);
            }
            else {
                goToOTP();
            }
        }
    }

    private void goToOTP(){
//        Save credential for re authentication if needed
        saveCredential();

        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        OtpFragment otpFragment = new OtpFragment();
        Bundle bundle = new Bundle();
        bundle.putString("phone", phoneNumber);
        if(user.getPhotoUrl() !=null){
            bundle.putString("photo", user.getPhotoUrl().toString());
        }
        else {
            bundle.putString("photo", photo);
        }
        if(user.getDisplayName() != null){
            bundle.putString("name", user.getDisplayName());
        }
        else {
            bundle.putString("name", name);
        }
        if(user.getEmail() !=null){
            bundle.putString("email", user.getEmail());
        }
        else{
            bundle.putString("email", "your email@gmail.com");
        }
        otpFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.layoutLoginContainer, otpFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void resetPassword() {
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_reset_pw);
        dialog.setCanceledOnTouchOutside(false);
        EditText edtMail = dialog.findViewById(R.id.edtMailDialog);
        ImageButton btnOK = dialog.findViewById(R.id.imageButtonSend);
        ImageButton btnCancel = dialog.findViewById(R.id.imbCancelResetPhone);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.sendPasswordResetEmail(edtMail.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getContext(), "Vui lòng kiểm tra hộp thư đến", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.dismiss();
            }
        });
        btnCancel.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }

    private void saveCredential(){
        SharedPreferences sharedPreferences= getActivity().getSharedPreferences(Constant.PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString("credential", String.valueOf(credential));
        editor.apply();
    }
}