package cn.com.woong.readhub.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.suke.widget.SwitchButton;

import cn.com.woong.readhub.R;

/**
 * @author wong
 * Created by wong on 2018/4/27.
 */

public class ArrowItemView extends RelativeLayout {
    ImageView ivArrowLabel;
    TextView tvArrowContent;
    ImageView ivArrowRight;
    SwitchButton switchButton;
    RelativeLayout rlArrowItem;

    public ArrowItemView(Context context) {
        this(context, null);
    }

    public ArrowItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ArrowItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ArrowItemView);
        tvArrowContent.setText(typedArray.getString(R.styleable.ArrowItemView_arrowContent));
        ivArrowLabel.setImageResource(typedArray.getResourceId(R.styleable.ArrowItemView_arrowLabel, R.drawable.ic_info));

        boolean labelVisible = typedArray.getBoolean(R.styleable.ArrowItemView_arrowLabelVisible, true);
        ivArrowLabel.setVisibility(labelVisible ? VISIBLE : GONE);

        boolean rightVisible = typedArray.getBoolean(R.styleable.ArrowItemView_arrowRightVisible, true);
        ivArrowRight.setVisibility(rightVisible ? VISIBLE : GONE);
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_arrow_item, this);

        ivArrowLabel = view.findViewById(R.id.iv_arrow_label);
        tvArrowContent = view.findViewById(R.id.tv_arrow_content);
        ivArrowRight = view.findViewById(R.id.iv_arrow_right);
        switchButton = view.findViewById(R.id.switch_button);
        rlArrowItem = view.findViewById(R.id.rl_arrow_item);

        switchButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if (mOnArrowListener != null) {
                    mOnArrowListener.onSwitchChange(isChecked);
                }
            }
        });
    }

    public void setArrowContent(String content) {
        tvArrowContent.setText(content);
    }

    public void setArrowLabelVisible(boolean visible) {
        ivArrowLabel.setVisibility(visible ? VISIBLE : GONE);
    }

    public void setArrowRightVisible(boolean visible) {
        ivArrowRight.setVisibility(visible ? VISIBLE : GONE);
    }

    public void setSwitchButtonVisible(boolean visible) {
        switchButton.setVisibility(visible ? VISIBLE : GONE);
    }

    /**
     * arrow item 事件监听
     */
    private OnArrowListener mOnArrowListener;
    public void setOnArrowListener(OnArrowListener listener) {
        mOnArrowListener = listener;
    }

    public interface OnArrowListener {
        void onSwitchChange(boolean checkable);
    }
}
