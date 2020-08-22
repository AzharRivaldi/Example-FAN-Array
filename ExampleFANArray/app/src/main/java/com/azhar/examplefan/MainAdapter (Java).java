package com.azhar.examplefan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Created by Azhar Rivaldi on 22-12-2019.
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<ModelMain> modelMain;

    public MainAdapter(List<ModelMain> modelMain){
        this.modelMain = modelMain;
    }

    @Override
    public MainAdapter.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_main, parent, false);
        return new MainAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainAdapter.ViewHolder holder, int pos){
        final ModelMain data = modelMain.get(pos);

        holder.name.setText(data.getName());
        holder.propellant.setText(data.getPropellant());
        holder.destination.setText(data.getDestination());
    }

    @Override
    public int getItemCount(){
        return modelMain.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView name;
        public TextView propellant;
        public TextView destination;

        public ViewHolder(View itemView){
            super(itemView);

            name = itemView.findViewById(R.id.name);
            propellant = itemView.findViewById(R.id.propellant);
            destination = itemView.findViewById(R.id.destination);
        }
    }

}
