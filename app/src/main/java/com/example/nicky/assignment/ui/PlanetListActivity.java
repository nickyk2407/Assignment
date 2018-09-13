package com.example.nicky.assignment.ui;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.nicky.assignment.R;
import com.example.nicky.assignment.databinding.ActivityMainBinding;
import com.example.nicky.assignment.network.Planets;
import com.example.nicky.assignment.viewmodule.PlanetListViewModule;

import javax.inject.Inject;

public class PlanetListActivity extends BaseActivity implements PlanetPagedAdaptor.Callback {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private PlanetListViewModule planetListModule;
    private ActivityMainBinding binding;
    private PlanetPagedAdaptor planetPagedAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        planetListModule = ViewModelProviders.of(this, viewModelFactory).get(PlanetListViewModule.class);
        planetListModule.onScreenCreated();
        initAdapter();
    }

    private void initAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        planetPagedAdapter = new PlanetPagedAdaptor(new ShowDiffUtilItemCallback(), this);
        binding.planets.setLayoutManager(layoutManager);
        binding.planets.setAdapter(planetPagedAdapter);
        planetListModule.getPlanets().observe(this, this::showPlanetData);
    }

    private void showPlanetData(PagedList<Planets> planets) {
        planetPagedAdapter.submitList(planets);
        binding.planets.setVisibility(View.VISIBLE);
    }

    @Override
    public void onItemClicked(Planets planets) {
        Intent intent = new Intent(this, PlanetDetailActivity.class);
        intent.putExtra("planet", planets);
        startActivity(intent);
    }
}
