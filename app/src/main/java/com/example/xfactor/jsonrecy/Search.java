package com.example.xfactor.jsonrecy;

import android.widget.Filter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sarthak on 22-07-2015.
 */
public class Search extends Filter {

    ArrayList<item> fin;

    public Search(ArrayList<item> fin) {
        this.fin = fin;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();
        // We implement here the filter logic
        if (constraint == null || constraint.length() == 0) {
            // No filter implemented we return all the list
            results.values = fin;
            results.count = fin.size();
        } else {
            // We perform filtering operation
            List<item> nPlanetList = new ArrayList<item>();

            for (item p : fin) {
                if (p.getContent().toUpperCase().startsWith(constraint.toString().toUpperCase()))
                    nPlanetList.add(p);
            }

            results.values = nPlanetList;
            results.count = nPlanetList.size();

        }
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults filterResults) {

        if (filterResults.count == 0)
        {

        }
        else {
            fin = (ArrayList<item>) filterResults.values;

        }
    }
}