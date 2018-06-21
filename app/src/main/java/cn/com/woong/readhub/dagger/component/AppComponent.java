package cn.com.woong.readhub.dagger.component;

import cn.com.woong.readhub.App;
import cn.com.woong.readhub.dagger.module.AllActivitysModule;
import cn.com.woong.readhub.dagger.module.AllFragmentsModule;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * @author woong
 */
@Component(modules = {
        AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class,
        AllActivitysModule.class,
        AllFragmentsModule.class
})
public interface AppComponent {
    void inject(App app);
}
