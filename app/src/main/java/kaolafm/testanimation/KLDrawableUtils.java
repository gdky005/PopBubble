package kaolafm.testanimation;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;

/**
 * Created by WangQing on 15/8/7.
 */
public class KLDrawableUtils {

    @SuppressWarnings("deprecation")
    public static Drawable getDrawable(Context context, int drawable) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return context.getResources().getDrawable(drawable);
        } else {
            return context.getResources().getDrawable(drawable, null);
        }
    }
}
