package cn.com.woong.readhub.ui.widget.newsview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import javax.inject.Inject;

import cn.com.woong.readhub.App;
import cn.com.woong.readhub.R;
import cn.com.woong.readhub.bean.NewsMo;
import cn.com.woong.readhub.db.DBManager;

/**
 * Created by wong on 2018/3/9.
 */

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private ArrayList<NewsMo> mNewsMos = new ArrayList<>();
    private boolean mShowDelete;

//    @Inject
    public NewsAdapter(Context context) {
        mContext = context;
    }

    public void showDelete(boolean show) {
        mShowDelete = show;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(App.Companion.getAppContext()).inflate(R.layout.item_news, parent, false);
        return new NewsViewHolder(mContext, view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mShowDelete) {
            ((NewsViewHolder) holder).showDelete();
        }

        ((NewsViewHolder) holder).bind(mNewsMos.get(position));
    }

    @Override
    public int getItemCount() {
        return mNewsMos.size();
    }

    public void updateNews(boolean clear, ArrayList<NewsMo> newsMos) {
        if (clear) {
            mNewsMos.clear();
        }

        mNewsMos.addAll(newsMos);
        notifyDataSetChanged();
    }

    public void removeNews(int position) {
        DBManager.getInstance(mContext).deleteNewsMo(mNewsMos.get(position));
        mNewsMos.remove(position);
        notifyItemRemoved(position);
    }
}
