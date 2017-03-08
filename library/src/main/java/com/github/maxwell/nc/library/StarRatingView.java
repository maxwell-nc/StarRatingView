package com.github.maxwell.nc.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.github.maxwell.nc.library.utils.DimenUtil;

import java.util.ArrayList;

/**
 * 星星评价控件
 */
public class StarRatingView extends LinearLayout {

    /**
     * 两颗星星的间距（dp）
     */
    private static final float DEFAULT_MARGIN_DP = 4;

    /**
     * 星星的默认大小
     */
    private static final int DEFAULT_STAR_SIZE = 32;

    /**
     * 默认的星星数量
     */
    private static final int DEFAULT_STAR_COUNT = 5;

    /**
     * 默认评价数量
     */
    private static final int DEFAULT_RATING_COUNT = 0;

    /**
     * 星星数量
     */
    private int starCount = DEFAULT_STAR_COUNT;

    /**
     * 评价，只支持整数
     */
    private int ratingCount = DEFAULT_RATING_COUNT;

    /**
     * 星星大小（px）
     */
    private int starSize;

    /**
     * 星星集合
     */
    private ArrayList<ImageView> starList;

    private OnRateChangeListener mOnRateChangeListener;

    public StarRatingView(Context context) {
        super(context);
        init(null);
    }

    public StarRatingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public StarRatingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public int getStarCount() {
        return starCount;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void setOnRateChangeListener(OnRateChangeListener onRateChangeListener) {
        mOnRateChangeListener = onRateChangeListener;
    }

    /**
     * 初始化控件
     */
    private void init(AttributeSet attrs) {
        Context context = getContext();

        int starSizePx = DimenUtil.dp2px(context, DEFAULT_STAR_SIZE);

        //根据配置更新
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.StarRatingView);
            starCount = typedArray.getInt(R.styleable.StarRatingView_star_count, DEFAULT_STAR_COUNT);
            ratingCount = typedArray.getInt(R.styleable.StarRatingView_rating_count, DEFAULT_RATING_COUNT);
            starSize = typedArray.getDimensionPixelSize(R.styleable.StarRatingView_star_size, starSizePx);
            typedArray.recycle();
        } else {
            starSize = starSizePx;
        }

        //防止越界
        if (ratingCount > starCount) {
            ratingCount = starCount;
        }

        //创建指定数量的星星
        starList = new ArrayList<>();
        removeAllViews();
        for (int i = 0; i < starCount; i++) {
            addView(createGrayStar());
        }

        //初始化评价值
        rate(ratingCount);

    }

    /**
     * 创建一个灰色的星星
     */
    private ImageView createGrayStar() {
        Context context = getContext();

        int margin = DimenUtil.dp2px(context, DEFAULT_MARGIN_DP);

        final ImageView starView = new ImageView(context);

        LinearLayout.LayoutParams layoutParams = new LayoutParams(starSize, starSize);

        //除了第一个星星，其他调整距离
        if (starList.size() >= 1) {

            //根据方向调整
            int orientation = getOrientation();
            if (orientation == VERTICAL) {
                layoutParams.setMargins(0, margin, 0, 0);
            } else {
                layoutParams.setMargins(margin, 0, 0, 0);
            }

        }

        starView.setLayoutParams(layoutParams);
        starView.setBackgroundResource(R.mipmap.icon_rating_star_gray);

        //评分点击
        starView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                rate(starList.indexOf(starView) + 1);
            }
        });

        starList.add(starView);
        return starView;
    }

    /**
     * 评价
     *
     * @param rateCount 评价分数
     */
    public void rate(int rateCount) {

        //更新分数
        ratingCount = rateCount > starCount ? starCount : rateCount;

        //更新星星状态
        for (int i = 0; i < starList.size(); i++) {
            starList.get(i).setBackgroundResource(i < rateCount ?
                    R.mipmap.icon_rating_star_orange : R.mipmap.icon_rating_star_gray);
        }

        //执行回调
        if (mOnRateChangeListener != null) {
            mOnRateChangeListener.onRated(rateCount);
        }

    }

    /**
     * 评价改变监听器
     */
    public interface OnRateChangeListener {

        /**
         * 评价后的操作
         *
         * @param rateCount 评价的分数
         */
        void onRated(int rateCount);

    }

}
