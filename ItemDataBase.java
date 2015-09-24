package data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;






import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;



public class ItemDataBase {
	
	public static final String DB_NAME = "ShoppingList.db";
	public static final int DB_VERSION = 2;
	public static final String ITEM_TABLE = "item";	
	public static final String ITEM_ID = "_id";
	public static final int ITEM_ID_COL = 0;
	public static final String ITEM_NAME = "name";
	public static final int ITEM_NAME_COL = 1;
	public static final String ITEM_AMOUNT = "amount";
	public static final int ITEM_AMOUNT_COL = 2;
	public static final String ITEM_COMMENT = "comment";
	public static final int ITEM_COMMENT_COL = 3;
	public static final String ITEM_DONE = "done";
	public static final int ITEM_DONE_COL = 4;
	
	public static final String CREATE_ITEM_TABLE = 
			"CREATE TABLE " + ITEM_TABLE + " (" + 
			ITEM_ID 		+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + 
			ITEM_NAME    + " TEXT, " +
			ITEM_AMOUNT  + " TEXT, " +
			ITEM_COMMENT + " TEXT, " +
			ITEM_DONE    + " INTEGER);";
	
	public static final String DROP_ITEM_TABLE = "DROP TABLE IF EXISTS " + ITEM_TABLE;
	
	
	private static SQLiteDatabase db;
	private DBHelper dbHelper;
	
	
	
	//konstruktor
	public ItemDataBase(Context context){dbHelper = new DBHelper(context, DB_NAME, null, DB_VERSION);}
	
	private void openReadableDB(){db = dbHelper.getReadableDatabase();}
	private void openWritableDB(){db = dbHelper.getWritableDatabase();}
	private void closeDB(){if (db != null){db.close();}
	
	
	}
	 
	
    
 public List<Item> getItems() {
    List<Item> items = new ArrayList<Item>();
   
    
    String selectQuery = "SELECT  * FROM " + ITEM_TABLE;
 
    this.openWritableDB();
    Cursor cursor = db.rawQuery(selectQuery, null);
 

    if (cursor.moveToFirst()) {
        
    	do {
        	int itemID = cursor.getInt(ITEM_ID_COL);
        	String itemName = cursor.getString(ITEM_NAME_COL);
			String comment = cursor.getString(ITEM_COMMENT_COL);
			String amount = cursor.getString(ITEM_AMOUNT_COL);
			int done = cursor.getInt(ITEM_DONE_COL);
			
			Item item = new Item(itemName, amount, comment);
			item.setItemID(itemID);
			item.setDoneInt(done);
			
            
            
            items.add(item);
        } while (cursor.moveToNext());
    }

    return items;
}
	
	
		
	
	
	public void addItem(Item item){
		
		ContentValues content = new ContentValues();
		content.put(ITEM_NAME, item.getItemName());
		content.put(ITEM_AMOUNT, item.getAmount());
		content.put(ITEM_COMMENT, item.getComment());
		content.put(ITEM_DONE, item.getDoneInt());
		
		this.openWritableDB();
		db.insert(ITEM_TABLE, null, content);
		this.closeDB();

	}
	
	
	

	public void updateItem(Item item){

		String id = "_id= " + Integer.toString(item.getItemID());
		String name = item.getItemName();
		String amount = item.getAmount();
		String comment = item.getComment();
		
		ContentValues newValues = new ContentValues();
		newValues.put(ITEM_NAME, name);
		newValues.put(ITEM_AMOUNT, amount);
		newValues.put(ITEM_COMMENT, comment);
		
		this.openWritableDB();
		db.update(ITEM_TABLE, newValues, id, null);
		this.closeDB();
			
	}
	
	
	public void setDone(Item item){
		String id = "_id= " + Integer.toString(item.getItemID());
		String done = Integer.toString(item.getDoneInt());
		
		ContentValues newValues = new ContentValues();
		newValues.put(ITEM_DONE, done);
		
		this.openWritableDB();
		db.update(ITEM_TABLE, newValues, id, null);
		this.closeDB();
		
	}
	
	
	public int removeDone(){
		this.openWritableDB();
		int rows = db.delete(ITEM_TABLE, ITEM_DONE + "=" + "1", null);
		this.closeDB();
		return rows;
	}



	
	
	 class DBHelper extends SQLiteOpenHelper {
		public DBHelper(Context context, String name, CursorFactory factory, int version) {super(context, name, factory, version);}
		
		public void onCreate(SQLiteDatabase db){db.execSQL(CREATE_ITEM_TABLE);}
		
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
			db.execSQL(ItemDataBase.DROP_ITEM_TABLE);
			onCreate(db);
		}
		
	}
	 
	 
	
		
}
