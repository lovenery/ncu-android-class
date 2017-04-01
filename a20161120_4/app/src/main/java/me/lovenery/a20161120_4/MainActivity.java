package me.lovenery.a20161120_4;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv1;
    private Button btn1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = (TextView) findViewById(R.id.tv1);
        btn1 = (Button) findViewById(R.id.btn1);

        btn1.setOnClickListener(btnlistener);
    }

    private Button.OnClickListener btnlistener = new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
            SQLiteDatabase db = openOrCreateDatabase("db1.db", MODE_PRIVATE, null);

            tv1.setText("資料庫檔路徑：" + db.getPath() + "\n資料庫分頁大小：" + db.getPageSize() + "\n資料庫上限：" + db.getMaximumSize());
            db.close();
        }
    };
}
