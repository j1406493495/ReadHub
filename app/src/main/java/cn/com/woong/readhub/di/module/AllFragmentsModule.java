package cn.com.woong.readhub.di.module;


import cn.com.woong.readhub.di.component.BaseFragmentComponent;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author woong
 */
@Module(subcomponents = {
        BaseFragmentComponent.class
})
public abstract class AllFragmentsModule {

//    @ContributesAndroidInjector(modules = HomeFragmentModule.class)
//    abstract HomeFragment contributeHomeFragmentInjector();
}
