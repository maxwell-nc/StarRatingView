package com.github.maxwell.nc.library.utils;

import android.content.Context;

/**
 * 屏幕密度工具
 */
public class DimenUtil {

    /**
     * dp转px
     */
    public static int dp2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

}
