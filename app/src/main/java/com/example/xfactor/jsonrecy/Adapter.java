package com.example.xfactor.jsonrecy;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;


/**
 * Created by Sarthak on 13-07-2015.
 */
public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> implements  Filterable{

    private LayoutInflater inflater;
    Context context;
    ArrayList<item> fin = new ArrayList<item>();


    Adapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    public void list(ArrayList<item> fin) {
        this.fin = fin;
        Search search = new Search(fin);
        notifyItemRangeChanged(0, fin.size());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = inflater.inflate(R.layout.rac, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        item current = fin.get(i);
        viewHolder.content.setText(current.content);

        //  Uri uri = Uri.parse(current.url);
        //  Context context = viewHolder.imageView.getContext();
        Picasso.with(context).load(current.url).resize(240, 320).into(viewHolder.imageView);
//        Async async = new Async(context);
        //      async.execute(current.url);
    }

    @Override
    public int getItemCount() {
        return fin.size();
    }

    @Override
    public Filter getFilter() {
        Search search = new Search(fin);
        return search;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView content;
        ImageView imageView;
        ImageButton imageButton;

        public ViewHolder(View itemView) {
            super(itemView);

            content = (TextView) itemView.findViewById(R.id.textView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            imageButton = (ImageButton) itemView.findViewById(R.id.imageButton);
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (view.equals(imageButton)) {
                        remove(getAdapterPosition());
                    }
                }
            });

        }

        private void remove(int adapterPosition) {
            fin.remove(adapterPosition);
            notifyItemRemoved(adapterPosition);
            notifyItemRangeChanged(getAdapterPosition(), fin.size());
        }
    }

}
