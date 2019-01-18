package cn.com.woong.readhub.dagger.module.activitys;

import cn.com.woong.readhub.dagger.scope.PerActivity;
import cn.com.woong.readhub.ui.topic.topicdetail.TopicDetailActivity;
import cn.com.woong.readhub.ui.widget.newsview.NewsAdapter;
import dagger.Module;
import dagger.Provides;

/**
 * @author woong
 * Created by wong on 2018/4/22.
 */

//@PerActivity
@Module
public class TopicDetailActivityModule {
    @Provides
    public NewsAdapter providerNewsAdapter(TopicDetailActivity topicDetailActivity) {
        return new NewsAdapter(topicDetailActivity);
    }
}
