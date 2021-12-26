package com.learning.todolist.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.learning.todolist.R;
import com.learning.todolist.entities.Todo;

import java.util.List;

public class TodoListAdapter extends RecyclerView.Adapter<TodoViewerHolder> {

    private Context ctx;
    private List<Todo> todoList;

    public TodoListAdapter(Context ctx, List<Todo> todoList) {
        this.ctx = ctx;
        this.todoList = todoList;
    }

    @NonNull
    @Override
    public TodoViewerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TodoViewerHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.list_todos, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewerHolder holder, int position) {
        holder.bindElements(this.ctx, todoList.get(position));
    }

    @Override
    public int getItemCount() {
        if (todoList == null) return 0;

        return todoList.size();
    }
}
