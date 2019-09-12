package com.adipurnomo.moviecatalogapi.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adipurnomo.moviecatalogapi.DetailTvActivity;
import com.adipurnomo.moviecatalogapi.R;
import com.adipurnomo.moviecatalogapi.modeltv.Result;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TvshowsAdapter extends RecyclerView.Adapter<TvshowsAdapter.TvHolder> {
    private Context context;
    private List<Result> listTv;

    public void setData(List<Result> listTv)
    {
        this.listTv = listTv;
    }
    @NonNull
    @Override
    public TvshowsAdapter.TvHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tvshow_row,parent,false);
        return new TvHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final TvHolder holder, final int position) {
        holder.tvTitleTv.setText(listTv.get(position).getOriginalName());
        holder.tvOverviewTv.setText(listTv.get(position).getOverview());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Result data=listTv.get(position);
                Intent intent=new Intent(holder.itemView.getContext(), DetailTvActivity.class);
                intent.putExtra("movie",new GsonBuilder().create().toJson(data));
                holder.itemView.getContext().startActivity(intent);
            }
        });
        Picasso.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/w185/" + listTv.get(position).getPosterPath())
                .error(R.drawable.no_image)
                .placeholder(R.drawable.progress_animation)
                .into(holder.imageViewTv);

    }

    @Override
    public int getItemCount() {
        return listTv.size();
    }

    public class TvHolder extends RecyclerView.ViewHolder{
        ImageView imageViewTv;
        TextView tvTitleTv, tvOverviewTv;
        public TvHolder(@NonNull View itemView) {
            super(itemView);
            imageViewTv =itemView.findViewById(R.id.image_tvshow);
            tvTitleTv =itemView.findViewById(R.id.tv_title_tvshow);
            tvOverviewTv=itemView.findViewById(R.id.tv_description_tvshow);
        }
    }
}
