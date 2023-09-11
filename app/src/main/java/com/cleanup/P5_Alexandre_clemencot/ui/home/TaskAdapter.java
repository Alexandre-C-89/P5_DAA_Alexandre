package com.cleanup.P5_Alexandre_clemencot.ui.home;

import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.cleanup.P5_Alexandre_clemencot.model.Project;
import com.cleanup.P5_Alexandre_clemencot.model.Task;
import com.cleanup.todoc.R;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    @NonNull
    private List<Task> mtasks;
    private OnItemClickListener mListener;

    public TaskAdapter(List<Task> tasks) {
        mtasks = tasks;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    // ViewHolder pour stocker les vues de l'élément de la liste de réunions
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final AppCompatImageView imgProject;

        private final TextView lblTaskName;

        private final TextView lblProjectName;

        private final AppCompatImageView imgDelete;

        //private final DeleteTaskListener deleteTaskListener;

        public ViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            imgProject = itemView.findViewById(R.id.img_project);
            lblTaskName = itemView.findViewById(R.id.lbl_task_name);
            lblProjectName = itemView.findViewById(R.id.lbl_project_name);
            imgDelete = itemView.findViewById(R.id.img_delete);

            //deleteTaskListener = itemView.findViewById(R.id.img_delete);
            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                    }
                }
            });
        }
        public void bind(Task task) {
            lblTaskName.setText(task.getTaskDescription());
            imgDelete.setTag(task);

            final Project taskProject = task.getProject();
            if (taskProject != null) {
                imgProject.setSupportImageTintList(ColorStateList.valueOf(taskProject.getColor()));
                lblProjectName.setText(taskProject.getName());
            } else {
                imgProject.setVisibility(View.INVISIBLE);
                lblProjectName.setText("");
            }
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_task, parent, false);
        return new ViewHolder(itemView, mListener);
    }

    void updateTasks(@NonNull final List<Task> tasks) {
        this.mtasks = tasks;
        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task task = mtasks.get(position);
        holder.bind(task);
    }

    @Override
    public int getItemCount() {
        return mtasks.size();
    }

}
