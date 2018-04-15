package cn.com.woong.readhub.ui.widget.newsview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.TimeUtils;

import java.text.SimpleDateFormat;

import butterknife.ButterKnife;
import cn.com.woong.readhub.R;
import cn.com.woong.readhub.bean.NewsMo;
import cn.com.woong.readhub.utils.CommonUtils;

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

        long publishDate = CommonUtils.getTimeStampByReahubDateString(newsMo.publishDate);
        tvItemTime.setText(TimeUtils.millis2String(publishDate, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")));
    }
}
