package cn.com.woong.readhub.ui.common;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.com.woong.readhub.R;
import cn.com.woong.readhub.bean.NewsMo;

/**
 * Created by wong on 2018/3/9.
 */

public class NewsViewHolder extends RecyclerView.ViewHolder {
    TextView tvItemTitle;
    TextView tvItemTime;
    TextView tvItemContent;

    public NewsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(itemView);
        tvItemTitle = itemView.findViewById(R.id.tv_item_title);
        tvItemContent = itemView.findViewById(R.id.tv_item_content);
        tvItemTime = itemView.findViewById(R.id.tv_item_time);
    }

    public void bind(NewsMo newsMo) {
        tvItemTitle.setText(newsMo.title);
        tvItemContent.setText(newsMo.summary);
        tvItemTime.setText(newsMo.publishDate);
    }
}
