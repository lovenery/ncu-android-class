package me.lovenery.a20161119_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edtATM;
    private Button  btn1, btn2, btn3,
                    btn4, btn5, btn6,
                    btn7, btn8, btn9,
                    btn0, btnok, btnback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtATM = (EditText) findViewById(R.id.editText2);
        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button3);
        btn4 = (Button) findViewById(R.id.button4);
        btn5 = (Button) findViewById(R.id.button5);
        btn6 = (Button) findViewById(R.id.button6);
        btn7 = (Button) findViewById(R.id.button7);
        btn8 = (Button) findViewById(R.id.button8);
        btn9 = (Button) findViewById(R.id.button9);
        btnok = (Button) findViewById(R.id.button10);
        btn0 = (Button) findViewById(R.id.button11);
        btnback = (Button) findViewById(R.id.button12);

        btn1.setOnClickListener(btnlistener);
        btn2.setOnClickListener(btnlistener);
        btn3.setOnClickListener(btnlistener);
        btn4.setOnClickListener(btnlistener);
        btn5.setOnClickListener(btnlistener);
        btn6.setOnClickListener(btnlistener);
        btn7.setOnClickListener(btnlistener);
        btn8.setOnClickListener(btnlistener);
        btn9.setOnClickListener(btnlistener);
        btnok.setOnClickListener(btnlistener);
        btn0.setOnClickListener(btnlistener);
        btnback.setOnClickListener(btnlistener);
    }
    private Button.OnClickListener btnlistener = new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button11:
                    display("0");
                    break;
                case R.id.button1:
                    display("1");
                    break;
                case R.id.button2:
                    display("2");
                    break;
                case R.id.button3:
                    display("3");
                    break;
                case R.id.button4:
                    display("4");
                    break;
                case R.id.button5:
                    display("5");
                    break;
                case R.id.button6:
                    display("6");
                    break;
                case R.id.button7:
                    display("7");
                    break;
                case R.id.button8:
                    display("8");
                    break;
                case R.id.button9:
                    display("9");
                    break;
                // 清除按鈕
                case R.id.button10:
                    String str = edtATM.getText().toString();
                    if (str.length() > 0) {
                        str = str.substring(0, str.length()-1);
                        edtATM.setText(str);
                    }
                    break;
                // 確定按鈕
                case R.id.button12:
                    str = edtATM.getText().toString();
                    if (str.equals("111")) {
                        Toast toast = Toast.makeText(MainActivity.this, "密碼正確！", Toast.LENGTH_LONG);
                        toast.show();
                    } else {
                        Toast toast = Toast.makeText(MainActivity.this, "錯誤！", Toast.LENGTH_LONG);
                        toast.show();
                    }
                    break;
            }
        }
    };

    private void display(String s) {
        String str = edtATM.getText().toString();
        edtATM.setText(str + s);
    }
}
