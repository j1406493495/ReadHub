package cn.com.woong.readhub.dagger.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * @author woong
 */
@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface PerFragment {
}
