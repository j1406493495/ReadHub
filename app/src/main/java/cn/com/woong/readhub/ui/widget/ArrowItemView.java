package cn.com.woong.readhub.ui.widget;

import android.content.Context;
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
    TextView tvArrowItem;
    ImageView ivArrowItem;
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
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_arrow_item, this);

        tvArrowItem = view.findViewById(R.id.tv_arrow_item);
        ivArrowItem = view.findViewById(R.id.iv_arrow_item);
        rlArrowItem = view.findViewById(R.id.rl_arrow_item);
    }

    public void setArrowContent(String content) {
        tvArrowItem.setText(content);
    }
}
