package cn.com.woong.readhub.di.module.fragments;

import android.content.Context;

import cn.com.woong.readhub.di.scope.PerFragment;
import cn.com.woong.readhub.ui.common.NewsAdapter;
import dagger.Module;
import dagger.Provides;

/**
 * Created by wong on 2018/3/9.
 */

@PerFragment
@Module
public class TopicFragmentModule {

    @Provides
    static NewsAdapter providesNewsAdapter(Context context) {
        return new NewsAdapter(context);
    }
}
