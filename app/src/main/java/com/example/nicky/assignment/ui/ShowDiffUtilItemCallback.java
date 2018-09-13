package com.example.nicky.assignment.ui;

import android.support.v7.util.DiffUtil;

import com.example.nicky.assignment.network.Planets;

public class ShowDiffUtilItemCallback extends DiffUtil.ItemCallback<Planets> {
    @Override
    public boolean areItemsTheSame(Planets oldItem, Planets newItem) {
        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(Planets oldItem, Planets newItem) {
        return oldItem.getId() == newItem.getId();
    }
}
