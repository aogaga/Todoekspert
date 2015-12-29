package com.aogaga.todoekspert;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button;
    private EditText usernameEditText;
    private EditText passwordEditText;
    public  static  final String TAG = LoginActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        button = (Button) findViewById(R.id.login_btn);
        usernameEditText = (EditText) findViewById(R.id.username_et);
        passwordEditText = (EditText) findViewById(R.id.password_et);

        button.setOnClickListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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

    @Override
    public void onClick(View v) {
        Log.d("Action", "Button Click");
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        boolean isError = false;

        /**if(!Patterns.EMAIL_ADDRESS.matcher(username).matches()){
            usernameEditText.setError("This Field has to be email");
            isError = true;
        }
        */

        if(TextUtils.isEmpty(username)){
            usernameEditText.setError("This Field is Required");
           isError = true;
        }

        if(TextUtils.isEmpty(password)){
            passwordEditText.setError(getString(R.string.this_field_is_required));
            isError = true;
        }

        if(!(isError)){
            login(username, password);
            Log.d("isError", Boolean.toString(isError));
        }


    }

    private void login(String username, String password) {
        AsyncTask<String, Integer, Boolean> asyncTask  = new AsyncTask<String, Integer, Boolean>()
        {

            @Override
            protected Boolean doInBackground(String... params) {
                String username = params[0];
                String password = params[1];

                for(int i =0; i < 100; i++){

                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    publishProgress(i);
                }


                return username.equals("test") && password.equals("test");

            }


            @Override
            protected  void  onProgressUpdate(Integer... values){
                super.onProgressUpdate(values);
                Log.d(TAG, "Progress " + values[0]);
            }


            @Override
            protected  void  onPostExecute(Boolean logged){

                super.onPostExecute(logged);
                button.setEnabled(true);
                if(logged){
                    Toast.makeText(getApplicationContext(), "Login Ok", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), TodoListActivity.class);
                    startActivity(intent);
                    finish();
                }
            }


            @Override
            protected void  onPreExecute(){
                    super.onPreExecute();
                button.setEnabled(false);
            }







        };


        asyncTask.execute(username, password);


    }



}
