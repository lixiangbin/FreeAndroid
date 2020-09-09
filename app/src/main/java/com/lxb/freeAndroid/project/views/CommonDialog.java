package com.lxb.freeAndroid.project.views;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lxb.freeAndroid.R;

/**
 * 业务名:公共Dialog
 * 功能说明:支持各种自定义
 * 编写日期:
 * 作者:	 李相斌
 * <p/>
 * 历史记录
 * 1、修改日期：
 * 修改人：
 * 修改内容：
 */

public class CommonDialog extends Dialog implements View.OnClickListener {
    private TextView tv_content;//内容文字
    private View content_top;//内容文字TOP距离
    private TextView tv_title;//标题文字
    private ImageView iv_icon;//图标
    private TextView tv_confirm;//确认按钮
    private TextView tv_cancel;//取消按钮
    private View v_lineX,//横向分割线
            v_lineY;//纵向分割线

    private Context mContext;
    private String title;
    private CharSequence content;
    private String confirmName;
    private String cancelName;

    /*颜色*/
    private int titleColor;
    private int contentColor;
    private int cancelColor;
    private int confirmColor;
    /*对齐方式*/
    private int gravity;

    private boolean constructFlag;


    private OnConfirmListener onConfirmListener;
    private OnCancelListener onCancelListener;

    public CommonDialog(Context context) {
        super(context, R.style.common_dialog);
        this.mContext = context;
    }

    public CommonDialog(Context context, int style) {
        super(context, style);
        this.mContext = context;
    }

    public CommonDialog(Context context, String content) {
        super(context, R.style.common_dialog);
        this.mContext = context;
        this.content = content;
        constructFlag = true;
    }

    public CommonDialog(Context context, String title, String content) {
        super(context, R.style.common_dialog);
        this.mContext = context;
        this.content = content;
        this.title = title;
        constructFlag = true;
    }

    public CommonDialog(Context context, String title, String content, OnConfirmListener onConfirmListener) {
        super(context, R.style.common_dialog);
        this.mContext = context;
        this.title = title;
        this.content = content;
        this.onConfirmListener = onConfirmListener;
    }

