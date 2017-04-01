package me.lovenery.a20161120_1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Hsu on 2016/11/20.
 */

public class Second  extends AppCompatActivity {
    private Button btn1;

    // NOTE: ctrl + o

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // start coding
        setContentView(R.layout.second);
        btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(btnhomelistener);
    }

    private Button.OnClickListener btnhomelistener = new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
            // 放棄第二個activity的工作權
            finish();
        }
    };
}
