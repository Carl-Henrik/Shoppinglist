package data;

import android.os.Bundle;

public class Item {
	
	public static final String ITEM_ID = "itemID";
	public static final String ITEM_NAME = "itemName";
	public static final String AMOUNT = "amount";
	public static final String COMMENT = "comment";
	public static final String DONE = "done";
	
	private int itemID;
	private String itemName;
	private String comment;
	private String amount;
	private boolean done;
	private String isDone;
	
	
	
	public int getItemID(){return itemID;}
	public void setItemID(int itemID){this.itemID = itemID;}
	
	public String getItemName(){return itemName;}
	public void setItemName(String itemName){this.itemName = itemName;}
	
	public String getComment(){return comment;}
	public void setComment(String comment){this.comment = comment;}
	
	public String getAmount(){return amount;}
	public void setAmount(String amount){this.amount = amount;}
	
	public boolean getDone(){return done;}
	public void setDone(boolean done){this.done = done;}
	
	public int getDoneInt(){if (done){return 1;} else{return 0;}}
	public void setDoneInt(int done){if (done == 1){this.done = true;} else{this.done = false;}}
	
	
	
	

	public Item(String name, String amount, String comment){
		
		this.itemName = name;
		this.amount = amount;
		this.comment = comment;
		
	}
	
	public Item(Bundle b){
		if (b != null) {
			this.itemID = b.getInt(ITEM_ID);
			this.itemName = b.getString(ITEM_NAME);
			this.amount = b.getString(AMOUNT);
			this.comment = b.getString(COMMENT);
			this.done = b.getBoolean(DONE);
		}
	}
	
	
	public Bundle toBundle(){
		Bundle b = new Bundle();
		b.putInt(ITEM_ID, this.itemID);
		b.putString(ITEM_NAME, this.itemName);
		b.putString(AMOUNT, this.amount);
		b.putString(COMMENT, this.comment);
		b.putBoolean(DONE, this.done);
		return b;
	}
	
	public String toString() {
		if (done){ isDone = "Done!";}
		else {isDone = "Not done.";}
		
		return amount + "x " + itemName + ". " + isDone + " " + comment;
	}
	
	
}
