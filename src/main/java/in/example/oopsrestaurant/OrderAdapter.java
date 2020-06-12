package in.example.oopsrestaurant;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import androidx.recyclerview.widget.RecyclerView;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Cart> cartArrayList;
    private int c = 0;

    public OrderAdapter(Context context, ArrayList<Cart> cartArrayList) {
        this.context = context;
        this.cartArrayList = cartArrayList;
    }


    @NonNull
    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_dish_layout, parent, false);
        return new OrderAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.ViewHolder holder, int position) {
        holder.dishName.setText(cartArrayList.get(position).getN());

        holder.price.setText(cartArrayList.get(position).getP());

        if(cartArrayList.get(position).getV().equalsIgnoreCase("veg")){
            holder.vnv.setImageResource(R.drawable.veg);
        }
        else {
            holder.vnv.setImageResource(R.drawable.non);
        }

    }

    @Override
    public int getItemCount() {
        return cartArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView vnv, dishadd, dishminus;
        private TextView dishName,price,veg, quantity, totalPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            vnv = itemView.findViewById(R.id.vnv);
            dishName = itemView.findViewById(R.id.dishName);
            price = itemView.findViewById(R.id.price);
            veg = itemView.findViewById(R.id.veg);
            dishadd = itemView.findViewById(R.id.dishadd);
            dishminus = itemView.findViewById(R.id.dishminus);
            totalPrice = itemView.findViewById(R.id.totalPrice);

        }
    }
}
