package me.lovenery.a20161119_1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {

    private EditText ed;
    private RadioButton r1, r2;
    private CheckBox cb1, cb2, cb3, cb4;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed = (EditText) findViewById(R.id.name);
        r1 = (RadioButton) findViewById(R.id.radioButton);
        r2 = (RadioButton) findViewById(R.id.radioButton2);
        cb1 = (CheckBox) findViewById(R.id.cBox1);
        cb2 = (CheckBox) findViewById(R.id.cBox2);
        cb3 = (CheckBox) findViewById(R.id.cBox3);
        cb4 = (CheckBox) findViewById(R.id.cBox4);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = "";
                if (!ed.getText().toString().equals("")) {
                    s += "您是：" + ed.getText().toString() + "\n";
                } else {
                    s += "您沒有填姓名\n";
                }

                if (r1.isChecked()) {
                    s += "您是" + r1.getText().toString() + "\n";
                } else {
                    s += "您是" + r2.getText().toString() + "\n";
                }

                s += "您的興趣有：";
                int cnt = 0;
                if (cb1.isChecked()) {
                    cnt++;
                    s += cb1.getText().toString() + "," ;
                }
                if (cb2.isChecked()) {
                    cnt++;
                    s += cb2.getText().toString() + "," ;
                }
                if (cb3.isChecked()) {
                    cnt++;
                    s += cb3.getText().toString() + "," ;
                }
                if (cb4.isChecked()) {
                    cnt++;
                    s += cb4.getText().toString();
                }
                if (cnt == 0) {
                    s += "您沒有勾選興趣！";
                }
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("顯示結果")
                        .setMessage(s)
                        .setNegativeButton("離開", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();

            }
        });
    }
}
