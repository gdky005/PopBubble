package kaolafm.testanimation;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.orhanobut.logger.Logger;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView iv;
    RelativeLayout relativeLayout;
    FavorLayout favorLayout;

    int delay = (int)(100 / 3000f * 1000);

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == 0)
                favorLayout.addFavor();

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = (ImageView) findViewById(R.id.iv);
        iv.setOnClickListener(this);

        favorLayout = (FavorLayout) findViewById(R.id.favor);



        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                int delayDefault = 0;

                for (int i = 0; i < 100; i++) {
                    Logger.i("delay:" + delayDefault);
                    handler.sendEmptyMessageDelayed(0, delayDefault);
                    delayDefault += delay;
                }



            }
        }, 5000);


    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv:

                favorLayout.addFavor();

                break;
        }
    }
}
