package me.lovenery.a20161203_3;

import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

// http://blog.xuite.net/gkyo0510/wretch/237970377-SQLite+%E6%96%B0%E5%A2%9E+%E4%BF%AE%E6%94%B9+%E5%88%AA%E9%99%A4+(%2BDB%E8%A8%AD%E5%AE%9A)
public class MainActivity extends AppCompatActivity {

    private MyDB db = null;

    Button btnAppend, btnEdit, btnDelete, btnClear;
    EditText edtName, edtPrice;
    ListView lv1;
    Cursor cursor;
    long myid; // 用來記錄資料的id

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAppend = (Button) findViewById(R.id.btnAppend);
        btnEdit = (Button) findViewById(R.id.btnEdit);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnClear = (Button) findViewById(R.id.btnClear);
        edtName = (EditText) findViewById(R.id.edtName);
        edtPrice = (EditText) findViewById(R.id.edtPrice);
        lv1 = (ListView) findViewById(R.id.lv1);

        btnAppend.setOnClickListener(myListener);
        btnEdit.setOnClickListener(myListener);
        btnDelete.setOnClickListener(myListener);
        btnClear.setOnClickListener(myListener);

        lv1.setOnItemClickListener(lvListener);

        db = new MyDB(this);
        db.open();
        cursor = db.getAll();
        UpdateAdapter(cursor);
    }

    private ListView.OnItemClickListener lvListener = new ListView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            ShowData(id);
            cursor.moveToPosition(position);
        }
    };

    private void ShowData(long id) {
        Cursor c = db.get(id);
        myid = id;
        edtName.setText(c.getString(1));
        edtPrice.setText("" + c.getInt(2));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }

    private Button.OnClickListener myListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                switch (v.getId()) {
                    case R.id.btnAppend: {
                        String name = edtName.getText().toString();
                        int price = Integer.parseInt(edtPrice.getText().toString());
                        if (db.append(name, price) > 0) {
                            cursor = db.getAll();
                            UpdateAdapter(cursor);
                            ClearEdit();
                        }
                        break;
                    }
                    case R.id.btnEdit: {
                        String name = edtName.getText().toString();
                        int price = Integer.parseInt(edtPrice.getText().toString());
                        if (db.update(myid, name, price)) {
                            cursor = db.getAll();
                            UpdateAdapter(cursor);
                        }
                        break;
                    }
                    case R.id.btnDelete: {
                        if (cursor != null && cursor.getCount() >= 0) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                            builder.setTitle("確定刪除");
                            builder.setMessage("確定要刪除" + edtName.getText() + "這筆資料嗎？");
                            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                            builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    if (db.delete(myid)) {
                                        cursor = db.getAll();
                                        UpdateAdapter(cursor);
                                        ClearEdit();
                                    }
                                }
                            });
                            builder.show();
                        }
                        break;
                    }
                    case R.id.btnClear: {
                        ClearEdit();
                        break;
                    }
                }
            } catch (Exception err) {
                Toast.makeText(getApplicationContext(), "資料不正確！", Toast.LENGTH_LONG).show();
            }
        }
    };

    public void ClearEdit() {
        edtName.setText("");
        edtPrice.setText("");
    }

    public void UpdateAdapter(Cursor cursor) {
        if (cursor != null && cursor.getCount() >= 0) {

            SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2,
                    cursor, new String[] {"name", "price"}, new int[] {android.R.id.text1, android.R.id.text2}, 0);
            lv1.setAdapter(adapter);
        }
    }
}
