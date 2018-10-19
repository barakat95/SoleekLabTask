package com.example.barakat.soleeklab.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.barakat.soleeklab.R;
import com.example.barakat.soleeklab.model.Countries;

import java.util.List;

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.CountriesViewHolder> {

    Context context;
    int rawLayout;
    List<Countries> countries;

    public CountriesAdapter(Context context, int rawLayout, List<Countries> responseList) {
        this.context = context;
        this.rawLayout = rawLayout;
        this.countries = responseList;
    }

    @Override
    public CountriesAdapter.CountriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rawLayout, parent, false);
        return new CountriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CountriesViewHolder holder, int position) {
        holder.name.setText(countries.get(position).getCountryName());
        holder.capital.setText(countries.get(position).getCapitalName());
        holder.region.setText(countries.get(position).getCountryRegion());
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public static class CountriesViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView capital;
        TextView region;

        public CountriesViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            capital = itemView.findViewById(R.id.capital);
            region = itemView.findViewById(R.id.region);
        }
    }


}
