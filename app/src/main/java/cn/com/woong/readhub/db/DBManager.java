package cn.com.woong.readhub.db;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import cn.com.woong.readhub.bean.DaoMaster;
import cn.com.woong.readhub.bean.DaoSession;
import cn.com.woong.readhub.bean.NewsMo;
import cn.com.woong.readhub.bean.NewsMoDao;
import cn.com.woong.readhub.bean.TopicMo;
import cn.com.woong.readhub.bean.TopicMoDao;

public class DBManager {
    private Context mContext;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private TopicMoDao mTopicMoDao;
    private NewsMoDao mNewsMoDao;

    private DBManager(Context context) {
        mContext = context;
    }

    private static volatile DBManager instance = null;
    public static DBManager getInstance(Context context){
        if (instance==null){
            synchronized (DBManager.class){
                if (instance==null){
                    instance = new DBManager(context);
                }
            }
        }
        return instance;
    }

    public void init() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(mContext, "readhub.db");
        mDaoMaster = new DaoMaster(helper.getWritableDb());
        mDaoSession = mDaoMaster.newSession();

        mTopicMoDao = mDaoSession.getTopicMoDao();
        mNewsMoDao = mDaoSession.getNewsMoDao();
    }

    public void insertTopicMo(TopicMo topicMo) {
        mTopicMoDao.insertOrReplace(topicMo);
    }

    public void insertNewsMo(NewsMo newsMo) {
        mNewsMoDao.insertOrReplace(newsMo);
    }

    public void deleteTopicMo(TopicMo topicMo) {
        mTopicMoDao.delete(topicMo);
    }

    public void deleteNewsMo(NewsMo newsMo) {
        mNewsMoDao.delete(newsMo);
    }

    public List<TopicMo> queryAllTopicMo() {
        return mTopicMoDao.queryBuilder().build().list();
    }

    public List<NewsMo> queryAllNewsMo() {
        return mNewsMoDao.queryBuilder().build().list();
    }
}