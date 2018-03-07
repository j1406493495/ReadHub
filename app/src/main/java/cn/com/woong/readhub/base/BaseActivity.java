package cn.com.woong.readhub.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.ToastUtils;
import javax.inject.Inject;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.com.woong.readhub.R;

/**
 * @author wong
 */
public abstract class BaseActivity<T extends BaseContract.BasePresenter> extends Activity implements BaseContract.BaseView {
    @Nullable
    @Inject
    protected T mPresenter;

    private Unbinder unbinder;

    protected abstract int getLayoutId();

    protected abstract void initView();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutId = getLayoutId();
        setContentView(layoutId);
        unbinder = ButterKnife.bind(this);
        attachView();
        initView();
        if (!NetworkUtils.isConnected()) {
            showNoNet();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        detachView();
    }

    @Override
    public void showLoading() {}

    @Override
    public void hideLoading() {}

    @Override
    public void showSuccess(String successMsg) {
        ToastUtils.showShort(successMsg);
    }

    @Override
    public void showFaild(String errorMsg) {
        ToastUtils.showShort(errorMsg);
    }

    @Override
    public void showNoNet() {
        ToastUtils.showShort(R.string.network_connect_error);
    }

    @Override
    public void onRetry() {}

    /**
     * 贴上view
     */
    private void attachView() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    /**
     * 分离view
     */
    private void detachView() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
