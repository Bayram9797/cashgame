package com.example.dashrunner;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvHighScore;
    private TextView tvTotalGames;
    private Button btnStartGame;
    private Button btnStats;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("DashRunner", MODE_PRIVATE);

        tvHighScore = findViewById(R.id.tvHighScore);
        tvTotalGames = findViewById(R.id.tvTotalGames);
        btnStartGame = findViewById(R.id.btnStartGame);
        btnStats = findViewById(R.id.btnStats);

        updateStats();

        btnStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
            }
        });

        btnStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showStatsDialog();
            }
        });
    }

    private void startGame() {
        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        startActivity(intent);
    }

    private void updateStats() {
        int highScore = sharedPreferences.getInt("highScore", 0);
        int totalGames = sharedPreferences.getInt("totalGames", 0);

        tvHighScore.setText("Рекорд: " + highScore);
        tvTotalGames.setText("Игр сыграно: " + totalGames);
    }

    private void showStatsDialog() {
        int highScore = sharedPreferences.getInt("highScore", 0);
        int totalGames = sharedPreferences.getInt("totalGames", 0);
        int totalDistance = sharedPreferences.getInt("totalDistance", 0);
        int totalCoins = sharedPreferences.getInt("totalCoins", 0);

        String message = "Статистика:\n\n" +
                "Рекорд: " + highScore + "\n" +
                "Игр сыграно: " + totalGames + "\n" +
                "Всего пройдено: " + totalDistance + " м\n" +
                "Всего монет: " + totalCoins;

        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Ваша статистика")
                .setMessage(message)
                .setPositiveButton("Ок", null)
                .show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateStats();
    }
}