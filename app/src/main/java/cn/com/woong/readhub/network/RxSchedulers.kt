package cn.com.woong.readhub.network

import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object RxSchedulers {
    fun <T> applySchedulers(): ObservableTransformer<T, T> {
        return ObservableTransformer { upstream -> upstream.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()) }
    }

    fun <T> io_main(): ObservableTransformer<T, T> {
        return applySchedulers<Any>() as ObservableTransformer<T, T>
    }
}