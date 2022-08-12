package com.example.paginate.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.paginate.R;
import com.example.paginate.data.Person;

import java.util.List;
import java.util.Locale;

public class RecyclerPersonAdapter extends RecyclerView.Adapter<RecyclerPersonAdapter.PersonVH> implements RecyclerOnItemClickListener {

    private static final String TAG = "RecyclerPersonAdapter";

    private final List<Person> data;

    public RecyclerPersonAdapter(List<Person> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public PersonVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_list_item, parent, false);
        return new PersonVH(view, this);
    }

    @Override
    public void onBindViewHolder(PersonVH holder, final int position) {
        Person person = data.get(position);
        holder.tvFullName.setText(
            String.format(Locale.getDefault(), "%s %s, %d", person.getFirstName(), person.getLastName(), person.getAge())
        );
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        Log.d(TAG, "onAttachedToRecyclerView");
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        Log.d(TAG, "onDetachedFromRecyclerView");
    }

    @Override
    public void onItemClicked(View view, int position) {
        Toast.makeText(view.getContext(), "Clicked position: " + position, Toast.LENGTH_SHORT).show();
        this.data.remove(position);
        notifyItemRemoved(position);
    }

    public void add(List<Person> items) {
        data.addAll(items);
        notifyDataSetChanged();
    }

    public void replace(List<Person> items) {
        data.clear();
        data.addAll(items);
        notifyDataSetChanged();
    }

    public static class PersonVH extends RecyclerView.ViewHolder {
        TextView tvFullName;

        public PersonVH(View view, final RecyclerOnItemClickListener recyclerOnItemClickListener) {
            super(view);
            this.tvFullName = (TextView) view.findViewById(R.id.tv_full_name);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recyclerOnItemClickListener != null) {
                        recyclerOnItemClickListener.onItemClicked(v, getAdapterPosition());
                    }
                }
            });
        }
    }

}