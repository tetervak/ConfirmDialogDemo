/* Alex Tetervak, Sheridan College, Ontario */
package ca.javateacher.confirmdialogdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
    implements ConfirmFragment.ConfirmListener{

  private static final String ABOUT_FRAGMENT = "aboutFragment";
  private static final String CONFIRM_FRAGMENT = "confirmFragment";
  private static final String STATUS = "status";

  private boolean mConfirmed;
  private TextView mMessageView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    mMessageView = findViewById(R.id.message);

    if(savedInstanceState != null){
      mConfirmed = savedInstanceState.getBoolean(STATUS);
      if(mConfirmed){
        mMessageView.setText(R.string.confirmed);
      }else{
        mMessageView.setText(R.string.not_confirmed);
      }
    }else{
      mConfirmed = false;
    }

    Button confirmButton = findViewById(R.id.confirm_button);
    confirmButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        ConfirmFragment confirmFragment
            = ConfirmFragment.newInstance(0, getString(R.string.confirm_message));
        confirmFragment.show(getSupportFragmentManager(), CONFIRM_FRAGMENT);
      }
    });

    Button resetButton = findViewById(R.id.reset_button);
    resetButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mConfirmed = false;
        mMessageView.setText(R.string.not_confirmed);
      }
    });

  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putBoolean(STATUS, mConfirmed);
  }

  @Override
  public void onConfirmed(int dialogID) {
    mConfirmed = true;
    mMessageView.setText(R.string.confirmed);
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
    if (id == R.id.about) {
      AboutFragment aboutFragment = AboutFragment.newInstance();
      aboutFragment.show(getSupportFragmentManager(), ABOUT_FRAGMENT);
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

}
