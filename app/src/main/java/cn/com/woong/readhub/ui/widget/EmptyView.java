package cn.com.woong.readhub.ui.widget;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import cn.com.woong.readhub.R;


/**
 * @author wong
 * Created by Wong on 2017/05/11.
 */

public class EmptyView {
    private TextView tvTitle;
    private TextView tvContent;
    private ImageView ivContent;
    private View parentView;

    public EmptyView(Context context, View view) {
        parentView = view;

        tvTitle = (TextView) view.findViewById(R.id.tv_title);
        tvContent = (TextView) view.findViewById(R.id.tv_content);
        ivContent = (ImageView) view.findViewById(R.id.iv_content);
    }


    public void setVisibility(int visibility) {
        parentView.setVisibility(visibility);
    }

    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    public void setContent(String content) {
        tvContent.setText(content);
    }

    public void setIvContent(int res) {
        ivContent.setImageResource(res);
    }
}
