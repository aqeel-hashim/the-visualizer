package go.application.com.go;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import go.application.com.go.Utilities.DatabaseOperations;
import go.application.com.go.Utilities.SessionManager;


public class Register extends AppCompatActivity {
    EditText USER_NAME, USER_PASS, CON_PASS;
    String user_name, user_pass, con_pass;
    Button REG;
    Context ctx = this;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        USER_NAME = (EditText) findViewById(R.id.nametext);
        USER_PASS = (EditText) findViewById(R.id.passwordtext);
        CON_PASS = (EditText) findViewById(R.id.confirmpassword);
        REG = (Button) findViewById(R.id.registerbutton);
        REG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_name = USER_NAME.getText().toString();
                user_pass = USER_PASS.getText().toString();
                con_pass = CON_PASS.getText().toString();

                if(!(user_pass.equals(con_pass)))
                {
                    Toast.makeText(getBaseContext(), "Password are not matching",Toast.LENGTH_LONG).show();
                    USER_NAME.setText("");
                    USER_PASS.setText("");
                    CON_PASS.setText("");
                }
                else
                {
                    DatabaseOperations DB = new DatabaseOperations(ctx);
                    DB.putInformation(DB, user_name, user_pass);
                    SessionManager session = new SessionManager(getApplicationContext());
                    session.checkLogin();
                    Toast.makeText(getBaseContext(), "Registration success", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });


    }
    }


//
//    protected void addFragment(@IdRes int containerViewId,
//                               @NonNull Fragment fragment,
//                               @NonNull String fragmentTag) {
//        getSupportFragmentManager()
//                .beginTransaction()
//                .add(containerViewId, fragment, fragmentTag)
//                .disallowAddToBackStack()
//                .commit();
//    }
//
//    protected void replaceFragment(@IdRes int containerViewId,
//                                   @NonNull Fragment fragment,
//                                   @NonNull String fragmentTag,
//                                   @Nullable String backStackStateName) {
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(containerViewId, fragment, fragmentTag)
//                .addToBackStack(backStackStateName)
//                .commit();
//    }

