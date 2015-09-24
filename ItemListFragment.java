package com.example.shoppinglist;
import java.util.List;

import data.Item;
import data.ItemDataBase;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

public class ItemListFragment extends ListFragment  {


	public static List<Item> items;
	public static ItemDataBase db; 
	public static ItemsArrayAdapter adapter;
	private Callbacks activity;
	
	public ItemListFragment(){}
	
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
				
		db = new ItemDataBase(getActivity());
		items = db.getItems(); 
				
		adapter = new ItemsArrayAdapter(getActivity(), R.layout.item_listitem, db.getItems());
		setListAdapter(adapter);
	
	}
	
	
	
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
			View rootView = inflater.inflate(R.layout.item_list_fragment, container, false);
			return rootView;
	}
	
	
	public interface Callbacks{public void onItemSelected(Item item);}
	
	
	public void onListItemClick(ListView l, View v, int position, long id){
		Item item = items.get(position);
		activity.onItemSelected(item);
	}
	
	
	
	public void onAttach(Activity activity){
		super.onAttach(activity);
		this.activity = (Callbacks) activity;
	}
	
		
	
	public void addItem(){
		db.addItem(new Item("Name", "0", "Comments"));
		refresh();
	}
	
	
	public void updateItem(Item item){
		db.updateItem(item);
		refresh();
	}
	
	public void setDone(Item item){
		db.setDone(item);
		refresh();
	}
	public void removeDone(){
		int rows = db.removeDone();
		
		Toast.makeText(getActivity(), Integer.toString(rows) + "items removed!", Toast.LENGTH_LONG).show();
		refresh();
	}
	
	
	public void refresh(){
		//Toast.makeText(getActivity(), "refresh damnit!", Toast.LENGTH_LONG).show();
		items = db.getItems();
		adapter = new ItemsArrayAdapter(getActivity(), R.layout.item_listitem, db.getItems());
		setListAdapter(adapter);
	}

	public String getSms(){
		
		items = db.getItems();
	
		String sms = "";
		
		for (int i = 0; i < items.size(); i++) {
			if (!items.get(i).getDone()){sms = sms + items.get(i).toString() + "\n";}
		}
		
		return sms;
	}


		
	
	
	
}
