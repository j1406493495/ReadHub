package cn.com.woong.readhub.di.module;


import cn.com.woong.readhub.di.component.BaseActivityComponent;
import cn.com.woong.readhub.di.module.activitys.TopicDetailActivityModule;
import cn.com.woong.readhub.di.scope.PerActivity;
import cn.com.woong.readhub.ui.topic.topicdetail.TopicDetailActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author woong
 */
@Module(subcomponents = {
        BaseActivityComponent.class
})
public abstract class AllActivitysModule {
    @PerActivity
    @ContributesAndroidInjector(modules = TopicDetailActivityModule.class)
    abstract TopicDetailActivity contributeTopicDetailActivityInjector();
}
