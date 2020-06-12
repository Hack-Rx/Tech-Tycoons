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

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {
    private ArrayList<Food> foods = new ArrayList<> (  );
    private Context context;

    public FoodAdapter(Context context, ArrayList<Food> foods){
        this.foods = foods;
        this.context = context;
    }

    @NonNull
    @Override
    public FoodAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from ( parent.getContext () ).inflate ( R.layout.food_list_item , parent, false);
        return new FoodAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodAdapter.ViewHolder holder, int position) {
    holder.food_name.setText (foods.get ( position ).getName () );
        holder.food_calorie.setText ( foods.get(position).getCalories () );
    holder.food_fat.setText ( foods.get(position).getFat () );
    holder.food_protein.setText (  foods.get ( position ).getProtein () );
    holder.food_carbohydrate.setText (  foods.get ( position ).getCarbohydrate () );

        Picasso.get ().load ( foods.get ( position ).getImage () ).into ( holder.food_image );

    }

    @Override
    public int getItemCount() {
        return foods.size ();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView food_image;
        private TextView food_name;
        private TextView food_fat;
        private TextView food_protein;
        private TextView food_carbohydrate;
        private TextView food_calorie;


        public ViewHolder(@NonNull View itemView) {
            super ( itemView );

            food_image = itemView.findViewById ( R.id.food_image );
            food_name = itemView.findViewById ( R.id.food_name );
            food_calorie = itemView.findViewById ( R.id.food_calorie );
            food_fat = itemView.findViewById ( R.id.food_fat );
            food_protein = itemView.findViewById ( R.id.food_protein );
            food_carbohydrate = itemView.findViewById ( R.id.food_carbohydrate );

        }
    }
}
