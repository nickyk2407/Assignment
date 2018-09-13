package com.example.nicky.assignment.ui;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nicky.assignment.R;
import com.example.nicky.assignment.network.Planets;

/**
 * Created by NICKY on 13-09-
 */

public class PlanetPagedAdaptor extends PagedListAdapter<Planets, PlanetPagedAdaptor.ViewHolder> {
    private final Callback callback;
    private Context context;

    public PlanetPagedAdaptor(ShowDiffUtilItemCallback showDiffUtilItemCallback, Callback callback) {
        super(showDiffUtilItemCallback);
        this.callback = callback;
    }

    @NonNull
    @Override
    public PlanetPagedAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
//        PlanetItemListBinding itemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
//                R.layout.planet_item_list, parent, false);
        View view = View.inflate(parent.getContext(), R.layout.adapter_planet_item_list, null);
        return new ViewHolder(view);
        //return new ViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanetPagedAdaptor.ViewHolder holder, int position) {
        Planets planet = getItem(position);
        holder.mTitle.setText(planet.getName());

        /*if (planet.image() != null) {
            Glide.with(context).load(planet.image().get("original"))
                    .apply(RequestOptions.placeholderOf(R.color.grey))
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(holder.binding.showImage);
        }*/

    }


    public interface Callback {
        void onItemClicked(Planets planet);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTitle;

        public ViewHolder(View binding) {
            super(binding);
            mTitle = binding.findViewById(R.id.title);
            binding.setOnClickListener(v -> callback.onItemClicked(getItem(getAdapterPosition())));

        }
    }
}
