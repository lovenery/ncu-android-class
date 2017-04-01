package me.lovenery.a20161120_2;

import android.content.Intent;
import android.renderscript.Double2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {

    private EditText edheight, edweight;
    private RadioButton rbf, rbm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edheight = (EditText) findViewById(R.id.height);
        edweight = (EditText) findViewById(R.id.weight);
        rbm = (RadioButton) findViewById(R.id.male);
        rbf = (RadioButton) findViewById(R.id.female);

        Button submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                double h = Double.parseDouble(edheight.getText().toString());
                double w = Double.parseDouble(edweight.getText().toString());
                String sex = "";
                if (rbm.isChecked()) {
                    sex = "M";
                } else {
                    sex = "F";
                }
                Intent intent = new Intent(MainActivity.this, MainActivity_child.class);
                Bundle bundle = new Bundle();
                bundle.putDouble("height", h);
                bundle.putDouble("weight", w);
                bundle.putString("sex", sex);
                intent.putExtras(bundle);
                startActivityForResult(intent, 0);
            }
        });
    }

    /*
        將第二個Activity Class 執行成功後，
        回傳resultCode的值加入switch判斷是否執行成功，
        若執行成功，
        則使用 getExtras 方法取得回傳的資料，
        再以識別資料符號取出個別的值。

         requestCode 對應startactivityForResult(intent, 0);的第二個參數
         用來判斷是由哪一個 Activity 回傳資料
    */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case RESULT_OK:
                Bundle bundle = data.getExtras();
                String sex = bundle.getString("sex");
                double height = bundle.getDouble("height");
                double weight = bundle.getDouble("weight");
                edheight.setText("" + height);
                edweight.setText("" + weight);
                if (sex.equals("M")) {
                    rbm.setChecked(true);
                } else {
                    rbf.setChecked(true);
                }
                break;
            default:
                break;
        }
    }
}
