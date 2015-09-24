package com.example.shoppinglist;

import java.util.List;

import data.Item;
import data.ItemDataBase;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;





public class ItemsArrayAdapter extends ArrayAdapter<Item> {
	
	private Context context;
	private List<Item> objects;
	 
	


	
		public ItemsArrayAdapter(Context context, int resource, List<Item> objects){
			
			super(context, resource, objects);
			this.context = context;
			this.objects = objects;
		}
		
		public View getView(int position, View convertView, ViewGroup parent){
			final Item item = objects.get(position);
		
			
			LayoutInflater inflater =
					(LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			View view = inflater.inflate(R.layout.item_listitem, null);
			
			TextView name = (TextView) view.findViewById(R.id.ListItemName);
			name.setText(item.getItemName());
			
			TextView amount = (TextView) view.findViewById(R.id.ListItemAmount);
			amount.setText("x" + item.getAmount());
			
			final CheckBox done = (CheckBox) view.findViewById(R.id.ListItemDone);
			done.setChecked(item.getDone());
			done.setOnClickListener(new OnClickListener(){
				public void onClick(View v) {
	                	
								
					
	                if(done.isChecked()){
	                	item.setDone(true);
	                	new ItemDataBase(context).setDone(item);
	                	

	                }
	                
	                else{
	                	item.setDone(false);
	                	new ItemDataBase(context).setDone(item);
	                	
	                }
	            }
	        });
			
			return view;
			
		}


		
		
}
