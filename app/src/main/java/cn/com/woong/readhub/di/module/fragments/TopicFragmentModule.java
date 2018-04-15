package cn.com.woong.readhub.di.module.fragments;


import cn.com.woong.readhub.App;
import cn.com.woong.readhub.di.scope.PerFragment;
import cn.com.woong.readhub.ui.widget.newsview.NewsAdapter;
import dagger.Module;
import dagger.Provides;

/**
 * Created by wong on 2018/3/9.
 */

@PerFragment
@Module
public class TopicFragmentModule {

    @Provides
    static NewsAdapter providesNewsAdapter() {
        return new NewsAdapter(App.getAppContext());
    }
}
