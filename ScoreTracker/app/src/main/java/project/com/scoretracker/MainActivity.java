package project.com.scoretracker;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.os.Vibrator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

// Custom API
import org.brickred.socialauth.android.DialogListener;
import org.brickred.socialauth.android.SocialAuthAdapter;
import org.brickred.socialauth.android.SocialAuthAdapter.Provider;
import org.brickred.socialauth.android.SocialAuthError;
import org.brickred.socialauth.android.SocialAuthListener;


public class MainActivity extends ActionBarActivity {

    private Vibrator myVib;
    public static int homeGoalsCounter;
    public static int homePointsCounter;
    public static int awayGoalsCounter;
    public static int awayPointsCounter;
    SocialAuthAdapter adapter;
    Button createConnection;
    Button updateTwitter;
    EditText homeTeamName;
    EditText awayTeamName;

    Button homeGoalButton;
    Button awayGoalButton;
    Button homePointButton;
    Button awayPointButton;

    Button homeGoalMinusButton;
    Button awayGoalMinusButton;
    Button homePointMinusButton;
    Button awayPointMinusButton;

    TextView homeGoalsTextView;
    TextView awayGoalsTextView;
    TextView homePointsTextView;
    TextView awayPointsTextView;

    Button previewButton;
    EditText summaryEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myVib = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);

        // SocialAuthAdapter displays a drop down menu where you can choose different social media types.
        adapter = new SocialAuthAdapter(new ResponseListener());
        updateTwitter = (Button) (findViewById(R.id.tweet));

        homeGoalsCounter = 0;
        awayGoalsCounter = 0;
        homePointsCounter = 0;
        awayPointsCounter = 0;

        homeTeamName = (EditText) (findViewById(R.id.home_team_name_EditText));
        awayTeamName = (EditText) (findViewById(R.id.away_team_name_EditText));

        homeGoalButton = (Button) (findViewById(R.id.home_team_goal_add_button));
        awayGoalButton = (Button) (findViewById(R.id.away_team_goal_add_button));
        homePointButton = (Button) (findViewById(R.id.home_team_point_add_button));
        awayPointButton = (Button) (findViewById(R.id.away_team_point_add_button));

        homeGoalMinusButton = (Button) (findViewById(R.id.home_team_goal_minus_button));
        awayGoalMinusButton = (Button) (findViewById(R.id.away_team_goal_minus_button));
        homePointMinusButton = (Button) (findViewById(R.id.home_team_point_minus_button));
        awayPointMinusButton = (Button) (findViewById(R.id.away_team_point_minus_button));

        homeGoalsTextView = (TextView) (findViewById(R.id.home_goals_tv));
        awayGoalsTextView = (TextView) (findViewById(R.id.away_goals_tv));
        homePointsTextView = (TextView) (findViewById(R.id.home_points_tv));
        awayPointsTextView = (TextView) (findViewById(R.id.away_points_tv));

        previewButton = (Button) (findViewById(R.id.update_preview_button));
        summaryEditText = (EditText) (findViewById(R.id.preview_editText));

        homeGoalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeGoalsCounter++;
                homeGoalsTextView.setText(Integer.toString(homeGoalsCounter));
                myVib.vibrate(50);
            }
        });

        awayGoalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                awayGoalsCounter++;
                awayGoalsTextView.setText(Integer.toString(awayGoalsCounter));
                myVib.vibrate(50);
            }
        });

        homePointButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homePointsCounter++;
                homePointsTextView.setText(Integer.toString(homePointsCounter));
                myVib.vibrate(50);
            }
        });

        awayPointButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                awayPointsCounter++;
                awayPointsTextView.setText(Integer.toString(awayPointsCounter));
                myVib.vibrate(50);
            }
        });

        homeGoalMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myVib.vibrate(50);
                if (homeGoalsCounter != 0) {
                    homeGoalsCounter--;
                } else {
                    homeGoalsCounter = 0;
                }
                homeGoalsTextView.setText(Integer.toString(homeGoalsCounter));
            }
        });

        awayGoalMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myVib.vibrate(50);
                if (awayGoalsCounter != 0) {
                    awayGoalsCounter--;
                } else {
                    awayGoalsCounter = 0;
                }
                awayGoalsTextView.setText(Integer.toString(awayGoalsCounter));
            }
        });

        homePointMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myVib.vibrate(50);
                if (homePointsCounter != 0) {
                    homePointsCounter--;
                } else {
                    homePointsCounter = 0;
                }
                homePointsTextView.setText(Integer.toString(homePointsCounter));
            }
        });

        awayPointMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myVib.vibrate(50);
                if (awayPointsCounter != 0) {
                    awayPointsCounter--;
                } else {
                    awayPointsCounter = 0;
                }
                awayPointsTextView.setText(Integer.toString(awayPointsCounter));
            }
        });

        previewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myVib.vibrate(50);
                summaryEditText.setText(homeTeamName.getText() + " "
                        + homeGoalsTextView.getText() + "-"
                        + homePointsTextView.getText() + " V "
                        + awayTeamName.getText() + awayGoalsTextView.getText() + "-"
                        + awayPointsTextView.getText());
            }
        });

        // The adapter allows the user to publish via multiple social media platforms (for the purpose of our project we were only
        // required to use Twitter). When the createConnection button is pressed launches the adapter via the SocialAuthAdapter API.
        createConnection = (Button) (findViewById(R.id.create_connection));
        adapter.addProvider(Provider.TWITTER, R.drawable.twitter);
        adapter.addCallBack(Provider.TWITTER, "http://www.kerryetb.ie");
        adapter.enable(createConnection);
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

    public void displayAbout(View view) {
        Intent intent = new Intent(this, DisplayAbout.class);
        startActivity(intent);
    }

    //
    private final class ResponseListener implements DialogListener {

        // Checks the validity of the provided Consumer Key and Secret Key
        public void onComplete(Bundle values) {
            Log.d("ShareButton", "Authentication Successful");

            // Get name of provider after the user credentials and both keys have been authenticated
            final String providerName = values.getString(SocialAuthAdapter.PROVIDER);
            Log.d("ShareButton", "Provider Name = " + providerName);

            // Toast provides a simple feedback popup window to provide user feedback.
            Toast.makeText(MainActivity.this, providerName + " connected", Toast.LENGTH_LONG).show();

            updateTwitter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adapter.updateStatus(summaryEditText.getText().toString(), new MessageListener(), false);
                }
            });
        }


        @Override
        public void onError(SocialAuthError error) {
            Log.d("ShareButton", "Authentication Error: " + error.getMessage());
        }

        @Override
        public void onCancel() {
            Log.d("ShareButton", "Authentication Cancelled");
        }

        @Override
        public void onBack() {
            Log.d("Share-Button", "Dialog Closed by pressing Back Key");
        }
    }

    // Each tweet posted by the user is validated against status codes supplied by the Providers API and
    // returns a Toast popup with either messages listed below.
    private final class MessageListener implements SocialAuthListener<Integer> {
        @Override
        public void onExecute(String provider, Integer t) {
            if (t == 200 || t == 201 || t == 204)
                Toast.makeText(MainActivity.this, "Message posted on " + provider, Toast.LENGTH_LONG).show();
            else
                Toast.makeText(MainActivity.this, "Message not posted on " + provider, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onError(SocialAuthError e) {

        }
    }

}
