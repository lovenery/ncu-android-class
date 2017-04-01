package me.lovenery.a20161113_1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    private TextView tv1;
    private String s1[] = {"大麥克", "麥香雞", "勁辣雞腿堡", "雙層牛肉吉士堡", "大薯"};
    private boolean check[] = {false, false, false, false, false};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.btn);
        tv1 = (TextView) findViewById(R.id.tv1);

        btn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                final boolean c[] = check;
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("您點的餐點有：")
                        .setMultiChoiceItems(s1, check, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int item, boolean isChecked) {
                                if (isChecked) {
                                    c[item] = true;
                                } else {
                                    c[item] = false;
                                }
                            }
                        })
                        .setPositiveButton("確認", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int item) {
                                check = c;
                                String tmp = "";
                                for (int i = 0; i < c.length; i++) {
                                    if (c[i]) {
                                        tmp += s1[i] + "\n";
                                    }
                                }
                                tv1.setText("您點的餐點是：\n" + tmp);
                            }
                        })
                        .setNegativeButton("離開", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // leave do nothing
                            }
                        })
                        .show();
            }
        });
    }
}
