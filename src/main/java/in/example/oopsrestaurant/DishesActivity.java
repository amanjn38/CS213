package in.example.oopsrestaurant;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.ArrayList;

public class DishesActivity extends AppCompatActivity {

    private EditText name, price, description;
    private RadioButton veg, nonVeg;
    private Button btnSave;
    private ProgressDialog progressDialog;
    private ImageView imageUploader;
    private ArrayList<String> url;
    private String restaurantName;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dishes);

//
//        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPrefs", MODE_PRIVATE);
//        pref.edit().putBoolean("isIntroOpened", true).apply();


        url = new ArrayList<>();
        name = findViewById(R.id.name);
        price = findViewById(R.id.price);
        progressDialog = new ProgressDialog(this);
        description = findViewById(R.id.description);
        veg = findViewById(R.id.veg);
        nonVeg = findViewById(R.id.nonVeg);
        btnSave = findViewById(R.id.btnSave);
        storageReference = FirebaseStorage.getInstance().getReference("Uploads");
        imageUploader = findViewById(R.id.imageuploader);

        restaurantName = getIntent().getStringExtra("name");

        btnSave.setOnClickListener(v -> {
            SaveDetails();
            ResetData();
        });

        imageUploader.setOnClickListener(v -> {
            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .start(DishesActivity.this);
        });

    }

    private void ResetData() {
        name.getText().clear();
        price.getText().clear();
        description.getText().clear();
        veg.setChecked(false);
        nonVeg.setChecked(false);
        imageUploader.setImageDrawable(null);
    }

    private void SaveDetails() {
        String name1 = name.getText().toString().trim();
        String price1 = price.getText().toString().trim();
        String desp = description.getText().toString().trim();
        String vn = "";
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        if(veg.isChecked()){
            vn = veg.getText().toString();
        } else if(nonVeg.isChecked()){
            vn = nonVeg.getText().toString();
        }
        User user = new User(name1, price1, desp, vn, uid);
        FirebaseFirestore.getInstance().collection("Restaurant - Dishes").document().set(user);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable final Intent data) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                progressDialog.setMessage("Uploading image ...");
                progressDialog.setTitle("");
                progressDialog.show();
                StorageReference filepath = storageReference.child("restaurant").child(System.currentTimeMillis()+".jpg");
                filepath.putFile(resultUri).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        filepath.getDownloadUrl().addOnCompleteListener(taskUrl -> {
                            progressDialog.dismiss();
                            if (taskUrl.isSuccessful()) {
                                url.add(taskUrl.getResult().toString());
                                } else
                              //Log.d("Error", taskUrl.getException().toString());
                            progressDialog.dismiss();
                            Toast.makeText(DishesActivity.this, "Failed to add image", Toast.LENGTH_SHORT).show();
                        });
                    } else
                        progressDialog.dismiss();
                   // Log.d("Error", task.getException().toString());
                        Toast.makeText(DishesActivity.this, "Failed to add image", Toast.LENGTH_LONG).show();
                });
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
