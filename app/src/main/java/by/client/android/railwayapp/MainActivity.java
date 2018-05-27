package by.client.android.railwayapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import by.client.android.railwayapp.ui.page.scoreboard.ScoreboardActivityFragment;

/**
 * Кланвая странциа приложения
 *
 * @author ROMAN PANTELEEV
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, ScoreboardActivityFragment.class);
        startActivity(intent);
    }
}
