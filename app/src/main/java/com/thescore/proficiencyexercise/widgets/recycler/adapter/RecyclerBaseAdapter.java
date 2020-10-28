package com.thescore.proficiencyexercise.widgets.recycler.adapter;

import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

import com.thescore.proficiencyexercise.widgets.recycler.holder.BaseHolder;

public abstract class RecyclerBaseAdapter<M, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    public static final int HEADER = 98, FOOTER = 99;
    private boolean isShowHeader = false, isShowFooter = false;

    @Override
    public abstract VH onCreateViewHolder(ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(VH holder, int position) {
        onBindViewHolder(holder, getItemByPos(position), position);
    }

    public abstract void onBindViewHolder(VH holder, M model, int position);

    protected abstract M getListItemByPos(int pos);

    protected abstract int getListSize();

    public boolean isEmpty() {
        return getListSize() == 0;
    }

    @Override
    public void onViewRecycled(VH holder) {
        super.onViewRecycled(holder);
        if (holder != null && holder instanceof BaseHolder) {
            //((BaseHolder) holder).clear();
        }
    }

    @Override
    public int getItemCount() {
        return getListSize() + getPosOffset() + (isShowFooter ? 1 : 0);
    }

    public M getItemByPos(int position) {
        return getListItemByPos(position - getPosOffset());
    }

    @Override
    public int getItemViewType(int position) {
        if (isShowHeader && position == 0) {
            return HEADER;
        } else if (isShowFooter && getItemCount() - 1 == position) {
            return FOOTER;
        } else {
            return super.getItemViewType(position);
        }
    }


    public boolean isShowHeader() {
        return isShowHeader;
    }

    public void showHeader(boolean show) {
        if (isShowHeader == show) return;
        isShowHeader = show;
        if (show) notifyItemInserted(0);
        else notifyItemRemoved(0);
    }

    public boolean isShowFooter() {
        return isShowFooter;
    }

    public void showFooter(boolean show) {
        if (isShowFooter == show) return;
        isShowFooter = show;
        if (show) notifyItemInserted(getItemCount());
        else notifyItemRemoved(getItemCount() + 1); //remove with footer
    }

    public int getPosOffset() {
        return isShowHeader ? 1 : 0;
    }

}
