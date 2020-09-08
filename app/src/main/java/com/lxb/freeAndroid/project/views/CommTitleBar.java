package com.lxb.freeAndroid.project.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.lxb.freeAndroid.R;

/**
 * 业务名:公用标题栏View
 * 功能说明:
 * 编写日期: xxxx-09-04.
 * 作者: 李相斌
 * <p/>
 * 历史记录
 * 1、修改日期：
 * 修改人：
 * 修改内容：
 */
public class CommTitleBar extends ConstraintLayout {

    private ImageView ivLeftIcon;//左侧icon
    private TextView tvLeftText;//左侧文字
    private TextView tvTitleText;//标题
    private TextView tvRightText;//右侧文字
    private ImageView ivRightIcon;//右侧icon
    private ConstraintLayout clTitleBarLayout;//标题布局

    /*属性*/
    private Drawable leftIcon;//左侧icon资源
    private int leftTextColor;//左侧文字颜色
    private int titleTextColor;//标题文字颜色
    private int rightTextColor;//右侧文字颜色
    private Drawable rightIcon;//右侧icon资源

    private static final int LEFT_TEXT_SIZE = 15;//左侧文字默认大小 sp
    private static final int TITLE_TEXT_SIZE = 15;//中间标题文字默认大小 sp
    private static final int RIGHT_TEXT_SIZE = 15;//右侧文字默认大小 sp

    private int backgroundColor;//titleBar背景色


