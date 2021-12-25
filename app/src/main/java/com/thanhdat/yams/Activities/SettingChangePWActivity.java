package com.thanhdat.yams.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.thanhdat.yams.R;

public class SettingChangePWActivity extends AppCompatActivity {
    Toolbar toolChangePwd;
    AppCompatButton btnPasswordChange;
    TextInputEditText edtNewPW, edtConfirmNewPW, edtOldPW;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_change_pwactivity);
        linkView();
        addEventTabBack();

        changePassword();
    }

    private void changePassword() {
        email = MainActivity.user.get(0).getEmail();
        btnPasswordChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newPW, oldPW, confirmPW;
                newPW = edtNewPW.getText().toString().trim();
                oldPW = edtOldPW.getText().toString().trim();
                confirmPW = edtConfirmNewPW.getText().toString().trim();
                if(newPW.equals(confirmPW) && !newPW.equals(oldPW)){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    AuthCredential credential = EmailAuthProvider.getCredential(email, oldPW);
                    user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            updatePassword(user, newPW);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(SettingChangePWActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                else {
                    Toast.makeText(SettingChangePWActivity.this, "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void updatePassword(FirebaseUser user, String newPW){
        user.updatePassword(newPW)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(SettingChangePWActivity.this, "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                            SettingChangePWActivity.super.onBackPressed();
                        }
                        else {
                            Log.e("Fail to change password", task.getException().toString());
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        startActivity(new Intent(SettingChangePWActivity.this, SettingAccount.class));
                        Log.e("Fail to change Password", e.getMessage());
                    }
                });
    }


    private void linkView() {
        edtOldPW = findViewById(R.id.edtOldPW);
        edtNewPW = findViewById(R.id.edtNewPW);
        edtConfirmNewPW = findViewById(R.id.edtRetypePW);
        btnPasswordChange = findViewById(R.id.btnConfirmPwChange);
        toolChangePwd = findViewById(R.id.toolbarChangePwd);
    }

    private void addEventTabBack() {
        setSupportActionBar(toolChangePwd);
        getSupportActionBar().setTitle(null);
        toolChangePwd.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}