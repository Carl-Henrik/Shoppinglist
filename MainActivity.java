package com.example.shoppinglist;



import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import data.Item;


public class MainActivity extends ActionBarActivity implements ItemListFragment.Callbacks, ItemDetailFragment.Callbacks, OnClickListener{

	
	
	public static final String ITEM_BUNDLE = "ITEM_BUNDLE";
	private static final int REQUEST_CODE = 1001;
	private boolean isTwoPane = false;
	public Button adButton;
	public Button smsButton;
	public Button removeButton;
	String smsBody="Sms Body";
	Intent sendIntent = new Intent(Intent.ACTION_VIEW);
	
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		if(findViewById(R.id.detailContainer) !=null){
			isTwoPane = true; 
			
			adButton = (Button) findViewById(R.id.adItem2);
			adButton.setOnClickListener(this);
			removeButton = (Button) findViewById(R.id.removeDone2);
			removeButton.setOnClickListener(this);
			smsButton = (Button) findViewById(R.id.sendText2);
			smsButton.setOnClickListener(this);
		}
		
		else {adButton = (Button) findViewById(R.id.adItem);
		adButton.setOnClickListener(this);
		removeButton = (Button) findViewById(R.id.removeDone);
		removeButton.setOnClickListener(this);
		smsButton = (Button) findViewById(R.id.sendText);
		smsButton.setOnClickListener(this);
		
		}
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


	
	
	public void onItemSelected(Item item) {
		Bundle b = item.toBundle();
		
		if (isTwoPane){
			ItemDetailFragment fragment = new ItemDetailFragment();
			fragment.setArguments(b);
			getFragmentManager().beginTransaction().replace(R.id.detailContainer, fragment).commit();
			
		}
		
		else{
		
		Intent intent = new Intent(this, ItemDetailActivity.class);
		intent.putExtra(ITEM_BUNDLE, b);
		startActivityForResult(intent, REQUEST_CODE);
		
		}
	}

		public ItemListFragment getItemListFragment(){
			android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
			ItemListFragment fragment = (ItemListFragment)fm.findFragmentById(R.id.listFragment);
			return fragment;
		}
	
	
		/*public void updateItem(Item item){
			getItemListFragment().updateItem(item);
		}*/
	
	
		public void onClick(View v) {
		switch(v.getId()){
		
		
		case R.id.adItem:
				
			getItemListFragment().addItem();
			break;
				
		
		case R.id.adItem2:
			
			getItemListFragment().addItem();
			break;
			
		
		
		case R.id.removeDone:
			
			getItemListFragment().removeDone();
			break;
			
		case R.id.removeDone2:
	
			getItemListFragment().removeDone();
			break;
			
			
		
			
		case R.id.sendText:
			
			smsBody = getItemListFragment().getSms();
			sendIntent.putExtra("sms_body", smsBody);
		    sendIntent.setType("vnd.android-dir/mms-sms");
		    startActivity(sendIntent);
		    break; 
		     
		case R.id.sendText2:
			
			smsBody = getItemListFragment().getSms();
			sendIntent.putExtra("sms_body", smsBody);
		    sendIntent.setType("vnd.android-dir/mms-sms");
		    startActivity(sendIntent);
		    break;    
			
		} 
		
		}


		@Override
		public void onSave(Item item) {
			
			getItemListFragment().updateItem(item);
			
		}
		
		public void onResume(){
			super.onResume();
			getItemListFragment().refresh();
		}
		
		
}
