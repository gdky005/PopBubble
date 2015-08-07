package kaolafm.testanimation;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.orhanobut.logger.Logger;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView iv;
    FavorLayout favorLayout;

    /** 3秒内所有的心均匀显示的时间间隔 */
    int delay = 0;
    /** 需要添加的 心 个数 */
    int heartCount = 100;
    /** 3000(毫秒) * 1000（秒单位）  = 3 */
    int heartGetDataTime = 3;
    /** 延迟5秒获取一次数据 */
    int HEART_DELAY_TIME = 5000;
    /** 添加 心 的MessageFlag */
    int HEART_MESSAGE_FLAG = 0;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == HEART_MESSAGE_FLAG)
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
                addFavor(heartCount);
            }
        }, HEART_DELAY_TIME);


    }

    private void addFavor(int heartCount) {
        int delayDefault = 0;
        delay = heartCount / heartGetDataTime;

        for (int i = 0; i < heartCount; i++) {
            Logger.i("delay:" + delayDefault);
            handler.sendEmptyMessageDelayed(HEART_MESSAGE_FLAG, delayDefault);
            delayDefault += delay;
        }
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
