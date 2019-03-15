package cn.com.woong.readhub.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kaopiz.kprogresshud.KProgressHUD

abstract class BaseActivity<P : BaseContract.IPresenter<*>> : AppCompatActivity(), BaseContract.IView {
    private var mKProgressHUD: KProgressHUD? = null
    protected lateinit var mPresenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layoutId = getLayoutId()
        setContentView(layoutId)
        initView()
        initData()
    }

    protected abstract fun getLayoutId(): Int
    protected abstract fun initView()
    protected abstract fun initData()

    override fun showLoading() {
        mKProgressHUD = KProgressHUD.create(this)
        mKProgressHUD?.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                ?.setCancellable(true)
                ?.setAnimationSpeed(2)
                ?.setDimAmount(0.5f)
                ?.show()
    }

    override fun hideLoading() {
        mKProgressHUD?.dismiss()
    }
}



