package in.example.oopsrestaurant;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private OrderAdapter myAdapter;
    private ArrayList<Cart> carts;
    ArrayList  history;
    ArrayList<Cart> cartArrayList = new ArrayList<>();
    TextView textView;
    String rest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        textView = findViewById(R.id.text);

//        FirebaseFirestore.getInstance().collection("Restaurants").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                if(task.isSuccessful()){
//                    DocumentSnapshot ds = task.getResult();
//                    rest = ds.getString("name");
//                }
//            }
//        });
        FirebaseFirestore.getInstance().collection("Orders")
                .whereEqualTo("uid", FirebaseAuth.getInstance().getCurrentUser().getUid())
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult() != null) {
                        for(DocumentSnapshot ds:task.getResult().getDocuments()){
                            String name = ds.getString("name");
                            Log.d("test", ds.get("order").toString());
                            history = ds.get("order", ArrayList.class);
                        }
                    }
                });
       // textView.setText(Integer.toString(history.size()));
        myAdapter = new OrderAdapter(OrderActivity.this, cartArrayList);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(OrderActivity.this));
        recyclerView.setAdapter(myAdapter);

    }

}

