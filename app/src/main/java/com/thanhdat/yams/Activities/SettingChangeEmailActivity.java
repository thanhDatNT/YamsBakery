package com.thanhdat.yams.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.thanhdat.yams.Constants.Constant;
import com.thanhdat.yams.Databases.UserDatabase;
import com.thanhdat.yams.Fragments.LoginFragment;
import com.thanhdat.yams.Models.User;
import com.thanhdat.yams.R;

import java.util.ArrayList;

public class SettingChangeEmailActivity extends AppCompatActivity {
    Toolbar toolbarChangEmail;
    EditText edtEmail;
    TextView tvEmail;
    AppCompatButton btnChangeEmail;
    ArrayList<User> users =  MainActivity.user;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_change_email);
        linkView();
        addEventBackTab();
        changeEmailEvent();
    }

    private void changeEmailEvent() {
        btnChangeEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEmail = edtEmail.getText().toString().trim();
                if (!newEmail.equals("")) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    user.updateEmail(newEmail)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Log.d("Update successfully", "User email address updated.");
                                        Toast.makeText(SettingChangeEmailActivity.this, "Thay đổi email thành công", Toast.LENGTH_SHORT).show();
                                        UserDatabase database = new UserDatabase(SettingChangeEmailActivity.this);
                                        database.execSQL("UPDATE " + database.TABLE_NAME + " SET " + database.COL_EMAIL + " = '" + newEmail + "'" + " WHERE " + database.COL_EMAIL + " = '" + email + "'");
                                        startActivity(new Intent(SettingChangeEmailActivity.this, SettingAccount.class));
                                    } else {
                                        Log.e("Fail to change email", task.getException().toString());
                                    }
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    startActivity(new Intent(SettingChangeEmailActivity.this, SettingAccount.class));
                                    Log.e("Fail to change email", e.getMessage());
                                }
                            });

                }
            }
        });
    }

    private void linkView() {
        btnChangeEmail = findViewById(R.id.btnConfirmEmailChange);
        tvEmail = findViewById(R.id.tvMyEmail);
        edtEmail = findViewById(R.id.edtTypeEmailEdit);
        toolbarChangEmail= findViewById(R.id.toolbarChangEmail);
    }

    private void addEventBackTab(){
        setSupportActionBar(toolbarChangEmail);
        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle(null);
            toolbarChangEmail.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                onBackPressed();
                }
            });
        }
        email = users.get(0).getEmail();
        tvEmail.setText(email);
    }

}