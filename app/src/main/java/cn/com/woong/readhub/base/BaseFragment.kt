package cn.com.woong.readhub.base

import android.view.ViewGroup
import android.view.LayoutInflater
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.view.View
import com.kaopiz.kprogresshud.KProgressHUD

abstract class BaseFragment<P : BaseContract.IPresenter<*>> : Fragment(), BaseContract.IView {
    private var mRootView: View? = null
    private var mKProgressHUD: KProgressHUD? = null
    protected lateinit var mPresenter: P

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        inflaterView(inflater, container)
        return mRootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView(mRootView)
        initData()
    }

    /**
     * 设置View
     *
     * @param inflater
     * @param container
     */
    private fun inflaterView(inflater: LayoutInflater, @Nullable container: ViewGroup?) {
        if (mRootView == null) {
            mRootView = inflater.inflate(getLayoutId(), null)
        }
    }

    protected abstract fun getLayoutId(): Int
    protected abstract fun initView(view: View?)
    protected abstract fun initData()

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
