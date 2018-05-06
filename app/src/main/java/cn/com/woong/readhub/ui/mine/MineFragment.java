package cn.com.woong.readhub.ui.mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.com.woong.readhub.R;
import cn.com.woong.readhub.base.BaseFragment;
import cn.com.woong.readhub.ui.mine.about.AboutActivity;
import cn.com.woong.readhub.ui.widget.ArrowItemView;

/**
 * Created by wong on 2018/3/9.
 */

public class MineFragment extends BaseFragment<MineContract.Presenter> implements MineContract.View {
    @BindView(R.id.arrow_about)
    ArrowItemView arrowAbout;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(View view) {
    }

    @Override
    protected void initData() {
        arrowAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AboutActivity.startAboutActivity(getActivity());
            }
        });
    }
}
