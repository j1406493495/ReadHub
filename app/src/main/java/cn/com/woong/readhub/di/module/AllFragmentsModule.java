package cn.com.woong.readhub.di.module;


import cn.com.woong.readhub.di.component.BaseFragmentComponent;
import cn.com.woong.readhub.di.module.fragments.NewsFragmentModule;
import cn.com.woong.readhub.di.module.fragments.TopicFragmentModule;
import cn.com.woong.readhub.di.scope.PerFragment;
import cn.com.woong.readhub.ui.news.NewsFragment;
import cn.com.woong.readhub.ui.topic.TopicFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author woong
 */
@Module(subcomponents = {
        BaseFragmentComponent.class
})
public abstract class AllFragmentsModule {
    @PerFragment
    @ContributesAndroidInjector(modules = TopicFragmentModule.class)
    abstract TopicFragment contributeTopicFragmentInjector();

    @PerFragment
    @ContributesAndroidInjector(modules = NewsFragmentModule.class)
    abstract NewsFragment contributeNewsFragmentInjector();
}
