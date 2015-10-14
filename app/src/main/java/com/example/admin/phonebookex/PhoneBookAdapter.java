package com.example.admin.phonebookex;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jce on 8/10/15.
 */
public class PhoneBookAdapter extends BaseAdapter {

    Context context;
    ArrayList<PhoneBook> listData;

    public PhoneBookAdapter(Context context, ArrayList<PhoneBook> listData) {
        this.context = context;
        this.listData = listData;
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder {
        private TextView tvUserName, tvPhoneNumber, tvEmail;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item_phonebook, null);
            viewHolder = new ViewHolder();
            viewHolder.tvUserName = (TextView) view.findViewById(R.id.tvName);
            viewHolder.tvPhoneNumber = (TextView) view.findViewById(R.id.tvPhoneNumber);
            viewHolder.tvEmail = (TextView) view.findViewById(R.id.tvEmail);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        PhoneBook phoneBook = listData.get(position);
        String userName = phoneBook.getName();
        String phoneNumber = phoneBook.getPhoneNumber();
        String email = phoneBook.getEmail();
        viewHolder.tvUserName.setText(userName);
        viewHolder.tvPhoneNumber.setText(phoneNumber);
        viewHolder.tvEmail.setText(email);
        return view;
    }
}