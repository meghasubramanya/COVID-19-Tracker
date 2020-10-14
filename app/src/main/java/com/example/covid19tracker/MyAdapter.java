package com.example.covid19tracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends ArrayAdapter<CountryModel>
{

    private Context context;
    private List<CountryModel> countryModelsList;
    private List<CountryModel> countryModelsListFiltered;

    public MyAdapter(@NonNull Context context,  List<CountryModel> countryModelsList) {
        super(context, R.layout.row_layout,countryModelsList);

        this.context=context;
        this.countryModelsList=countryModelsList;
        this.countryModelsListFiltered=countryModelsList;        //for search button.
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view= LayoutInflater.from(getContext()).inflate(R.layout.row_layout,null,true);
        TextView tvCountryName=view.findViewById(R.id.tvCountryName);
        ImageView ivFlag=view.findViewById(R.id.ivFlag);

        tvCountryName.setText(countryModelsListFiltered.get(position).getCountry());
        Glide.with(context).load(countryModelsListFiltered.get(position).getFlag()).into(ivFlag);

        return view;
    }

    @Override
    public int getCount() {
        return countryModelsListFiltered.size();
    }

    @Nullable
    @Override
    public CountryModel getItem(int position) {
        return countryModelsListFiltered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public Filter getFilter() {
       Filter filter=new Filter() {
           @Override
           protected FilterResults performFiltering(CharSequence charSequence) {

               FilterResults filterResults=new FilterResults();
               if(charSequence==null||charSequence.length()==0)
               {
                   filterResults.count=countryModelsList.size();
                   filterResults.values=countryModelsList;
               }
               else
               {
                   List<CountryModel> resultsModel=new ArrayList<>();
                   String searchStr=charSequence.toString().toLowerCase();

                   for(CountryModel itemsModel:countryModelsList)
                   {
                       if(itemsModel.getCountry().toLowerCase().contains(searchStr))
                       {
                           resultsModel.add(itemsModel);
                       }
                       filterResults.count=resultsModel.size();
                       filterResults.values=resultsModel;
                   }
               }
               return filterResults;
           }

           @Override
           protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

               countryModelsListFiltered=(List<CountryModel>) filterResults.values;
               AffectedCountries.countryModelsList=(List<CountryModel>) filterResults.values;
               notifyDataSetChanged();
           }
       };

       return filter;
    }
}
