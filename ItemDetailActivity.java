package com.example.shoppinglist;

import data.Item;
import data.ItemDataBase;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;

public class ItemDetailActivity extends FragmentActivity implements  ItemDetailFragment.Callbacks{
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_detail);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		if (savedInstanceState == null) {
			
			ItemDetailFragment fragment = new ItemDetailFragment();
			
			Bundle b = getIntent().getBundleExtra(MainActivity.ITEM_BUNDLE);
			fragment.setArguments(b);
			
			getFragmentManager().beginTransaction().add(R.id.detailContainer, fragment).commit();
			
		}
	}
	
	
	public boolean onOptionsItemSelected(MenuItem item){
		if (item.getItemId() == android.R.id.home){
			finish();
		}
		
		return true;
	}


	@Override
	public void onSave(Item item) {
		new ItemDataBase(this).updateItem(item);
		//getItemListFragment().refresh();
		
	}
	
	public ItemListFragment getItemListFragment(){
		android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
		ItemListFragment fragment = (ItemListFragment)fm.findFragmentById(R.id.listFragment);
		return fragment;
	}
}
