package cn.com.woong.readhub.di.component;

import cn.com.woong.readhub.base.BaseFragment;
import dagger.Subcomponent;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

/**
 * @author woong
 */
@Subcomponent(modules = {
        AndroidInjectionModule.class,
})
public interface BaseFragmentComponent extends AndroidInjector<BaseFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<BaseFragment> {
    }

}
