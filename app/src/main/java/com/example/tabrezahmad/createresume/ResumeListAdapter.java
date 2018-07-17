package com.example.tabrezahmad.createresume;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

public class ResumeListAdapter extends RecyclerView.Adapter implements ItemTouchHelperAdapter {

    private List<ResumeListModels> mPersonList;
    OnItemClickListener mItemClickListener;
    private static final int TYPE_ITEM = 0;
    private final LayoutInflater mInflater;
    private final OnStartDragListener mDragStartListener;
    private Context mContext;

    public ResumeListAdapter(Context context, List list, OnStartDragListener dragListner) {
        this.mPersonList = list;
        this.mInflater = LayoutInflater.from(context);
        mDragStartListener = dragListner;
        mContext = context;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        if (viewType == TYPE_ITEM) {
            //inflate your layout and pass it to view holder
            View v = mInflater.inflate(R.layout.person_item, viewGroup, false);
            return new VHItem(v );
        }

        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");

    }

    @Override
    public int getItemViewType(int position) {
        return TYPE_ITEM;
    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {

        if (viewHolder instanceof VHItem) {

            final VHItem holder= (VHItem)viewHolder;
            ((VHItem) viewHolder).title.setText(mPersonList.get(i).getName());
            Picasso.with(mContext)
                    .load(mPersonList.get(i).getImagePath())
                    .placeholder(R.drawable.ic_menu_camera)
                    .into(((VHItem) viewHolder).imageView);

            ((VHItem) viewHolder).image_menu.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                        mDragStartListener.onStartDrag(holder);
                    }
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mPersonList.size();
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public class VHItem extends RecyclerView.ViewHolder implements View.OnClickListener ,ItemTouchHelperViewHolder{
        public TextView title;
        private ImageView imageView;
        private ImageView image_menu;

        public VHItem(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.name);
            image_menu = (ImageView) itemView.findViewById(R.id.image_menu);
            imageView = (ImageView) itemView.findViewById(R.id.circle_imageView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(v, getPosition());
            }
        }

        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(0);
        }
    }

    @Override
    public void onItemDismiss(int position) {
        mPersonList.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        //Log.v("", "Log position" + fromPosition + " " + toPosition);
        if (fromPosition < mPersonList.size() && toPosition < mPersonList.size()) {
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(mPersonList, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(mPersonList, i, i - 1);
                }
            }
            notifyItemMoved(fromPosition, toPosition);
        }
        return true;
    }

    public void updateList(List list) {
        mPersonList = list;
        notifyDataSetChanged();
    }
}

//public class ResumeListAdapter extends RecyclerView.Adapter<ResumeListAdapter.ResumeViewHolder> implements ItemTouchHelperAdapter{
//
//    private Context context;
//    private List<ResumeListModel> resumeListModels;
//
//    public ResumeListAdapter(Context context, List<ResumeListModel> resumeListModels) {
//        this.context = context;
//        this.resumeListModels = resumeListModels;
//    }
//
//    @Override
//    public ResumeListAdapter.ResumeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View view = inflater.inflate(R.layout.resume_list, null);
//
//        ResumeViewHolder resumeViewHolder = new ResumeViewHolder(view);
//        return resumeViewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(ResumeListAdapter.ResumeViewHolder holder, int position) {
//
//        ResumeListModel resumeListModel = resumeListModels.get(position);
//
//        holder.tv_resume_name.setText(resumeListModel.getResume_list_name());
//        holder.tv_resume_fname.setText(resumeListModel.getResume_list_fname());
//        holder.iv_resume_list.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_menu_camera));
//
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return resumeListModels.size();
//    }
//
//    public class ResumeViewHolder extends RecyclerView.ViewHolder{
//
//        ImageView iv_resume_list;
//        TextView tv_resume_name, tv_resume_fname;
//
//        public ResumeViewHolder(View itemView) {
//            super(itemView);
//
//            iv_resume_list = itemView.findViewById(R.id.iv_resume_list);
//            tv_resume_name = itemView.findViewById(R.id.resume_list_name);
//            tv_resume_fname = itemView.findViewById(R.id.resume_list_fname);
//
//        }
//    }

