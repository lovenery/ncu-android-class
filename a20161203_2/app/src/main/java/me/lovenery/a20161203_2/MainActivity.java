package me.lovenery.a20161203_2;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase db = null;
    private final static String CREATE_TABLE = "CREATE TABLE table02(_id INTEGER PRIMARY KEY, name TEXT, price INTEGER)";
    ListView lv1;
    Button btnSearchData, btnSearchAll;
    EditText etID;
    Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv1 = (ListView) findViewById(R.id.lv1);
        btnSearchData = (Button) findViewById(R.id.btnSearchData);
        btnSearchAll = (Button) findViewById(R.id.btnSearchAll);
        etID = (EditText) findViewById(R.id.etID);

        btnSearchData.setOnClickListener(myListener);
        btnSearchAll.setOnClickListener(myListener);
        lv1.setOnItemClickListener(lv1Listener);

        db = openOrCreateDatabase("db02.db", MODE_PRIVATE, null); // 建立或開啟資料庫檔案
        // 新增資料表 插入資料
        try {
            db.execSQL("DROP TABLE IF EXISTS table02");
            db.execSQL(CREATE_TABLE);
            db.execSQL("INSERT INTO table02 (name, price) values ('牛肉麵', 120)");
            db.execSQL("INSERT INTO table02 (name, price) values ('肉絲麵', 50)");
            db.execSQL("INSERT INTO table02 (name, price) values ('乾麵', 35)");
            db.execSQL("INSERT INTO table02 (name, price) values ('大滷麵', 60)");
        } catch (Exception e) {}

        // 預設顯示全部
        cursor = getAll();
        UpdateAdapter(cursor);
    }

    private ListView.OnItemClickListener lv1Listener = new ListView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            cursor.moveToPosition(position);
            Cursor c = get(id);
            String s = "id= " + (id) + "\r\n" + "name=" + c.getString(1) + "\r\n" + "price=" + c.getInt(2);
            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
        }
    };

    // 關閉資料庫
    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }


    private Button.OnClickListener myListener = new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
            try {
                switch (view.getId()) {
                    case R.id.btnSearchData:
                    {
                        long id = Integer.parseInt(etID.getText().toString());
                        cursor = get(id);
                        UpdateAdapter(cursor);
                        break;
                    }
                    case R.id.btnSearchAll:
                    {
                        cursor = getAll();
                        UpdateAdapter(cursor);
                        break;
                    }
                }
            } catch (Exception err) {
                Toast.makeText(getApplicationContext(), "拜託輸入數字！", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private void UpdateAdapter(Cursor cursor) {
        // 當 Cursor有東西並且數量大於零
        if (cursor != null && cursor.getCount() >= 0) {
            SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.mylayout , // 兩個資料項
                    cursor, new String[]{"_id", "name", "price"},
                    new int[] {R.id.txtID, R.id.txtName, R.id.txtPrice}, 0);
            // SimpleCursorAdapter 負責橋接顯示界面元件ListView與Cursor
            // 參數1: 本應用程式
            // 參數2: 顯示的介面元件配置資源檔
            // 參數3: cursor為要顯示的資料
            // 參數4: 要顯示的欄位，_id name price
            // 參數5: 以自訂的mylayout, 顯示界面元件對應的欄位
            // 參數6: 代表flag(旗標)，自Android 4.X版後設定為0，可取得更好地執行效率。
            lv1.setAdapter(adapter);
        }
    }

    public Cursor getAll() {
        Cursor cursor = db.rawQuery("SELECT _id, name, price FROM table02", null);
        return cursor;
    }

    public Cursor get(long rowId) throws SQLException {
        Cursor cursor = db.rawQuery("SELECT _id, name, price FROM table02 WHERE _id = " + rowId, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst(); // 移動到第一筆
        } else {
            Toast.makeText(getApplicationContext(), "查無此筆資料！", Toast.LENGTH_SHORT).show();
        }
        return cursor;
    }
}
