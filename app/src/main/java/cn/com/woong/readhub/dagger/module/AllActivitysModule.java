package cn.com.woong.readhub.dagger.module;


import cn.com.woong.readhub.dagger.module.activitys.ReadLaterActivityModule;
import cn.com.woong.readhub.dagger.module.activitys.TopicDetailActivityModule;
import cn.com.woong.readhub.dagger.scope.PerActivity;
import cn.com.woong.readhub.ui.mine.readlater.ReadLaterActivity;
import cn.com.woong.readhub.ui.topic.topicdetail.TopicDetailActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author woong
 */
@Module()
public abstract class AllActivitysModule {
    @PerActivity
    @ContributesAndroidInjector(modules = TopicDetailActivityModule.class)
    abstract TopicDetailActivity contributeTopicDetailActivityInjector();

    @PerActivity
    @ContributesAndroidInjector(modules = ReadLaterActivityModule.class)
    abstract ReadLaterActivity contributeReadLaterActivityInjector();
}
