package cn.com.woong.readhub.ui.widget.newsview;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.TimeUtils;
import com.blankj.utilcode.util.ToastUtils;

import java.text.SimpleDateFormat;

import cn.com.woong.readhub.R;
import cn.com.woong.readhub.bean.NewsMo;
import cn.com.woong.readhub.db.DBManager;
import cn.com.woong.readhub.ui.news.NewsDetailActivity;
import cn.com.woong.readhub.utils.CommonUtils;

/**
 * Created by wong on 2018/3/9.
 */

public class NewsViewHolder extends RecyclerView.ViewHolder {
    private Context mContext;

    private CardView newsCardView;
    private TextView tvItemTitle;
    private TextView tvItemTime;
    private TextView tvItemAuthor;
    private TextView tvItemContent;
    private ImageView ivCollect;
    private ImageView ivShare;

    public NewsViewHolder(Context context, View itemView) {
        super(itemView);
        mContext = context;

        newsCardView = itemView.findViewById(R.id.news_card_view);
        tvItemTitle = itemView.findViewById(R.id.tv_item_title);
        tvItemContent = itemView.findViewById(R.id.tv_item_content);
        tvItemAuthor = itemView.findViewById(R.id.tv_item_author);
        tvItemTime = itemView.findViewById(R.id.tv_item_time);
        ivCollect = itemView.findViewById(R.id.iv_collect);
        ivShare = itemView.findViewById(R.id.iv_share);
    }

    public void bind(final NewsMo newsMo) {
        tvItemTitle.setText(newsMo.title);
        tvItemContent.setText(newsMo.summary);
        tvItemAuthor.setText(newsMo.siteName + "/" + newsMo.authorName);

        long publishDate = CommonUtils.getTimeStampByReadhubDateString(newsMo.publishDate);
        tvItemTime.setText(TimeUtils.millis2String(publishDate, new SimpleDateFormat("MM-dd HH:mm")));

        newsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewsDetailActivity.startNewsDetailActivity(mContext, newsMo.mobileUrl);
            }
        });

        ivCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShort(R.string.add_read_delay);
                DBManager.getInstance(mContext).insertNewsMo(newsMo);
            }
        });
    }
}
