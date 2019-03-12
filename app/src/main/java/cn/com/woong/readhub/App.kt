package cn.com.woong.readhub

import android.app.Application
import cn.com.woong.readhub.db.DBManager
import cn.com.woong.readhub.network.ApiManager
import com.blankj.utilcode.util.Utils
import com.facebook.stetho.Stetho

/**
 * Created by wong on 2019/01/21.
 * @author Woong
 */
class App : Application() {
    private var mApiManager: ApiManager? = null

    companion object {
        val sInstance: App by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            App()
        }
        lateinit var sContext: App
    }

    override fun onCreate() {
        super.onCreate()
        sContext = this
        mApiManager = ApiManager()
        Utils.init(this)
        DBManager.getInstance(this).init()
        Stetho.initializeWithDefaults(this)
    }

    fun <T> apiService(clz: Class<T>): T? {
        return sInstance.mApiManager?.getService(clz)
    }
}