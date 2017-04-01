package me.lovenery.a20161113_3;

/*
W(瓦數) ＝ V(伏特)*A(安培) P=IV
電費 = (瓦數/1000)*(每天使用時數*30天)＊每度電費
example input:  110V, 10A, 30hr, 2$
example output: 1980
*/


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Spinner sp1;
    private EditText a;
    private EditText hr;
    private EditText m;
    private Button btn;
    private TextView tv;

    protected int volt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp1 = (Spinner) findViewById(R.id.input_v); // 伏特
        a = (EditText) findViewById(R.id.input_a); // 安培
        hr = (EditText) findViewById(R.id.input_hr); // 小時
        m = (EditText) findViewById(R.id.input_m); // 每度電費
        btn = (Button) findViewById(R.id.submit); // 送出
        tv = (TextView) findViewById(R.id.response); // 答案

        // 建立橋接器 string.xml <-> sp1
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(MainActivity.this, R.array.option_vs, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(adapter);

        btn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp1.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        volt = parent.getSelectedItemPosition();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                // volt = 0 or 1 (position)
                int inputv = 0;
                if (volt == 0) {
                    inputv = 110;
                } else {
                    inputv = 220;
                }
                double inputa = Double.parseDouble(a.getText().toString());
                int inputhr = Integer.parseInt(hr.getText().toString());
                int inputm = Integer.parseInt(m.getText().toString());
                double w = inputv * inputa;
                int total = (int) Math.round(w/1000*inputhr*30*inputm);
                tv.setText("電費$ " + total);
            }
        });
    }
}
