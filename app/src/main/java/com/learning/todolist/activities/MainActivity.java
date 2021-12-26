package com.learning.todolist.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.learning.todolist.R;
import com.learning.todolist.activities.AddActivity;
import com.learning.todolist.db.CRUDHelper;
import com.learning.todolist.db.CrudHelperImpl;
import com.learning.todolist.db.DbHandler;
import com.learning.todolist.entities.Todo;
import com.learning.todolist.views.TodoListAdapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private List<Todo> todos = new ArrayList<Todo>();
    private TodoListAdapter todoAdapter;
    private DbHandler handler = null;
    private CRUDHelper db;

    @BindView(R.id.fab_new_task)
    FloatingActionButton fabNewTask;

    @BindView(R.id.todo_list)
    RecyclerView todolistRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.handler = new DbHandler(this);
        this.db = new CrudHelperImpl(this.handler);

        ButterKnife.bind(this);
        populateRecycleView();
    }

    private void populateRecycleView() {
        todos = db.findAll();
        todoAdapter = new TodoListAdapter(this, todos);

        todolistRV.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        todolistRV.setItemAnimator(new DefaultItemAnimator());
        todolistRV.setAdapter(todoAdapter);
        setItemAnimation();
    }

    private void setItemAnimation() {
        ItemTouchHelper.SimpleCallback simpleCallback =  new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                deleteTodo(viewHolder.getAdapterPosition());
            }
        };

        ItemTouchHelper itemTouchHelper =  new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(todolistRV);
    }

    public void deleteTodo(int pos) {
        new AlertDialog.Builder(this)
                .setTitle("TodoList")
                .setMessage("Are you sure?")
                .setPositiveButton("Yes", (dialogInterface, btn) -> {
                        dialogInterface.dismiss();
                        deleteTodoFromDb(pos);
                    }
                )
                .setNegativeButton("No", (dialog, btn) -> dialog.dismiss())
                .show();
    }

    public void deleteTodoFromDb(int pos) {
        Todo todo = todos.get(pos);
        this.db.delete(todo);
        todos.remove(pos);
        Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
        populateRecycleView();
    }

    @OnClick(R.id.fab_new_task)
    public void onClick(View view) {
        Intent addTask = new Intent(this, AddActivity.class);
        startActivity(addTask);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}