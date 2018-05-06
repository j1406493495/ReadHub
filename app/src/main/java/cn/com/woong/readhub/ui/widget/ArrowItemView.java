package cn.com.woong.readhub.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.com.woong.readhub.R;

/**
 * @author wong
 * Created by wong on 2018/4/27.
 */

public class ArrowItemView extends RelativeLayout {
    ImageView ivArrowLabel;
    TextView tvArrowContent;
    ImageView ivArrow;
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
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_arrow_item, this);

        ivArrowLabel = view.findViewById(R.id.iv_arrow_label);
        tvArrowContent = view.findViewById(R.id.tv_arrow_content);
        ivArrow = view.findViewById(R.id.iv_arrow);
        rlArrowItem = view.findViewById(R.id.rl_arrow_item);
    }

    public void setArrowContent(String content) {
        tvArrowContent.setText(content);
    }
}
