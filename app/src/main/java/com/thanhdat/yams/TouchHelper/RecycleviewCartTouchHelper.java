package com.thanhdat.yams.TouchHelper;

import android.graphics.Canvas;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.thanhdat.yams.Adapter.CartAdapter;
import com.thanhdat.yams.Interfaces.ItemtouchHelperListener;

public class RecycleviewCartTouchHelper extends ItemTouchHelper.SimpleCallback {
    private ItemtouchHelperListener listener;
    public RecycleviewCartTouchHelper(int dragDirs, int swipeDirs , ItemtouchHelperListener listener) {
        super(dragDirs, swipeDirs);
        this.listener =listener;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        if (listener!=null){
            listener.onSwiped(viewHolder);
        }
    }

    @Override
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
        if(viewHolder!=null){
            View foregroundview = ((CartAdapter.CartViewHolder)viewHolder).itemView;
            getDefaultUIUtil().onSelected(foregroundview);
        }
    }

    @Override
    public void onChildDrawOver(@NonNull Canvas c, @NonNull RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        View foregroundview = ((CartAdapter.CartViewHolder)viewHolder).itemView;
        getDefaultUIUtil().onDrawOver(c,recyclerView,foregroundview,dX,dY,actionState,isCurrentlyActive);
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        View foregroundview = ((CartAdapter.CartViewHolder)viewHolder).itemView;
        getDefaultUIUtil().onDraw(c, recyclerView,foregroundview,dX,dY,actionState,isCurrentlyActive);
    }

    @Override
    public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        View foregroundview = ((CartAdapter.CartViewHolder)viewHolder).itemView;
        getDefaultUIUtil().clearView(foregroundview);
    }
}
