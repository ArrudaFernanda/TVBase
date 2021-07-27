package com.a7apps.tvbase.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import com.a7apps.tvbase.R;
import androidx.recyclerview.widget.RecyclerView;
import com.a7apps.tvbase.assistant.Constants;
import com.a7apps.tvbase.data.Data;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class AdapSeries extends RecyclerView.Adapter<AdapSeries.HolderAdapSeries> {
    private Context context;
    private Data data;
    public AdapSeries(Context context) {
        this.context = context;
        data = new Data(context);
    }

    public static class HolderAdapSeries extends RecyclerView.ViewHolder{
        ImageView imageView;

        public HolderAdapSeries(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ivItemRvSeries);
        }
    }

    @NonNull
    @Override
    public AdapSeries.HolderAdapSeries onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_rv_series,parent,false);
        return new HolderAdapSeries(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapSeries.HolderAdapSeries holder, int position) {
        Glide.with(context).load(Constants.getIMAGE_URL()+data.getDataPopSeries()
                .get(position)).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return data.getDataPopSeries().size();
    }
}
