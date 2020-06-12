package in.example.oopsrestaurant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button btnLogin;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        progressDialog = new ProgressDialog(this);
        btnLogin = findViewById(R.id.btnLogin);
        firebaseAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(etEmail.getText().toString().trim(), etPassword.getText().toString().trim());
            }
        });

    }

    private void validate(String username, String userPassword) {
        progressDialog.setMessage("Logging in...");
        progressDialog.show();
//
//        if (cbRemember.isChecked()) {
//            Paper.book().write(Prevalent.user_email, username);
//            Paper.book().write(Prevalent.user_password, userPassword);
//        }
        progressDialog.setMessage("Logging in...");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(username, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_LONG).show();
                        retrieveData();
                        startActivity(new Intent(MainActivity.this, RegisterActivity.class));
                    } else if (!task.isSuccessful()) {
                    try {
                        throw task.getException();
                    } catch (FirebaseAuthInvalidUserException invalidEmail) {
                        Toast.makeText(MainActivity.this, invalidEmail.getMessage(), Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                        startActivity(new Intent(MainActivity.this, RegisterActivity.class));

                    } catch (FirebaseAuthInvalidCredentialsException wrongPassword) {
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, wrongPassword.getMessage(), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
    private void retrieveData() {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String UID = firebaseAuth.getCurrentUser().getUid();
                User user = dataSnapshot.child("Users").child(UID).getValue(User.class);
                Prevalent.currentOnlineUser = user;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }




}
