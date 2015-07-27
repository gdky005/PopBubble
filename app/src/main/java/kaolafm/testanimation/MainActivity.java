package kaolafm.testanimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView iv;
    RelativeLayout relativeLayout;
    FavorLayout favorLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = (ImageView) findViewById(R.id.iv);
        iv.setOnClickListener(this);

        favorLayout = (FavorLayout) findViewById(R.id.favor);


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
