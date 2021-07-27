package com.a7apps.tvbase.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.a7apps.tvbase.R;
import com.a7apps.tvbase.assistant.Constants;
import com.a7apps.tvbase.data.Data;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class AdapMovies extends RecyclerView.Adapter<AdapMovies.HolderAdapMovies> {
    private Context context;
    private Data data;

    public AdapMovies(Context context) {
        this.context = context;
        data = new Data(context);
    }

    public static class HolderAdapMovies extends RecyclerView.ViewHolder{
        ImageView imageView;
        public HolderAdapMovies(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ivItemRv);
        }
    }

    @NonNull
    @Override
    public HolderAdapMovies onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_rv_movies,parent,false);
        return new HolderAdapMovies(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderAdapMovies holder, int position) {
        Glide.with(context).load(Constants.getIMAGE_URL()+data.getDataPopMovies()
                .get(position)).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return data.getDataPopMovies().size();
    }
}
