package go.application.com.go;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ForgotPassword extends AppCompatActivity {

    public static int REQUEST_EMAIL_CODE = 9876;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ImageButton btn = (ImageButton) findViewById(R.id.imageButton);
        final EditText text = (EditText) findViewById(R.id.user_name);
        ImageView back = (ImageView) findViewById(R.id.imageView2);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForgotPassword.this,Login.class));
                finish();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String email = text.getText().toString();
//                if(isEmailValid(email)){
//                    Intent intent = new Intent(Intent.ACTION_SEND);
//                    intent.setType("message/rfc822");
//                    intent.putExtra(Intent.EXTRA_EMAIL, new String[] {email});
//                    intent.putExtra(Intent.EXTRA_SUBJECT, "Reset Email");
//                    intent.putExtra(Intent.EXTRA_TEXT, "Click the link to rest password");
//
//                    startActivityForResult(Intent.createChooser(intent, "Send Email"),REQUEST_EMAIL_CODE);
//                }else{
//
//                    Toast.makeText(ForgotPassword.this,"Invalid email address",Toast.LENGTH_SHORT).show();
//
//                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_EMAIL_CODE) {
            if(resultCode == RESULT_OK) {
                startActivity(new Intent(ForgotPassword.this, Login.class));

            } else {

                Intent ingoHome = new Intent(ForgotPassword.this,
                        Login.class);
                ingoHome.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(ingoHome);


            }
        }

        finish();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){

            case android.R.id.home:
                startActivity(new Intent(ForgotPassword.this,Login.class));
                this.finish();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }
}
