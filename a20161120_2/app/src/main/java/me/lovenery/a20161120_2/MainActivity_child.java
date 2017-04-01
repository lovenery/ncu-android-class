package me.lovenery.a20161120_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

/**
 * Created by Hsu on 2016/11/20.
 * BMI = kg / m*m
 */

public class MainActivity_child extends AppCompatActivity{
    Bundle bundle;
    Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mylayout);

        // 取得資料並計算
        intent = this.getIntent();
        bundle = intent.getExtras();
        String sex = bundle.getString("sex");
        double height = bundle.getDouble("height");
        double weight = bundle.getDouble("weight");
        String BMI_result = this.getBMI(height, weight);
        String BMI_advice = this.getAdvice(sex, height, weight);

        // 將結果印出
        TextView tvBMI = (TextView) findViewById(R.id.tvBMI);
        tvBMI.setText(BMI_result);
        TextView tvAdvice = (TextView) findViewById(R.id.tvAdvice);
        tvAdvice.setText(BMI_advice);

        // 回上一步
        Button back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity_child.this.setResult(RESULT_OK, intent);
                MainActivity_child.this.finish();
            }
        });
    }
    private String format(double num) {
        DecimalFormat nf = new DecimalFormat("0.00");
        String s = nf.format(num);
        return s;
    }
    private String getBMI(double height, double weight) {
        double BMI = weight / (height * height);
        return "您的BMI結果為: " + format(BMI);
    }
    private String getAdvice(String sex, double height, double weight) {
        double BMI = weight / (height * height);
        double BMI_MAX;
        double BMI_MIN;
        if (sex.equals("M")) {
            BMI_MAX = 25.0;
            BMI_MIN = 22.0;
        } else {
            BMI_MAX = 22.0;
            BMI_MIN = 18.0;
        }
        if (BMI > BMI_MAX) {
            return "肥";
        } else if (BMI < BMI_MIN){
            return "瘦";
        } else {
            return "水";
        }
    }
}
