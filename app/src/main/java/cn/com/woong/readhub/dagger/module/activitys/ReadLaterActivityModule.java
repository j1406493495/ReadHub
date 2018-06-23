package cn.com.woong.readhub.dagger.module.activitys;

import cn.com.woong.readhub.base.BasePresenter;
import cn.com.woong.readhub.ui.mine.readlater.ReadLaterActivity;
import cn.com.woong.readhub.ui.topic.TopicAdapter;
import cn.com.woong.readhub.ui.widget.ShowEmptyRecyclerView;
import cn.com.woong.readhub.ui.widget.newsview.NewsAdapter;
import dagger.Module;
import dagger.Provides;

/**
 * Created by wong on 2018/6/15.
 */

@Module
public class ReadLaterActivityModule {
    @Provides
    public ShowEmptyRecyclerView providerShowEmptyRecyclerView(ReadLaterActivity readLaterActivity) {
        return new ShowEmptyRecyclerView(readLaterActivity);
    }

    @Provides
    public TopicAdapter providerTopicAdapter(ReadLaterActivity readLaterActivity) {
        return new TopicAdapter(readLaterActivity);
    }

    @Provides
    public NewsAdapter providerNewsAdapter(ReadLaterActivity readLaterActivity) {
        return new NewsAdapter(readLaterActivity);
    }

    @Provides
    public BasePresenter providerPresenter() {
        return new BasePresenter();
    }
}
