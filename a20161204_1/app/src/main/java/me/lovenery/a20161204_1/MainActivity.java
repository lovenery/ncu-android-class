package me.lovenery.a20161204_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

// http://blog.xuite.net/gkyo0510/wretch/237050286-Login+%E7%99%BB%E5%85%A5%E7%B3%BB%E7%B5%B1
// ch12-ExInputdata
public class MainActivity extends AppCompatActivity {

    private EditText edtId, edtPw, edtContent;
    private Button btnAdd, btnClear, btnEnd;
    private static final String FILENAME = "login.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtId = (EditText) findViewById(R.id.edtId);
        edtPw = (EditText) findViewById(R.id.edtPw);
        edtContent = (EditText) findViewById(R.id.edtContent);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnClear = (Button) findViewById(R.id.btnClear);
        btnEnd = (Button) findViewById(R.id.btnEnd);

        btnAdd.setOnClickListener(btnlistener);
        btnClear.setOnClickListener(btnlistener);
        btnEnd.setOnClickListener(btnlistener);

        DisplayFile(FILENAME);
    }

    private Button.OnClickListener btnlistener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.btnAdd:  //加入資料
                    //檢查帳號及密碼是否都有輸入
                    if(edtId.getText().toString().equals("") || edtPw.getText().toString().equals("")){
                        Toast.makeText(getApplicationContext(), "帳號及密碼都必須輸入！", Toast.LENGTH_LONG) .show();
                        break;
                    }
                    // 檔案存取核心：FileOutputStream、FileInputStream
                    FileOutputStream fout = null; //建立寫入資料流

                    // 進行檔案存取必須建立 BufferedOutputStream 和 BufferedInputStream 物件
                    // 寫入資料的型態必須為 byte 型別
                    BufferedOutputStream buffout = null; // 進行檔案存取必須建立
                    try {
                        fout = openFileOutput(FILENAME, MODE_APPEND); // (檔名, 寫入權限)
                        /*
                        MODE_APPEND : 只有本應用程式可以存取
                        MODE_PRIVATE : 只有本應用程式可以存取。檔案存在時會覆蓋檔案。
                        MODE_WORLD_READABLE : 所有都可讀
                        MODE_WORLD_WRITABLE : 所有都可寫
                         */
                        buffout=new BufferedOutputStream(fout);

                        //寫入帳號及密碼
                        buffout.write(edtId.getText().toString().getBytes());
                        buffout.write("\n".getBytes());
                        buffout.write(edtPw.getText().toString().getBytes());
                        buffout.write("\n".getBytes());
                        buffout.close();
                    }	catch(Exception e)	{
                        e.printStackTrace();
                    }
                    DisplayFile(FILENAME);
                    edtId.setText("");
                    edtPw.setText("");
                    break;
                case R.id.btnClear:  //清除資料
                    try {
                        //以覆寫方式開啟檔案
                        fout=openFileOutput(FILENAME, MODE_PRIVATE);
                        fout.close();
                    }	catch(Exception e)	{
                        e.printStackTrace();
                    }
                    DisplayFile(FILENAME);
                    break;
                case R.id.btnEnd:  //結束
                    finish();
            }
        }
    };

    private void DisplayFile(String fname)
    {
        FileInputStream fin = null;  //建立讀取資料流
        BufferedInputStream buffin = null;
        try {
            fin = openFileInput(fname);
            buffin=new BufferedInputStream(fin);
            byte[] buffbyte=new byte[20];
            edtContent.setText("");
            // 讀取資料，直到檔案結束
            // 無窮迴圈 每次讀取20bytes的資料，以flag判斷 -1 表示讀不到資料已到盡頭
            do {
                int flag=buffin.read(buffbyte);
                if(flag==-1)  break;
                else
                    edtContent.append(new String(buffbyte), 0, flag); //顯示資料
            }while(true);
            buffin.close();
        }	catch(Exception e)	{
            e.printStackTrace();
        }
    }
}
