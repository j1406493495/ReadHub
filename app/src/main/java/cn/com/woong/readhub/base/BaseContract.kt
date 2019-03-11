package cn.com.woong.readhub.base

open class BaseContract {
    interface View {
        fun showLoading()
        fun hideLoading()
    }

    interface Presenter<T : BaseContract.View> {
        fun attachView(view: T)
        fun detachView()
    }
}
