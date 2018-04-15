package cn.com.woong.readhub.ui.news;

import java.util.ArrayList;

import cn.com.woong.readhub.base.BaseContract;
import cn.com.woong.readhub.bean.NewsMo;

/**
 * Created by wong on 2018/3/9.
 */

public interface NewsContract {
    interface View extends BaseContract.BaseView {
        void updateTechNews(String publishDate, ArrayList<NewsMo> newsMos);
        void updateDevelopNews(String publishDate, ArrayList<NewsMo> newsMos);
        void updateBlockchainNews(String publishDate, ArrayList<NewsMo> newsMos);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getTechNews(String publishDate);
        void getDevelopNews(String publishDate);
        void getBlockchainNews(String publishDate);
    }
}
