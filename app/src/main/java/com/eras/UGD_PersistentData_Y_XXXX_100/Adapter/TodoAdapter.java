package com.eras.UGD_PersistentData_Y_XXXX_100.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eras.UGD_PersistentData_Y_XXXX_100.Database.DatabaseTodo;
import com.eras.UGD_PersistentData_Y_XXXX_100.Model.Todo;
import com.eras.UGD_PersistentData_Y_XXXX_100.R;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {
    private List<Todo> todoList;
    private Context context;
    private DatabaseTodo databaseTodo;

    public TodoAdapter(List<Todo> todoList, Context context) {
        this.todoList = todoList;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //init view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_todo,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Todo todo = todoList.get(position);
        holder.tvTitle.setText(todo.getTitle());
        databaseTodo = DatabaseTodo.getInstance(context);

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseTodo.getDatabase()
                        .todoDao()
                        .deleteTodo(todo);
                Toast.makeText(context, "Berhasil menghapus", Toast.LENGTH_SHORT).show();
                todoList.remove(holder.getAdapterPosition());
                notifyDataSetChanged();
            }
        });

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_update);
                int width = WindowManager.LayoutParams.MATCH_PARENT;
                int height = WindowManager.LayoutParams.WRAP_CONTENT;

                dialog.getWindow().setLayout(width,height);
                dialog.show();

                EditText edt_todoEdit = dialog.findViewById(R.id.edt_todoEdit);
                Button btnUpdate = dialog.findViewById(R.id.btnUpdate);

                edt_todoEdit.setText(todo.getTitle());

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(!edt_todoEdit.getText().toString().isEmpty()){
                            todo.setTitle(edt_todoEdit.getText().toString());
                            databaseTodo.getDatabase()
                                    .todoDao()
                                    .updateTodo(todo);
                            todoList.clear();
                            todoList.addAll(databaseTodo.getDatabase().todoDao().getAll());
                            notifyDataSetChanged();
                            dialog.dismiss();
                            Toast.makeText(context, "Berhasil mengubah data", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Ga boleh kosong titlenya isi yaaa", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private ImageButton btnDelete,btnEdit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnEdit = itemView.findViewById(R.id.btnEdit);

        }
    }
}
