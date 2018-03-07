package cn.com.woong.readhub.di.module;


import cn.com.woong.readhub.di.component.BaseActivityComponent;
import cn.com.woong.readhub.di.scope.PerActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author woong
 */
@Module(subcomponents = {
        BaseActivityComponent.class
})
public abstract class AllActivitysModule {
//
//    @ActivityScope
//    @ContributesAndroidInjector(modules = MainActivityModule.class)
//    abstract MainActivity contributeMainActivitytInjector();
}
