package com.example.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button mojGumb = findViewById(R.id.button);
        CheckBox cb = findViewById(R.id.checkBox);
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);

        mojGumb.setOnClickListener(v -> {
            if (cb.isChecked()) {
                showCenteredToast();
            } else {
                Toast.makeText(MainActivity.this, R.string.toast_message, Toast.LENGTH_SHORT).show();
            }
        });

        fab.setOnClickListener(v -> Snackbar.make(v, "Just another option for button.", Snackbar.LENGTH_LONG).show());
    }

    private void showCenteredToast() {
        Toast toast = Toast.makeText(MainActivity.this, R.string.toast_message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

        View customView = getLayoutInflater().inflate(R.layout.custom_toast, null);
        TextView textView = customView.findViewById(R.id.toast_text);
        textView.setText(R.string.toast_message);

        PopupWindow popupWindow = new PopupWindow(
                customView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                false
        );
        popupWindow.setElevation(10f);
        popupWindow.showAtLocation(findViewById(R.id.main), Gravity.CENTER, 0, 0);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            if (popupWindow.isShowing()) {
                popupWindow.dismiss();
            }
        }, 2000);
    }
}
