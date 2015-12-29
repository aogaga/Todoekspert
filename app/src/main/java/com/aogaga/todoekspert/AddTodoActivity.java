package com.aogaga.todoekspert;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class AddTodoActivity extends AppCompatActivity {

    public static final String TODO = "content";
    public static final String DONE = "done";
    private  EditText contextEditText;
    private   CheckBox doneCheckBox;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_actity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        contextEditText = (EditText) findViewById(R.id.content_et);
        doneCheckBox = (CheckBox) findViewById(R.id.done_ch);
        saveButton = (Button) findViewById(R.id.save_btn);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String content = contextEditText.getText().toString();
                boolean isDone = doneCheckBox.isChecked();

                Todo todo = new Todo();
                todo.content = content;
                todo.done = isDone;
                Intent intent = new Intent();
                intent.putExtra(TODO, todo);

                setResult(RESULT_OK, intent);
                finish();

            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
