package com.example.tabrezahmad.createresume;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

public class EditItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private final StartActivity.RVadapter mAdapter;

    public EditItemTouchHelperCallback(StartActivity.RVadapter adapter) {
        mAdapter = adapter;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return false;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                          RecyclerView.ViewHolder target) {
        mAdapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());

        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

        mAdapter.onItemDismiss(viewHolder.getAdapterPosition());


        mAdapter.onItemDismiss(viewHolder.getAdapterPosition());
    }
    @Override
    public void onMoved(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int fromPos, RecyclerView.ViewHolder target, int toPos, int x, int y) {
        super.onMoved(recyclerView, viewHolder, fromPos, target, toPos, x, y);

    }

    public interface ItemTouchHelperAdapter {

        boolean onItemMove(int fromPosition, int toPosition);

        void onItemDismiss(int position);
    }

    public interface ItemTouchHelperViewHolder {

        void onItemSelected();

        void onItemClear();


    }

    public interface OnStartDragListener {

        void onStartDrag(RecyclerView.ViewHolder viewHolder);

    }
}
