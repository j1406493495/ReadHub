package cn.com.woong.readhub.ui.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;
import cn.com.woong.readhub.R;

/**
 * Created by wong on 2018/5/13.
 */

public class ShowEmptyRecyclerView extends FrameLayout {
    private RecyclerView recyclerView;
    private ViewStub viewStubEmpty;
    private EmptyView emptyView;

    public ShowEmptyRecyclerView(@NonNull Context context) {
        this(context, null);
    }

    public ShowEmptyRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShowEmptyRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_show_empty_recyclerview, this);
        recyclerView = view.findViewById(R.id.recycler_view);
        viewStubEmpty = view.findViewById(R.id.view_stub_empty);
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public void showEmptyView(boolean show, String content) {
        if (show) {
            if (emptyView == null) {
                View view = viewStubEmpty.inflate();
                emptyView = new EmptyView(getContext(), view);
            }

            recyclerView.setVisibility(GONE);
            emptyView.setVisibility(VISIBLE);
            emptyView.setIvContent(R.drawable.ic_empty);
            emptyView.setTitle(content);
        } else {
            recyclerView.setVisibility(VISIBLE);
            emptyView.setVisibility(GONE);
        }
    }
}
