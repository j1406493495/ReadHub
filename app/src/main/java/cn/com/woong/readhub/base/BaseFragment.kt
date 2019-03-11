package cn.com.woong.readhub.base

import android.view.ViewGroup
import android.view.LayoutInflater
import android.app.Fragment
import android.os.Bundle
import android.support.annotation.Nullable
import android.view.View
import com.kaopiz.kprogresshud.KProgressHUD

abstract class BaseFragment<T : BaseContract.Presenter<*>> : Fragment(), BaseContract.View {
    private var mRootView: View? = null
    private var mKProgressHUD: KProgressHUD? = null
    protected lateinit var mPresenter: T

    @Nullable
    override fun onCreateView(inflater: LayoutInflater, @Nullable container: ViewGroup, @Nullable savedInstanceState: Bundle): View? {
        inflaterView(inflater, container)
        attachView()
        initView(mRootView)
        return mRootView
    }

    override fun onActivityCreated(@Nullable savedInstanceState: Bundle) {
        super.onActivityCreated(savedInstanceState)
        initData()
    }

    /**
     * 设置View
     *
     * @param inflater
     * @param container
     */
    private fun inflaterView(inflater: LayoutInflater, @Nullable container: ViewGroup) {
        if (mRootView == null) {
            mRootView = inflater.inflate(getLayoutId(), container, false)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        detachView()
    }

    protected abstract fun getLayoutId(): Int
    protected abstract fun initView(view: View?)
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
        mKProgressHUD = KProgressHUD.create(activity)
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
