package cn.com.woong.readhub.ui.topic;

import android.content.Context;
import android.view.View;

import java.util.ArrayList;

import butterknife.BindView;
import cn.com.woong.readhub.R;
import cn.com.woong.readhub.base.BaseFragment;
import cn.com.woong.readhub.bean.NewsMo;
import cn.com.woong.readhub.ui.widget.NewsView;
import dagger.android.support.AndroidSupportInjection;

/**
 * @author woong
 *         Created by wong on 2018/3/9.
 */
public class TopicFragment extends BaseFragment<TopicPresenter> implements TopicContract.View {
    @BindView(R.id.topic_news_view)
    NewsView topicNewsView;

    private int mCurrentPage;

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_topic;
    }

    @Override
    protected void initView(View view) {
        mCurrentPage = 0;

        topicNewsView.setOnNewsListener(new NewsView.OnNewsListener() {
            @Override
            public void onNewsLoadMore() {
                ArrayList<NewsMo> mNewsMos = new ArrayList<>();

                for (int i = 0, count = 10; i < count; i++) {
                    NewsMo newsMo = new NewsMo();
                    newsMo.title = "new === 供钢丝等冲等宏观第刚抵港的创恒第刚吃第刚抵过创恒创高创恒创";
                    newsMo.publishDate = "time == " + mCurrentPage + ":" + i;
                    newsMo.summary = "测试供广大的当红的当黄黄扫就发丧公 a 刚僧洪邓程红城等会冲等红第刚抵僧三创刚抵过创刚创刚创刚抵创刚创长谷川长凳坑红僧洪";
                    mNewsMos.add(newsMo);
                }

                topicNewsView.updateNews(mNewsMos, mCurrentPage++);
                topicNewsView.loadMoreComplete();
            }

            @Override
            public void onNewsRefreshing() {
                ArrayList<NewsMo> mNewsMos = new ArrayList<>();
                mCurrentPage = 0;

                for (int i = 0, count = 10; i < count; i++) {
                    NewsMo newsMo = new NewsMo();
                    newsMo.title = "new === 供钢丝等冲等宏观第刚抵港的创恒第刚吃第刚抵过创恒创高创恒创";
                    newsMo.publishDate = "time == " + mCurrentPage + ":" + i;
                    newsMo.summary = "测试供广大的当红的当黄黄扫就发丧公 a 刚僧洪邓程红城等会冲等红第刚抵僧三创刚抵过创刚创刚创刚抵创刚创长谷川长凳坑红僧洪";
                    mNewsMos.add(newsMo);
                }

                topicNewsView.updateNews(mNewsMos, mCurrentPage);
                topicNewsView.refreshComplete();
            }
        });
    }

    @Override
    public void updateData() {

    }
}
