package com.example.basicunitconvertersir;
//453408765800-4i38frcmo6frcs3jmgk4cm5iu8513v7p.apps.googleusercontent.com//
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    GoogleSignInOptions gso;
    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setOnClickListener(this);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account == null)
        {            Toast.makeText(this, "Please sign in with Gmail account", Toast.LENGTH_SHORT).show();
        }        else
        {            Intent intent = new Intent(this,home.class);
                    intent.putExtra("Name",account.getDisplayName());
                    intent.putExtra("Email",account.getEmail());
                    startActivity(intent);
        }
    }
    @Override
    public void onClick(View view)
    {        switch(view.getId())
    {            case R.id.sign_in_button:
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent,10);
                break;        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        // if (requestCode == 10) {
        // The Task returned from this call is always completed, no need to attach
        // a listener.
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        handleSignInResult(task);
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
    try
    {
        GoogleSignInAccount account = completedTask.getResult(ApiException.class);
        Toast.makeText(this,"Already signed in",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,home.class);
        intent.putExtra("Name",account.getDisplayName());
        intent.putExtra("Email",account.getEmail());
        startActivity(intent);
    }
    catch (ApiException e) {
        // The ApiException status code indicates the detailed failure reason.
        // Please refer to the GoogleSignInStatusCodes class reference for more information.
       // Log.w("OH NO!", "signInResult:failed code=" + e.getStatusCode());
        Toast.makeText(this, "Sorry, you're unable to sign in",Toast.LENGTH_SHORT).show();
    }    }
}
