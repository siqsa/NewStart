package com.example.amdin.newstart;

/**
 * Created by amdin on 2017-11-22.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {//뷰홀더란?->뷰를 담아 두는 뷰 보관 상자

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView showDate;
        public TextView showContents;
        private ItemAdapter mContacts;
        private Context context;

        public ViewHolder(Context context, View itemView, ItemAdapter contacts) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            showDate = itemView.findViewById(R.id.date);
            showContents = itemView.findViewById(R.id.context);
            this.context = context;
            this.mContacts = contacts;
        }
    }

    private List<Item> mContacts;
    // Store the context for easy access
    private Context mContext;

    // Pass in the contact array into the constructor
    public ItemAdapter(Context context, List<Item> contacts) {
        mContacts = contacts;
        mContext = context;
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {//아이템을 위한 뷰를 만들어서 뷰홀더에 넣어서 리턴을 한다.

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_list, parent, false);
        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(context, contactView, this);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(ItemAdapter.ViewHolder viewHolder, int position) {//뷰홀더에 뷰에 position에 해당하는 데이터를 입력한다.
        // Get the data model based on position
        final Item contact = mContacts.get(position);
        // Set item views based on your views and data model
        TextView textView = viewHolder.showDate;
        String a = contact.getYear() + "년" + contact.getMonth() + "월" + contact.getDay() + "일";
        textView.setText(a);
        TextView textView1 = viewHolder.showContents;
        textView1.setText(contact.getText());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (mContext, DetailViewActivity.class);
                intent.putExtra("item", contact);
                mContext.startActivity(intent);
            }
        });
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    public void removeItem(int p) {
        mContacts.remove(p);
        notifyItemRemoved(p);

    }

}