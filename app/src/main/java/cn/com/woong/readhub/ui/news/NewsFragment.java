package cn.com.woong.readhub.ui.news;

import android.content.Context;
import android.view.View;

import cn.com.woong.readhub.R;
import cn.com.woong.readhub.base.BaseFragment;
import dagger.android.support.AndroidSupportInjection;

/**
 * Created by wong on 2018/3/9.
 */

public class NewsFragment extends BaseFragment<NewsPresenter> implements NewsContract.View {
    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    public void setHomeBanners() {

    }
}
