package cn.com.woong.readhub.ui.widget.newsview

import android.content.Context
import cn.com.woong.readhub.db.DBManager
import cn.com.woong.readhub.bean.NewsMo
import android.support.v7.widget.RecyclerView
import cn.com.woong.readhub.App
import android.view.LayoutInflater
import android.view.ViewGroup
import cn.com.woong.readhub.R


class NewsAdapter constructor(private val mContext: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val mNewsMos = arrayListOf<NewsMo>()
    private var mShowDelete: Boolean = false

    fun showDelete(show: Boolean) {
        mShowDelete = show
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(App.sContext).inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(mContext, view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (mShowDelete) {
            (holder as NewsViewHolder).showDelete()
        }

        (holder as NewsViewHolder).bind(mNewsMos.get(position))
    }

    override fun getItemCount(): Int {
        return mNewsMos.size
    }

    fun updateNews(clear: Boolean, newsMos: ArrayList<NewsMo>) {
        if (clear) {
            mNewsMos.clear()
        }

        mNewsMos.addAll(newsMos)
        notifyDataSetChanged()
    }

    fun removeNews(position: Int) {
        DBManager.getInstance(mContext).deleteNewsMo(mNewsMos[position])
        mNewsMos.removeAt(position)
        notifyItemRemoved(position)
    }
}
