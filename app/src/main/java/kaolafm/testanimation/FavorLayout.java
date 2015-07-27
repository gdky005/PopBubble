package kaolafm.testanimation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Random;

/**
 * Created by WangQing on 15/7/27.
 */
public class FavorLayout extends RelativeLayout {

    private Random random = new Random();//用于实现随机功能

    private int dHeight = 100;//爱心的高度
    private int dWidth = 100;//爱心的宽度
    private int mHeight;//FavorLayout的高度
    private int mWidth;//FavorLayout的宽度


    /**
     * 定义一个LayoutParams 用它来控制子view的位置
     */
    private RelativeLayout.LayoutParams lp;

    //首先定义 3个代表不同爱心的drawable,以及他们的和drawables
    private Drawable red;
    private Drawable yellow;
    private Drawable blue;
    private Drawable[] drawables;

    public FavorLayout(Context context) {
        super(context);
    }

    public FavorLayout(Context context, AttributeSet attrs) {

        super(context, attrs);
        //为了显示区域,我设置了一个背景颜色,随意
        setBackgroundColor(getResources().getColor(R.color.mian_item_color_green));
        //init里做一些初始化变量的操作
        init();
    }

    private void init() {
        //        //底部 并且 水平居中
        lp = new RelativeLayout.LayoutParams(dWidth, dHeight);
        lp.addRule(CENTER_HORIZONTAL, TRUE); //这里的TRUE 要注意 不是true
        lp.addRule(ALIGN_PARENT_BOTTOM, TRUE);
        //好了,之后只要给子view设置LayoutParams就可以实现了


        //接下去我们初始化:
        //初始化显示的图片
        drawables = new Drawable[12];
        red = getResources().getDrawable(R.drawable.heart0);
        yellow = getResources().getDrawable(R.drawable.heart1);
        blue = getResources().getDrawable(R.drawable.heart2);
        Drawable heart3 = getResources().getDrawable(R.drawable.heart3);
        Drawable heart4 = getResources().getDrawable(R.drawable.heart4);
        Drawable heart5 = getResources().getDrawable(R.drawable.heart5);
        Drawable heart6 = getResources().getDrawable(R.drawable.heart6);
        Drawable heart7 = getResources().getDrawable(R.drawable.heart7);
        Drawable heart8 = getResources().getDrawable(R.drawable.heart8);
        Drawable heart9 = getResources().getDrawable(R.drawable.heart9);
        Drawable heart10 = getResources().getDrawable(R.drawable.heart10);
        Drawable heart11 = getResources().getDrawable(R.drawable.heart11);
        //赋值给drawables
        drawables[0] = red;
        drawables[1] = yellow;
        drawables[2] = blue;

        drawables[3] = heart3;
        drawables[4] = heart4;
        drawables[5] = heart5;
        drawables[6] = heart6;
        drawables[7] = heart7;
        drawables[8] = heart8;
        drawables[9] = heart9;
        drawables[10] = heart10;
        drawables[11] = heart11;


//        drawables[random.nextInt(3)];  //表示0-2的随机数,注意,3是取不到的,是个开区间
    }


    //重写onMeasure 获取控件宽高
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //注意!!  获取本身的宽高 需要在测量之后才有宽高
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
    }


    //我封装了一个方法  利用ObjectAnimator AnimatorSet来实现 alpha以及x,y轴的缩放功能
//target就是爱心
    private AnimatorSet getEnterAnimtor(final View target) {

        ObjectAnimator alpha = ObjectAnimator.ofFloat(target, View.ALPHA, 0.2f, 1f);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(target, View.SCALE_X, 0.2f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(target, View.SCALE_Y, 0.2f, 1f);
        AnimatorSet enter = new AnimatorSet();
        enter.setDuration(500);
        enter.setInterpolator(new LinearInterpolator());
        enter.playTogether(alpha, scaleX, scaleY);
        enter.setTarget(target);
        return enter;
    }

    public void addFavor() {

        ImageView imageView = new ImageView(getContext());
        //随机选一个
        imageView.setImageDrawable(drawables[random.nextInt(12)]);
        // 设置底部 水平居中
        imageView.setLayoutParams(lp);

        addView(imageView);
        Log.v("TAG", "add后子view数:" + getChildCount());

        dWidth = imageView.getWidth();
        dHeight = imageView.getHeight();

        getBezierValueAnimator(imageView).start();

//        Animator set = getEnterAnimtor(imageView);
//        set.start();

    }


    private ValueAnimator getBezierValueAnimator(View target) {

        //初始化一个BezierEvaluator
        BezierEvaluator evaluator = new BezierEvaluator(getPointF(2),getPointF(1));

        //这里最好画个图 理解一下 传入了起点 和 终点
        ValueAnimator animator = ValueAnimator.ofObject(evaluator, new PointF((mWidth - dWidth) / 2, mHeight - dHeight), new PointF(random.nextInt(getWidth()), 0));//随机
        animator.addUpdateListener(new BezierListenr(target));
        animator.setTarget(target);
        animator.setDuration(3000);
        return animator;
    }

//这里涉及到另外一个方法:getPointF(),这个是我用来获取途径的两个点
// 这里的取值可以随意调整,调整到你希望的样子就好
    /**
     * 获取中间的两个 点
     * @param scale
     */
    private PointF getPointF(int scale) {

        PointF pointF = new PointF();
        pointF.x = random.nextInt((mWidth - 100));//减去100 是为了控制 x轴活动范围,看效果 随意~~
        //再Y轴上 为了确保第二个点 在第一个点之上,我把Y分成了上下两半 这样动画效果好一些  也可以用其他方法
        pointF.y = random.nextInt((mHeight - 100))/scale;
        return pointF;
    }


}
