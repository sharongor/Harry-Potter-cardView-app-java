package il.example.assignment2;

import android.media.Image;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CharactersAdapter extends RecyclerView.Adapter<CharactersAdapter.MyViewHolder> {

    interface ItemListener{
        void onItemClicked(int index);
    }

    ArrayList<DataModel> dataset;
    private static ItemListener callback;

    public void setList(ArrayList<DataModel> list){
        this.dataset = list;
        notifyDataSetChanged(); // update the data set with every change in the search bar
    }

    public CharactersAdapter(ArrayList<DataModel> list, ItemListener callback){
        this.dataset = list;
        this.callback=callback;
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView textViewName;
        TextView textViewDesc;


        public MyViewHolder(View view){
            super(view);
            view.setOnClickListener(this);
            imageView = view.findViewById(R.id.image);
            textViewName = view.findViewById(R.id.name_character);
            textViewDesc = view.findViewById(R.id.desc_character);
        }

        @Override
        public void onClick(View v) {
            callback.onItemClicked(getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public CharactersAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CharactersAdapter.MyViewHolder holder, int position) {
        holder.imageView.setImageResource(dataset.get(position).getImage());
        holder.textViewName.setText(dataset.get(position).getName());
        holder.textViewDesc.setText(dataset.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
