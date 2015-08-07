package kaolafm.testanimation;

import android.test.ActivityInstrumentationTestCase2;

import com.orhanobut.logger.Logger;

/**
 * Created by WangQing on 15/8/7.
 */
public class MyTest extends ActivityInstrumentationTestCase2 {
    public MyTest() {
        super(MainActivity.class);
    }


    public void test() {
        Logger.i("日志是：" + 100 / 3000d);
        Logger.i("日志是：" + 100 / 3000f * 1000);
    }
}
