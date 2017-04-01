package me.lovenery.a20161112_2;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static java.lang.Math.pow;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText ed_lend, ed_rate, ed_number;
    private Button btn, reset;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed_lend = (EditText) findViewById(R.id.ed_lend);
        ed_rate = (EditText) findViewById(R.id.ed_rate);
        ed_number = (EditText) findViewById(R.id.ed_number);

        btn = (Button) findViewById(R.id.button);
        reset = (Button) findViewById(R.id.reset);

        result = (TextView) findViewById(R.id.result);


        btn.setOnClickListener(btnViewText);
        reset.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                ed_lend.setText("");
                ed_rate.setText("");
                ed_number.setText("");
                result.setText("");
            }
        });
    }

    private Button.OnClickListener btnViewText = new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
            DecimalFormat nf = new DecimalFormat("0.00");

            double tmp_ans;
            int lend = Integer.parseInt(ed_lend.getText().toString());
            double rate = Double.parseDouble(ed_rate.getText().toString()) / 100;
            double num = Double.parseDouble(ed_number.getText().toString());

            // 本利和 = 本金*(1+月利率)^期數
            tmp_ans = lend * Math.pow((rate/12) + 1, num);

            result.setText("本利和為：" + nf.format(tmp_ans));
        }
    };


}