    public CommonDialog(Context context, String content, String title, OnConfirmListener onConfirmListener, OnCancelListener onCancelListener) {
        super(context, R.style.common_dialog);
        this.mContext = context;
        this.title = title;
        this.content = content;
        this.onConfirmListener = onConfirmListener;
        this.onCancelListener = onCancelListener;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_common_round);
        setCanceledOnTouchOutside(false);
        initView();
        if (constructFlag) isShowCancel(false);//根据构造方法标记判断，如果只有标题和内容，则只显示确定按钮
    }

    /**
     * 是否允许点击外部取消
     */
    boolean isCanceledOnTouchOutside;

    public CommonDialog isCanceledOnTouchOutside(boolean is) {
        isCanceledOnTouchOutside = is;
        return this;
    }

    /**
     * 是否显示标题
     */
    boolean isShowTitle = true;

    public CommonDialog isShowTitle(boolean is) {
        isShowTitle = is;
        return this;
    }

    /**
     * 是否显示内容
     */
    boolean isShowContent = true;

    public CommonDialog isShowContent(boolean is) {
        isShowContent = is;
        return this;
    }

    /**
     * 是否显示确认按钮
     */
    boolean isShowConfirm = true;

    public CommonDialog isShowConfirm(boolean is) {
        isShowConfirm = is;
        return this;
    }


    /**
     * 是否显示取消按钮
     */
    boolean isShowCancel = true;

    public CommonDialog isShowCancel(boolean is) {
        isShowCancel = is;
        return this;
    }

    /**
     * 是否加粗标题
     */
    boolean isTitleBold;
    public CommonDialog setTitleBold(boolean is) {
        isTitleBold = is;
        return this;
    }


    /**
     * 设置确定按钮点击监听
     */
    public CommonDialog setConfirmClickListener(OnConfirmListener onConfirmListener) {
        this.onConfirmListener = onConfirmListener;
        return this;
    }

    /**
     * 设置取消按钮点击监听
     */
    public CommonDialog setCancelClickListener(OnCancelListener onCancelListener) {
        this.onCancelListener = onCancelListener;
        return this;
    }

    /**
     * 设置标题文字
     */
    public CommonDialog setTitle(String title) {
        this.title = title;
//        if (!TextUtils.isEmpty(title)) isShowTitle = true;
        return this;
    }

    /**
     * 设置内容文字
     */
    public CommonDialog setContent(CharSequence content) {
        this.content = content;
        return this;
    }

    /**
     * 设置确定按钮文字
     */
    public CommonDialog setConfirmName(String name) {
        this.confirmName = name;
        return this;
    }

    /**
     * 设置取消按钮文字
     */
    public CommonDialog setCancelName(String name) {
        this.cancelName = name;
        return this;
    }

    /**
     * 设置标题颜色
     */
    public CommonDialog setTitleColor(int color) {
        titleColor = color;
        return this;
    }

    /**
     * 设置内容颜色
     */
    public CommonDialog setContentColor(int color) {
        contentColor = color;
        return this;
    }

    /**
     * 设置取消按钮颜色
     */
    public CommonDialog setCancelColor(int color) {
        cancelColor = color;
        return this;
    }


    /**
     * 设置确定按钮颜色
     */
    public CommonDialog setConfirmColor(int color) {
        confirmColor = color;
        return this;
    }


    /**
     * 设置内容对齐方式
     */
    public CommonDialog setContentGravity(int gravity) {
        this.gravity = gravity;
        return this;
    }

    /**
     * 设置内容字体是否加粗
     */
    boolean isOverStriking;

    public CommonDialog setContentOverStriking(boolean is) {
        isOverStriking = is;
        return this;
    }


    /**
     * 设置内容icon
     */
    int resourceId = 0;

    public CommonDialog setIconResource(int resourceId) {
        this.resourceId = resourceId;
        return this;
    }

    /**
     * 设置是否拦截手机的物理back按键
     * true:拦截<br/>
     * false:不拦截
     */
    boolean isBack;

    public CommonDialog isInterceptBackKay(boolean isBack) {
        this.isBack = isBack;
        return this;
    }


    private void initView() {
        tv_content = (TextView) findViewById(R.id.content);
        content_top = findViewById(R.id.content_top);
        tv_title = (TextView) findViewById(R.id.title);
        tv_confirm = (TextView) findViewById(R.id.confirm);
        tv_cancel = (TextView) findViewById(R.id.cancel);
        iv_icon = (ImageView) findViewById(R.id.iv_icon);
        v_lineX = findViewById(R.id.v_lineX);
        v_lineY = findViewById(R.id.v_lineY);
        tv_confirm.setOnClickListener(this);
        tv_cancel.setOnClickListener(this);
        tv_content.setText(content);
        tv_content.getPaint().setFakeBoldText(isOverStriking);
        //图标默认不显示
        iv_icon.setVisibility(View.GONE);
        //设置图标资源
        if (resourceId != 0) {
            iv_icon.setVisibility(View.VISIBLE);
            iv_icon.setImageResource(resourceId);
        }
        //设置位置
        if (gravity != 0) {
            tv_content.setGravity(gravity);
        }
        //设置颜色
        if (titleColor != 0) {
            tv_title.setTextColor(mContext.getResources().getColor(titleColor));
        }
        if (contentColor != 0) {
            tv_content.setTextColor(mContext.getResources().getColor(contentColor));
        }
        if (cancelColor != 0) {
            tv_cancel.setTextColor(mContext.getResources().getColor(cancelColor));
        }
        if (confirmColor != 0) {
            tv_confirm.setTextColor(mContext.getResources().getColor(confirmColor));
        }
        //设置文字
        if (!TextUtils.isEmpty(confirmName)) {
            tv_confirm.setText(confirmName);
        }
        if (!TextUtils.isEmpty(cancelName)) {
            tv_cancel.setText(cancelName);
        }
        if (!TextUtils.isEmpty(title)) {
            tv_title.setText(title);
        }

        //文字样式
        //标题是否加粗
        if(isTitleBold){
            tv_title.getPaint().setFakeBoldText(true);
        }

        //是否允许点击外部消失
        setCanceledOnTouchOutside(isCanceledOnTouchOutside);
        //是否允许响应back按键
        setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                return keyCode == KeyEvent.KEYCODE_BACK ? isBack : false;
            }
        });
        //设置控件显示或隐藏
        if (isShowTitle) {
            tv_title.setVisibility(View.VISIBLE);
            content_top.setVisibility(View.GONE);
        } else {
            tv_title.setVisibility(View.GONE);
            content_top.setVisibility(View.VISIBLE);
        }
        if (isShowContent) {
            tv_content.setVisibility(View.VISIBLE);
        } else {
            tv_content.setVisibility(View.GONE);
        }


        if (isShowConfirm) {
            tv_confirm.setVisibility(View.VISIBLE);
        } else {
            tv_confirm.setVisibility(View.GONE);
            v_lineY.setVisibility(View.GONE);
        }

        if (isShowCancel) {
            tv_cancel.setVisibility(View.VISIBLE);
        } else {
            tv_cancel.setVisibility(View.GONE);
            v_lineY.setVisibility(View.GONE);
        }

        if (!isShowConfirm && !isShowCancel) {
            v_lineX.setVisibility(View.GONE);
        }

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.confirm) {
            if (onConfirmListener != null) {
                onConfirmListener.onClick(this);
            }
            this.dismiss();
        } else if (i == R.id.cancel) {
            if (onCancelListener != null) {
                onCancelListener.onClick(this);
            } else {
                this.dismiss();
            }
        }
    }

    /**
     * 确定按钮监听
     */
    public interface OnConfirmListener {
        void onClick(CommonDialog dialog);
    }

    /**
     * 取消按钮监听
     */
    public interface OnCancelListener {
        void onClick(CommonDialog dialog);
    }


}
