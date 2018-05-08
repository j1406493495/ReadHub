package cn.com.woong.readhub.ui.mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.com.woong.readhub.R;
import cn.com.woong.readhub.base.BaseFragment;
import cn.com.woong.readhub.ui.mine.about.AboutActivity;
import cn.com.woong.readhub.ui.mine.readlater.ReadLaterActivity;
import cn.com.woong.readhub.ui.mine.set.SettingActivity;
import cn.com.woong.readhub.ui.widget.ArrowItemView;

/**
 * Created by wong on 2018/3/9.
 */

public class MineFragment extends BaseFragment<MineContract.Presenter> implements MineContract.View {
    @BindView(R.id.arrow_about)
    ArrowItemView arrowAbout;
    @BindView(R.id.arrow_read_later)
    ArrowItemView arrowReadLater;
    @BindView(R.id.arrow_setting)
    ArrowItemView arrowSetting;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(View view) {
    }

    @Override
    protected void initData() {
    }

    @OnClick({R.id.arrow_about, R.id.arrow_read_later, R.id.arrow_setting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.arrow_about:
                AboutActivity.startAboutActivity(getActivity());
                break;
            case R.id.arrow_read_later:
                ReadLaterActivity.startReadLaterActivity(getActivity());
                break;
            case R.id.arrow_setting:
                SettingActivity.startSettingActivity(getActivity());
                break;
            default:
                break;
        }
    }

}
