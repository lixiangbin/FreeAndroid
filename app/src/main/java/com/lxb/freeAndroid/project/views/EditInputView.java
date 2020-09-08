package com.lxb.freeAndroid.project.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.DigitsKeyListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.lxb.freeAndroid.R;

/**
 * 业务名:公共输入控件
 * 功能说明:
 * 编写日期: xxxx-09-05.
 * 作者: 李相斌
 * <p/>
 * 历史记录
 * 1、修改日期：
 * 修改人：
 * 修改内容：
 */
public class EditInputView extends ConstraintLayout {

    /*控件*/
    private TextView tv_inputTitle;//输入栏标题
    private EditText et_inputTxt;//输入框
    private TypedArray attributes;//获取属性

    public EditInputView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_edit_input, this, true);
        attributes = context.obtainStyledAttributes(attrs, R.styleable.EditInputView);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        tv_inputTitle = findViewById(R.id.tv_inputTitle);
        et_inputTxt = findViewById(R.id.et_inputTxt);

        /*处理属性*/
        //标题文字
        String inputTitleText = attributes.getString(R.styleable.EditInputView_inputTitleText);
        setInputTitleText(inputTitleText);
        //输入框内容文字
        String inputText = attributes.getString(R.styleable.EditInputView_inputText);
        setInputText(inputText);
        //是否单行
        boolean singleLine = attributes.getBoolean(R.styleable.EditInputView_singleLine, false);
        setSingleLine(singleLine);
        //最大行数
        int maxLines = attributes.getInt(R.styleable.EditInputView_maxLines, 0);
        setMaxLines(maxLines);
        //字数上限
        int maxEms = attributes.getInt(R.styleable.EditInputView_maxEms, 0);
        setMaxEms(maxEms);
        //最大长度
        int maxLength = attributes.getInt(R.styleable.EditInputView_maxLength, 0);
        setMaxLength(maxLength);
        //输入文本颜色
        int inputTextColor = attributes.getColor(R.styleable.EditInputView_inputTextColor, getResources().getColor(R.color.color_000000));
        setInputTextColor(inputTextColor);
        //输入框提示文字
        String hintText = attributes.getString(R.styleable.EditInputView_hintText);
        setHintText(hintText);
        //输入类型
        int inputType = attributes.getInt(R.styleable.EditInputView_inputType, 0);
        setInputType(inputType);
        //输入指定字符
        String digits = attributes.getString(R.styleable.EditInputView_digits);
        setDigits(digits);
        //回收
        attributes.recycle();
    }

    public void setInputTitleText(String inputTitleText) {
        tv_inputTitle.setText(inputTitleText == null ? "" : inputTitleText);
    }

    public void setInputText(String inputText) {
        et_inputTxt.setText(inputText == null ? "" : inputText);
    }

    public void setSingleLine(boolean singleLine) {
        et_inputTxt.setSingleLine(singleLine);
    }

    public void setMaxLines(int maxLines) {
        if (maxLines > 0) {
            et_inputTxt.setMaxLines(maxLines);
        }
    }

    public void setMaxEms(int maxEms) {
        if (maxEms > 0) {
            et_inputTxt.setMaxEms(maxEms);
        }
    }

    public void setMaxLength(int maxLength) {
        if (maxLength > 0) {
            et_inputTxt.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
        }
    }

    public void setInputTextColor(int inputTextColor) {
        et_inputTxt.setTextColor(inputTextColor);
    }


    public void setHintText(String hintText) {
        et_inputTxt.setHint(hintText == null ? "" : hintText);
    }

    public void setInputType(int inputType) {
        switch (inputType) {
            case 0://文本类型
                et_inputTxt.setInputType(InputType.TYPE_CLASS_TEXT);
                break;
            case 1://屏蔽输入
                et_inputTxt.setInputType(InputType.TYPE_NULL);
                break;
            case 2://数字类型
                et_inputTxt.setInputType(InputType.TYPE_CLASS_NUMBER);
                break;
            case 3://电话号码
                et_inputTxt.setInputType(InputType.TYPE_CLASS_PHONE);
                break;
            case 4://密码
                et_inputTxt.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                break;
            default:
                et_inputTxt.setInputType(InputType.TYPE_CLASS_TEXT);
                break;
        }

    }


    public void setDigits(String digits) {
        if (!TextUtils.isEmpty(digits)) {
            et_inputTxt.setKeyListener(DigitsKeyListener.getInstance(digits));
        }
    }

    public String getText() {
        if (et_inputTxt != null && et_inputTxt.getText() != null) {
            return et_inputTxt.getText().toString();
        }
        return null;
    }

    public TextView getTitleView() {
        return tv_inputTitle;
    }

    public EditText getEditView() {
        return et_inputTxt;
    }
}
