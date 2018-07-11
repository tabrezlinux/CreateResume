package com.example.tabrezahmad.createresume;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ResumeListAdapter extends RecyclerView.Adapter<ResumeListAdapter.ResumeViewHolder> {

    private Context context;
    private List<ResumeListModel> resumeListModels;

    public ResumeListAdapter(Context context, List<ResumeListModel> resumeListModels) {
        this.context = context;
        this.resumeListModels = resumeListModels;
    }

    @Override
    public ResumeListAdapter.ResumeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.resume_list, null);

        ResumeViewHolder resumeViewHolder = new ResumeViewHolder(view);
        return resumeViewHolder;
    }

    @Override
    public void onBindViewHolder(ResumeListAdapter.ResumeViewHolder holder, int position) {

        ResumeListModel resumeListModel = resumeListModels.get(position);

        holder.tv_resume_name.setText(resumeListModel.getResume_list_name());
        holder.tv_resume_fname.setText(resumeListModel.getResume_list_fname());
        holder.iv_resume_list.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_menu_camera));


    }

    @Override
    public int getItemCount() {
        return resumeListModels.size();
    }

    public class ResumeViewHolder extends RecyclerView.ViewHolder{

        ImageView iv_resume_list;
        TextView tv_resume_name, tv_resume_fname;

        public ResumeViewHolder(View itemView) {
            super(itemView);

            iv_resume_list = itemView.findViewById(R.id.iv_resume_list);
            tv_resume_name = itemView.findViewById(R.id.resume_list_name);
            tv_resume_fname = itemView.findViewById(R.id.resume_list_fname);

        }
    }
}
