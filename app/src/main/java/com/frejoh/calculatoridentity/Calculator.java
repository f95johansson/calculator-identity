package com.frejoh.calculatoridentity;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.internal.view.menu.MenuPopupHelper;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupMenu;
import android.widget.ScrollView;
import android.widget.TextView;

public class Calculator extends AppCompatActivity {

    private PopupMenu popup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        Window window = this.getWindow();

        /** Set statusbar to other color than primary */
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) { // to support status bar color
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorStatusBar));
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView historyText = (TextView) findViewById(R.id.history_text);
                CharSequence currentText = historyText.getText();
                if (currentText.length() > 0) {
                    historyText.setText(currentText.subSequence(0, currentText.length() - 1));
                }
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
            }
        });


        View menuView = findViewById(R.id.menu_button);
        /*
        // Setup Popup
        ListPopupWindow popup = new ListPopupWindow(this);
        // Enable drag to open, for those who support it
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            menuView.setOnTouchListener(popup.createDragToOpenListener(menuView));
        }
        */


        popup = new PopupMenu(this, menuView);

        // Enable drag to open, for those who support it
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            menuView.setOnTouchListener(popup.getDragToOpenListener());
        }
        popup.inflate(R.menu.menu_calculator);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calculator, menu);
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

    public void showPopupMenu(View view) {
        popup.show();
    }

    public void numpadPress(View view) {
        TextView historyText = (TextView) findViewById(R.id.history_text);
        ScrollView historyScroll = (ScrollView) findViewById(R.id.history_scroll);

        historyText.append(((TextView) view).getText());
        if (((TextView) view).getText().equals("=")) {
            historyText.append("\n");
        }
        // scroll to bottom, with animation
        historyScroll.fullScroll(ScrollView.FOCUS_DOWN);
    }
}
