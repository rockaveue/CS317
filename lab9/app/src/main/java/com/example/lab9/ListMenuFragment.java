package com.example.lab9;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by tutlane on 06-08-2017.
 */
public class ListMenuFragment extends ListFragment {
    String[] users = new String[] { "Архангай","Баян-Өлгий","Булган","Дархан-уул","Дорноговь","Дорнод", "Сэлэнгэ", "Орхон" };
    String[] location = new String[]{"Цэцэрлэг","Өлгий","Булган","Дархан","Сайншанд","Чойбалсан", "Сүхбаатар","Эрдэнэт"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.listitems_info, container, false);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, users);
        setListAdapter(adapter);
        return view;
    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        DetailFragment txt = (DetailFragment)getFragmentManager().findFragmentById(R.id.fragment2);
        txt.change("Аймаг : "+ users[position],"Нийслэл : "+ location[position]);
        getListView().setSelector(android.R.color.holo_blue_dark);
    }
}