package com.htw.songfinder.adapter;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.htw.songfinder.callback.RecyclerViewClickListener;
import com.htw.songfinder.R;
import com.htw.songfinder.databinding.RecyclerViewItemBinding;

import java.util.Collections;
import java.util.List;

public class RecyclerMainAdapter extends RecyclerView.Adapter<RecyclerMainAdapter.SongHolder> {

    private LayoutInflater layoutInflater;
    RecyclerViewClickListener recyclerViewClickListener;

    public RecyclerMainAdapter(Context context, RecyclerViewClickListener recyclerViewClickListener) {
        this.recyclerViewClickListener = recyclerViewClickListener;
    }

    private List<String> localArtistNameList  = Collections.emptyList();
    private List<String> localSourceList  = Collections.emptyList();

    public void setProjectList (final List<Pair<String,String>> list ){
        if (list != null){
            localArtistNameList = Stream.of(list).map(p -> p.first).collect(Collectors.toList());
            localSourceList = Stream.of(list).map(p -> p.second).collect(Collectors.toList());
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public SongHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (layoutInflater ==null){
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        RecyclerViewItemBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.recycler_view_main_item,parent,false);

        return new SongHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SongHolder holder, int possition) {


        holder.binding.setModelItem(localArtistNameList.get(possition));
        // Source imageSwicher
        holder.binding.sourceIcon.setBackgroundResource(
                Stream.of(localSourceList.get(possition)).anyMatch((s) -> s.startsWith("https://is")) ? R.drawable.icon_source_itune : R.drawable.icon_source_lastfm);

        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return localArtistNameList.size();
    }

    class SongHolder extends RecyclerView.ViewHolder  {

        private final RecyclerViewItemBinding binding ;

        public SongHolder(@NonNull  RecyclerViewItemBinding itembinding) {
            super(itembinding.getRoot());
            this.binding = itembinding;

            itemView.setOnClickListener(view -> {
                recyclerViewClickListener.recyclerViewListClicked(view,getLayoutPosition());
            });

        }

    }

}
