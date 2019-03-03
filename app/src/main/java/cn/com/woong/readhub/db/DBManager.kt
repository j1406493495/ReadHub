package cn.com.woong.readhub.db

import android.content.Context
import cn.com.woong.readhub.bean.NewsMo
import cn.com.woong.readhub.bean.TopicMo
import java.nio.file.Files.delete


class DBManager private constructor(private val mContext: Context) {
//    private var mDaoMaster: DaoMaster? = null
//    private var mDaoSession: DaoSession? = null
//    private var mTopicMoDao: TopicMoDao? = null
//    private var mNewsMoDao: NewsMoDao? = null
//
//    fun init() {
//        val helper = DaoMaster.DevOpenHelper(mContext, "readhub.db")
//        mDaoMaster = DaoMaster(helper.getWritableDb())
//        mDaoSession = mDaoMaster!!.newSession()
//
//        mTopicMoDao = mDaoSession!!.getTopicMoDao()
//        mNewsMoDao = mDaoSession!!.getNewsMoDao()
//    }
//
//    fun insertTopicMo(topicMo: TopicMo) {
//        mTopicMoDao!!.insertOrReplace(topicMo)
//    }
//
//    fun insertNewsMo(newsMo: NewsMo) {
//        mNewsMoDao!!.insertOrReplace(newsMo)
//    }
//
//    fun deleteTopicMo(topicMo: TopicMo) {
//        mTopicMoDao!!.delete(topicMo)
//    }
//
//    fun deleteNewsMo(newsMo: NewsMo) {
//        mNewsMoDao!!.delete(newsMo)
//    }
//
//    fun queryAllTopicMo(): List<TopicMo> {
//        return mTopicMoDao!!.queryBuilder().build().list()
//    }
//
//    fun queryAllNewsMo(): List<NewsMo> {
//        return mNewsMoDao!!.queryBuilder().build().list()
//    }
//
//    companion object {
//
//        @Volatile
//        private var instance: DBManager? = null
//
//        fun getInstance(context: Context): DBManager? {
//            if (instance == null) {
//                synchronized(DBManager::class.java) {
//                    if (instance == null) {
//                        instance = DBManager(context)
//                    }
//                }
//            }
//            return instance
//        }
//    }
}