package com.example.fitnessapp.model_class;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DatumAdapter extends RecyclerView.Adapter<DatumAdapter.ViewHolder> {
    private ArrayList<Datum> data = new ArrayList<> (  );
    private Context context;
    public DatumAdapter(Context context, ArrayList<Datum> data) {
       this.data=data;
       this.context=context;
    }

    @NonNull
    @Override
    public DatumAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from ( parent.getContext () ).inflate ( R.layout.daily_food_list_item , parent, false);
        return new DatumAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull DatumAdapter.ViewHolder holder, int position) {
        holder.food_name.setText ( data.get ( position ).getItemname () );
        holder.food_calorie.setText ( String.valueOf ( data.get ( position ).getCalories () ) );
        holder.food_amount.setText ( data.get ( position ).getAmount () );
        Picasso.get ().load ( data.get ( position ).getImage () ).into ( holder.food_image );


    }

    @Override
    public int getItemCount() {
        return data.size ();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView food_image;
        private TextView food_name;
        private TextView food_calorie;
        private TextView food_amount;

        public ViewHolder(@NonNull View itemView) {
            super ( itemView );
            food_image = itemView.findViewById ( R.id.food_image );
            food_name = itemView.findViewById ( R.id.food_name );
            food_calorie = itemView.findViewById ( R.id.food_calorie );
            food_amount = itemView.findViewById ( R.id.food_amount );

        }
    }
}
