package com.learning.todolist.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.learning.todolist.R;
import com.learning.todolist.db.CrudHelperImpl;
import com.learning.todolist.db.DbHandler;
import com.learning.todolist.entities.Todo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddActivity extends AppCompatActivity {

    private DbHandler handler = null;

    @BindView(R.id.txt_task_title)
    EditText txtTitle;

    @BindView(R.id.txt_task_desc)
    EditText txtDesc;

    @BindView(R.id.btn_create_task)
    Button btnCreateTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        this.handler = new DbHandler(this);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_create_task)
    public void save(View view) {
        CrudHelperImpl db = new CrudHelperImpl(this.handler);

        try {
            db.create(new Todo(
                    this.txtTitle.getText().toString(),
                    this.txtDesc.getText().toString()
            ));
            Intent mainActivity = new Intent(this, MainActivity.class);
            startActivity(mainActivity);
            Toast.makeText(this, "Created", Toast.LENGTH_SHORT).show();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}