package com.example.shoppinglist;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import data.*;



public class ItemDetailFragment extends Fragment implements OnClickListener {

	private EditText name;
	private EditText amount;
	private EditText comment;
	private Button saveButton;
	Callbacks mCallback;

	Item item;
	
	public interface Callbacks {
        public void onSave(Item item);
    }
	
	public void onAttach(Activity activity) {
        super.onAttach(activity);
        
        
       try {
            mCallback = (Callbacks) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement onSave");
        }
    }
	
	
	public ItemDetailFragment(){}
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		Bundle b = getArguments();
		if (b != null){
			item = new Item(b);
		}
	}
	
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		
		
		View view = inflater.inflate(R.layout.item_detail_fragment, container, false);
		
		if (item != null){
			
			name = (EditText) view.findViewById(R.id.ItemName);
			name.setText(item.getItemName());
			
			comment = (EditText) view.findViewById(R.id.ItemComment);
			comment.setText(item.getComment());
			
			
			amount = (EditText) view.findViewById(R.id.ItemAmount);
			amount.setText(item.getAmount());
			
			saveButton = (Button) view.findViewById(R.id.saveButton);
			saveButton.setOnClickListener(this);
			
			
			
		}
		
		
		
		return view;
		
	}

	public void onClick(View v) {
		switch(v.getId()){
		case R.id.saveButton:
			int id = item.getItemID();
			String newName = name.getText().toString();
			String newAmount = amount.getText().toString();
			String newComment = comment.getText().toString();
			Item item = new Item(newName, newAmount, newComment);
			item.setItemID(id);
			
			mCallback.onSave(item);
			
		
			}
	}
	
	
	
	
}
