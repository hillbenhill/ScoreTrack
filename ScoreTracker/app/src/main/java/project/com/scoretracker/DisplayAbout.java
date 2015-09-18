package project.com.scoretracker;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class DisplayAbout extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String about = "About:\nThis is a GAA Score Tracker app built for a Java Associate class project in Kerry ETB in December 2014. " +
                "It allows the user to input home and away team names and keep track of the score. " +
                "The user can then display a summary of the info entered and send this to twitter." +

                "\n\nBy:\nGlen Curran \nglencurran@eircom.net \n\nBen Hill \nhillbenhill@gmail.com \n\nMichael Finnegan \nmchl_fnngn@yahoo.ie" +

                "\n\nSpecial Thanks:\nBrian O'Shea (Lecturer Extraordinaire)\nGareth Coles (Gradle God)\nJohn Moore (Graphics Guru)";

        TextView about_tv = new TextView(this);
        about_tv.setTextSize(20);
        about_tv.setTextColor(Color.parseColor("#FAFAFC"));
        about_tv.setText(about);
        about_tv.setBackgroundResource(R.drawable.green_squares_background);

        setContentView(about_tv);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_about, menu);
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
