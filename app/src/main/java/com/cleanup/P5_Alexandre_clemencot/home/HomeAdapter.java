package com.cleanup.P5_Alexandre_clemencot.home;

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

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder>{
    private List<Task> mTasks;
    private OnItemClickListener mListener;

    // Constructeur pour initialiser la liste de réunions et l'écouteur de clic
    public HomeAdapter(List<Task> tasks) {
        mTasks = tasks;
    }

    // Interface pour écouter les clics sur les éléments de la liste
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    // Méthode pour définir l'écouteur de clic
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    // ViewHolder pour stocker les vues de l'élément de la liste de réunions
    // ViewHolder pour stocker les vues de l'élément de la liste de réunions
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private AppCompatImageView imgProject;
        private TextView lblTaskName;
        private TextView lblProjectName;
        private AppCompatImageView imgDelete;

        public ViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            imgDelete = itemView.findViewById(R.id.img_project);
            lblTaskName = itemView.findViewById(R.id.lbl_task_name);
            lblProjectName = itemView.findViewById(R.id.lbl_project_name);
            imgDelete = itemView.findViewById(R.id.img_delete);

            imgDelete = itemView.findViewById(R.id.img_delete);
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

        /**
         * Binds a task to the item view.
         *
         * @param task the task to bind in the item view
         */
        void bind(Task task) {
            lblTaskName.setText(task.getName());
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

    // onBindViewHolder pour mettre à jour les vues de l'élément de la liste de réunions
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task task = mTasks.get(position);
        holder.bind(task);
    }

    // getItemCount pour renvoyer le nombre d'éléments dans la liste de réunions
    @Override
    public int getItemCount() {
        return mTasks.size();
    }

    public void removeTask(int position) {
        if (position >= 0 && position < mTasks.size()) {
            mTasks.remove(position);
            notifyItemRemoved(position);
        }
    }

}