package go.application.com.go;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import go.application.com.go.Utilities.DatabaseOperations;
import go.application.com.go.Utilities.SessionManager;

public class Login extends AppCompatActivity {
    Button Login;
    EditText USERNAME, USERPASS;
    String username, userpass;
    Context CTX=this;
    SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //  _("LoginActivity Born!");
        setContentView(R.layout.activity_login);
        session = new SessionManager(getApplicationContext());
//        addFragment(android.R.id.content,
//                new LoginFragment(),LoginFragment.FRAGMENT_LOGIN);

        Login = (Button) findViewById(R.id.b_login);
        USERNAME = (EditText) findViewById(R.id.user_name);
        USERPASS = (EditText) findViewById(R.id.user_pass);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Please Wait...", Toast.LENGTH_LONG).show();
                username = USERNAME.getText().toString();
                userpass = USERPASS.getText().toString();
                DatabaseOperations DOP = new DatabaseOperations(CTX);
                Cursor CR = DOP.getInformation(DOP);
                CR.moveToFirst();
                boolean loginstatus = false;
                String NAME = "";

                do {
                    if (username.equals(CR.getString(0)) && (userpass.equals(CR.getString(1)))) {
                        loginstatus = true;
                        NAME = CR.getString(0);

                    }
                }
                while (CR.moveToNext());
                if (loginstatus) {
                    session.createLoginSession("Android Hive", "anroidhive@gmail.com");
                    startActivity(new Intent(getApplicationContext(),Homepage.class));
                    Toast.makeText(getBaseContext(), "Login Success----\n Welcome" + NAME, Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(getBaseContext(), "Login Failed" + NAME, Toast.LENGTH_LONG).show();
                    finish();
                }

            }
        });

    }

//
//
//    protected void addFragment(@IdRes int containerViewId,
//                               @NonNull LoginFragment fragment,
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
//
//                                   @Nullable String backStackStateName) {
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(containerViewId, fragment, fragmentTag)
//                .addToBackStack(backStackStateName)
//                .commit();
//    }



    public void gotoregister(View v)
    {
        Intent intent = new Intent(Login.this, Register.class);
        startActivity(intent);
    }



    public void gotoforgotpassword (View v)
    {
        Intent intent = new Intent(Login.this, ForgotPassword.class);
        startActivity(intent);
    }
}