    public CommTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_comm_title_bar, this, true);
        ivLeftIcon = findViewById(R.id.iv_leftIcon);
        tvLeftText = findViewById(R.id.tv_leftText);
        tvTitleText = findViewById(R.id.tv_title);
        tvRightText = findViewById(R.id.tv_rightText);
        ivRightIcon = findViewById(R.id.iv_rightIcon);
        clTitleBarLayout = findViewById(R.id.cl_TitleBar);

        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.CommTitleBar);
        //主题类型  默认蓝色主题
        int themeType = attributes.getInt(R.styleable.CommTitleBar_themeType, 0);


        //左侧文字名称
        String leftText = attributes.getString(R.styleable.CommTitleBar_leftText);
        setLeftText(leftText);

        //左侧文字大小
        float leftTextSize = attributes.getDimension(R.styleable.CommTitleBar_leftTextSize, -1);
        setLeftTextSize(leftTextSize);
        //标题名称
        String titleText = attributes.getString(R.styleable.CommTitleBar_titleText);
        setTitleText(titleText);

        //标题文字大小
        float titleTextSize = attributes.getDimension(R.styleable.CommTitleBar_titleTextSize, -1);
        setTitleTextSize(titleTextSize);
        //右侧文字名称
        String rightText = attributes.getString(R.styleable.CommTitleBar_rightText);
        setRightText(rightText);

        //右侧文字大小
        float rightTextSize = attributes.getDimension(R.styleable.CommTitleBar_rightTextSize, -1);
        setRightTextSize(rightTextSize);

        //左侧icon资源是否显示
        boolean leftIconVisibility = attributes.getBoolean(R.styleable.CommTitleBar_leftIconIsShow, true);
        setLeftIconVisibility(leftIconVisibility ? View.VISIBLE : View.GONE);
        //右侧icon资源是否显示
        boolean rightIconVisibility = attributes.getBoolean(R.styleable.CommTitleBar_rightIconIsShow, false);
        setRightIconVisibility(rightIconVisibility ? View.VISIBLE : View.GONE);


        //设置颜色
        leftIcon = attributes.getDrawable(R.styleable.CommTitleBar_leftIcon);
        rightIcon = attributes.getDrawable(R.styleable.CommTitleBar_rightIcon);
        leftTextColor = attributes.getColor(R.styleable.CommTitleBar_leftTextColor, getResources().getColor(R.color.white));
        titleTextColor = attributes.getColor(R.styleable.CommTitleBar_titleTextColor, getResources().getColor(R.color.white));
        rightTextColor = attributes.getColor(R.styleable.CommTitleBar_rightTextColor, getResources().getColor(R.color.white));
        backgroundColor = attributes.getColor(R.styleable.CommTitleBar_backgroundColor, getResources().getColor(R.color.themeColor));
        if (themeType == 0) {
            //蓝色主题
            blueTheme();
        } else if (themeType == 1) {
            //白色主题
            whiteTheme();
        } else {
            //自定义主题
            otherTheme();
        }
        //回收
        attributes.recycle();
    }

    private void blueTheme() {
        setLeftIcon(getResources().getDrawable(R.mipmap.title_back_white));
        setRightIcon(getResources().getDrawable(R.mipmap.arrow_right_white));
        setLeftTextColor(getResources().getColor(R.color.white));
        setTitleTextColor(getResources().getColor(R.color.white));
        setRightTextColor(getResources().getColor(R.color.white));
        setTitleBarBackgroundColor(getResources().getColor(R.color.themeColor));
    }

    private void whiteTheme() {
        setLeftIcon(getResources().getDrawable(R.mipmap.title_back_gray));
        setRightIcon(getResources().getDrawable(R.mipmap.arrow_right_black));
        setLeftTextColor(getResources().getColor(R.color.black));
        setTitleTextColor(getResources().getColor(R.color.black));
        setRightTextColor(getResources().getColor(R.color.white));
        setTitleBarBackgroundColor(getResources().getColor(R.color.white));
    }

    private void otherTheme() {
        setLeftIcon(leftIcon == null ? getResources().getDrawable(R.mipmap.title_back_white) : leftIcon);
        setRightIcon(rightIcon == null ? getResources().getDrawable(R.mipmap.arrow_right_white) : rightIcon);
        setLeftTextColor(leftTextColor);
        setTitleTextColor(titleTextColor);
        setRightTextColor(rightTextColor);
        setTitleBarBackgroundColor(backgroundColor);
    }


    public void setLeftIcon(Drawable leftIcon) {
        ivLeftIcon.setBackground(leftIcon);
    }

    public void setLeftIconVisibility(int leftIconVisibility) {
        ivLeftIcon.setVisibility(leftIconVisibility);
    }

    public void setLeftText(String leftText) {
        tvLeftText.setText(leftText == null ? "" : leftText);
    }

    public void setLeftTextColor(int leftTextColor) {
        tvLeftText.setTextColor(leftTextColor);
    }

    public void setLeftTextSize(float leftTextSize) {
        if (leftTextSize < 0) {
            tvLeftText.setTextSize(LEFT_TEXT_SIZE);
        } else {
            tvLeftText.setTextSize(TypedValue.COMPLEX_UNIT_PX, leftTextSize);
        }
    }

    public void setTitleText(String titleText) {
        tvTitleText.setText(titleText == null ? "" : titleText);
    }

    public void setTitleTextColor(int titleTextColor) {
        tvTitleText.setTextColor(titleTextColor);
    }

    public void setTitleTextSize(float titleTextSize) {
        if (titleTextSize < 0) {
            tvTitleText.setTextSize(TITLE_TEXT_SIZE);
        } else {
            tvTitleText.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleTextSize);
        }

    }

    public void setRightText(String rightText) {
        tvRightText.setText(rightText == null ? "" : rightText);
    }

    public void setRightTextColor(int rightTextColor) {
        tvRightText.setTextColor(rightTextColor);
    }

    public void setRightTextSize(float rightTextSize) {
        if (rightTextSize < 0) {
            tvRightText.setTextSize(RIGHT_TEXT_SIZE);
        } else {
            tvRightText.setTextSize(TypedValue.COMPLEX_UNIT_PX, rightTextSize);
        }

    }

    public void setRightIcon(Drawable rightIcon) {
        ivRightIcon.setBackground(rightIcon);
    }

    public void setRightIconVisibility(int rightIconVisibility) {
        ivRightIcon.setVisibility(rightIconVisibility);
    }

    public void setTitleBarBackgroundColor(int backgroundColor) {
        clTitleBarLayout.setBackgroundColor(backgroundColor);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ivLeftIcon.setOnClickListener(v -> {
            if (onLeftIconClickListener != null) {
                onLeftIconClickListener.onClick(v);
            }
        });
        tvLeftText.setOnClickListener(v -> {
            if (onLeftTextClickListener != null) {
                onLeftTextClickListener.onClick(v);
            }
        });
        tvRightText.setOnClickListener(v -> {
            if (onRightTextClickListener != null) {
                onRightTextClickListener.onClick(v);
            }
        });

        ivRightIcon.setOnClickListener(v -> {
            if (onRightIconClickListener != null) {
                onRightIconClickListener.onClick(v);
            }
        });

    }

    /*左侧icon点击*/
    private OnLeftIconClickListener onLeftIconClickListener;

    public void setLeftIconClickListener(OnLeftIconClickListener onLeftIconClickListener) {
        this.onLeftIconClickListener = onLeftIconClickListener;
    }

    public interface OnLeftIconClickListener {
        void onClick(View v);
    }

    /*左侧文字点击*/
    private OnLeftTextClickListener onLeftTextClickListener;

    public void setLeftTextClickListener(OnLeftTextClickListener onLeftTextClickListener) {
        this.onLeftTextClickListener = onLeftTextClickListener;
    }

    public interface OnLeftTextClickListener {
        void onClick(View v);
    }


    /*右侧文字点击*/
    private OnRightTextClickListener onRightTextClickListener;

    public void setRightTextClickListener(OnRightTextClickListener onRightTextClickListener) {
        this.onRightTextClickListener = onRightTextClickListener;
    }

    public interface OnRightTextClickListener {
        void onClick(View v);
    }


    /*右侧icon点击*/
    private OnRightIconClickListener onRightIconClickListener;

    public void setRightIconClickListener(OnRightIconClickListener onRightIconClickListener) {
        this.onRightIconClickListener = onRightIconClickListener;
    }

    public interface OnRightIconClickListener {
        void onClick(View v);
    }

}
