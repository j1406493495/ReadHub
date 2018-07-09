package cn.com.woong.readhub.dagger.module;


import cn.com.woong.readhub.dagger.module.fragments.TopicFragmentModule;
import cn.com.woong.readhub.dagger.scope.PerFragment;
import cn.com.woong.readhub.ui.news.NewsFragment;
import cn.com.woong.readhub.ui.topic.TopicFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author woong
 */
@Module()
public abstract class AllFragmentsModule {
    @PerFragment
    @ContributesAndroidInjector(modules = TopicFragmentModule.class)
    abstract TopicFragment contributeTopicFragmentInjector();

    @PerFragment
    @ContributesAndroidInjector()
    abstract NewsFragment contributeNewsFragmentInjector();
}
