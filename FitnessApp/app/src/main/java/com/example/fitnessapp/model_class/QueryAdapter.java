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

import java.util.ArrayList;

public class QueryAdapter extends RecyclerView.Adapter<QueryAdapter.ViewHolder> {
    ArrayList<FoodModel> foodModels;
    Context context;

    public QueryAdapter(ArrayList<FoodModel> foodModels, Context context) {
        this.foodModels = foodModels;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from ( parent.getContext () ).inflate ( R.layout.row_food, parent, false);
        return new ViewHolder ( view );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.label.setText ( foodModels.get ( position ).getLabel () );
        holder.value.setText ( foodModels.get ( position ).getValues () );
        holder.imageicon.setImageResource ( foodModels.get ( position ).getImageicon () );

    }

    @Override
    public int getItemCount() {
        return foodModels.size ();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView label, value;
        ImageView imageicon;
        public ViewHolder(@NonNull View itemView) {
            super ( itemView );
            imageicon = itemView.findViewById ( R.id.imageicon );
            label = itemView.findViewById ( R.id.label );
            value = itemView.findViewById ( R.id.value ) ;
        }
    }
}
