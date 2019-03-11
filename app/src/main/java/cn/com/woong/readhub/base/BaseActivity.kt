package cn.com.woong.readhub.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kaopiz.kprogresshud.KProgressHUD

abstract class BaseActivity<T : BaseContract.Presenter<*>> : AppCompatActivity(), BaseContract.View {
    private var mKProgressHUD: KProgressHUD? = null
    protected lateinit var mPresenter: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layoutId = getLayoutId()
        setContentView(layoutId)
        attachView()
        initView()
        initData()
    }

    override fun onDestroy() {
        super.onDestroy()
        detachView()
    }

    protected abstract fun getLayoutId(): Int
    protected abstract fun initView()
    protected abstract fun initData()

    /**
     * 分离view
     */
    private fun detachView() {
        mPresenter.detachView()
    }

    /**
     * 贴上view
     */
    private fun attachView() {
        mPresenter.attachView(this as Nothing)
    }

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



