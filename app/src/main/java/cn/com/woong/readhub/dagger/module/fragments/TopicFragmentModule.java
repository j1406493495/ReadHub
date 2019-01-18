package cn.com.woong.readhub.dagger.module.fragments;


import cn.com.woong.readhub.MainActivity;
import cn.com.woong.readhub.dagger.scope.PerFragment;
import cn.com.woong.readhub.ui.topic.TopicAdapter;
import cn.com.woong.readhub.ui.topic.TopicFragment;
import dagger.Module;
import dagger.Provides;

/**
 * Created by wong on 2018/3/9.
 */

//@PerFragment
@Module
public class TopicFragmentModule {
    @Provides
    static TopicAdapter provideTopicAdapter(TopicFragment topicFragment) {
        return new TopicAdapter(topicFragment.getActivity());
    }
}
