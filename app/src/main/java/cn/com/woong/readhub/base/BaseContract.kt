package cn.com.woong.readhub.base

interface BaseContract {
    interface IView {
        fun showLoading()
        fun hideLoading()
    }

    interface IPresenter<V : IView> {
        fun getView(): V
    }
}
