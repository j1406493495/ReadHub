package cn.com.woong.readhub.base

open class BasePresenter<T : BaseContract.View> : BaseContract.Presenter<T> {
    protected var mView: T? = null

    override fun attachView(view: T) {
        this.mView = view
    }

    override fun detachView() {
        mView?.let { mView = null }
    }
}