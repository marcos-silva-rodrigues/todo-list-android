package com.learning.todolist.views;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.learning.todolist.R;
import com.learning.todolist.entities.Todo;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TodoViewerHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.todo_id)
    TextView txtTodoId;

    @BindView(R.id.todo_title)
    TextView txtTodoTitle;

    @BindView(R.id.todo_desc)
    TextView txtTodoDesc;


    public TodoViewerHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindElements(Context ctx, Todo todo) {
        txtTodoId.setId(Integer.parseInt(todo.getId().toString()));
        txtTodoTitle.setText(todo.getTitle());
        txtTodoDesc.setText(todo.getDescription());
    }
}
