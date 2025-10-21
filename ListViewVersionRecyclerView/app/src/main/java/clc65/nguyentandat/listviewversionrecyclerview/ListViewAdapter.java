package clc65.nguyentandat.listviewversionrecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.ItemListHoder> {
    Context context;
    ArrayList<ListView> lsDatas;

    public ListViewAdapter(Context _context, ArrayList<ListView> _lsDatas) {
        this.context = _context;
        this.lsDatas = _lsDatas;
    }

    @NonNull
    @Override
    public ItemListHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater cai_bom = LayoutInflater.from(context);
        View vItem = cai_bom.inflate(R.layout.textview, parent, false );
        ItemListHoder holder = new ItemListHoder(vItem);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemListHoder holder, int position) {
        ListView listView = lsDatas.get(position);
        String caption = String.valueOf(listView.getTvSubject());
        holder.tvTitle.setText(caption);
    }

    @Override
    public int getItemCount() {
        return lsDatas.size();
    }

    class ItemListHoder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        public ItemListHoder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
        }
    }
}
