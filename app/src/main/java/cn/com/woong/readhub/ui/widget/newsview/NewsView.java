package cn.com.woong.readhub.ui.widget.newsview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.ajguan.library.EasyRefreshLayout;
import java.util.ArrayList;
import cn.com.woong.readhub.R;
import cn.com.woong.readhub.bean.NewsMo;

/**
 * @author woong
 * Created by wong on 2018/3/12.
 */

public class NewsView extends FrameLayout {
    RecyclerView newsRecyclerView;
    EasyRefreshLayout newsRefreshLayout;

    private NewsAdapter mNewsAdapter;

    public NewsView(@NonNull Context context) {
        this(context, null);
    }

    public NewsView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public NewsView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.view_news, this);

        newsRecyclerView = findViewById(R.id.news_recycler_view);
        newsRefreshLayout = findViewById(R.id.news_refresh_layout);

        mNewsAdapter = new NewsAdapter(getContext());
        newsRecyclerView.setAdapter(mNewsAdapter);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        newsRefreshLayout.addEasyEvent(new EasyRefreshLayout.EasyEvent() {
            @Override
            public void onLoadMore() {
                mOnNewsListener.onNewsLoadMore();
            }

            @Override
            public void onRefreshing() {
                mOnNewsListener.onNewsRefreshing();
            }
        });
    }

    public void updateNews(boolean clear, ArrayList<NewsMo> newsMos) {
        mNewsAdapter.updateNews(clear, newsMos);
        newsRefreshLayout.refreshComplete();
        newsRefreshLayout.loadMoreComplete();
    }

    public void scrollToPosition(int pos) {
        newsRecyclerView.scrollToPosition(pos);
    }

    public void loadMoreComplete() {
        newsRefreshLayout.loadMoreComplete();
    }

    public void refreshComplete() {
        newsRefreshLayout.refreshComplete();
    }

    public interface OnNewsListener {
        void onNewsLoadMore();
        void onNewsRefreshing();
    }

    private OnNewsListener mOnNewsListener;
    public void setOnNewsListener(OnNewsListener listener) {
        mOnNewsListener = listener;
    }
}

