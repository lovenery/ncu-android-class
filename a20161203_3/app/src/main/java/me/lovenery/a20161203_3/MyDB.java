package me.lovenery.a20161203_3;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.SQLException;
import android.content.Context;

public class MyDB {

    public SQLiteDatabase db = null; // 資料庫類別
    private final static String DATABASE_NAME = "db1.db";// 資料庫名稱
    private final static String TABLE_NAME = "table03"; // 資料表名稱
    private final static String _ID = "_id"; // 資料表欄位/
    private final static String NAME = "name";
    private final static String PRICE = "price";
    /* 建立資料表的欄位 */
    private final static String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + _ID
            + " INTEGER PRIMARY KEY," + NAME + " TEXT,"+ PRICE + " INTEGER)";

    private Context mCtx = null;
    public MyDB(Context ctx){ // 建構式
        this.mCtx = ctx; // 傳入 建立物件的 Activity
    }

    public void open() throws SQLException {
        db = mCtx.openOrCreateDatabase(DATABASE_NAME, 0, null);
        try {
            db.execSQL(CREATE_TABLE);
        } catch ( Exception e) {}
    }

    public void close() {
        db.close();
    }

    // 查詢所有資料，取出所有欄位
    public Cursor getAll() {
        return db.query(TABLE_NAME, new String[] {_ID, NAME, PRICE}, null, null, null, null, null, null);
    }

    // 查詢指令 ID 的資料
    public Cursor get(long rowId) throws SQLException {
        Cursor mCursor = db.query(TABLE_NAME, new String[] {_ID, NAME, PRICE}, _ID + "=" + rowId, null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    // 新增一筆資料
    public long append(String name, int price) {
        ContentValues args = new ContentValues();
        args.put(NAME, name);
        args.put(PRICE, price);
        return db.insert(TABLE_NAME, null, args);
    }

    // 刪除指定資料
    public boolean delete(long rowId) {
        return db.delete(TABLE_NAME, _ID + "=" + rowId, null) > 0;
    }

    // 修改指定資料
    public boolean update(long rowId, String name,int price) {
        ContentValues args = new ContentValues();
        args.put(NAME, name);
        args.put(PRICE, price);
        return db.update(TABLE_NAME, args,_ID + "=" + rowId, null) > 0;
    }
}
