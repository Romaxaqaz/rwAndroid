package by.client.android.railwayapp.ui.utils;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;

/**
 * Created by PanteleevRV on 28.02.2018.
 *
 * @author PRV
 */
public class AnimationUtils {

    public static void setAnimation(Context context, View view, int animId) {
        Animation animation = android.view.animation.AnimationUtils.loadAnimation(context, animId);
        animation.setStartOffset(100);
        view.startAnimation(animation);
    }
}
