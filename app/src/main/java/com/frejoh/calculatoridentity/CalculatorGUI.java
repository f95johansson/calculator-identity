package com.frejoh.calculatoridentity;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.PopupMenu;
import android.widget.ScrollView;
import android.widget.TextView;

import com.udojava.evalex.Expression;

import java.lang.reflect.Field;
import java.util.HashMap;


public class CalculatorGUI extends AppCompatActivity {

    private PopupMenu popup;
    private ExpressionParser parser;
    private Calculator calculator;

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

        /** Setup Calculator */
        parser = new ExpressionParser(getAllSymbols());
        calculator = new Calculator(parser);


        /** Setup widgets */
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView historyText = (TextView) findViewById(R.id.history_text);
                CharSequence currentText = historyText.getText();
                if (currentText.length() > 0) {
                    historyText.setText(parser.deleteLastSymbol(currentText.toString()));
                }
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
            }
        });
        fab.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                TextView historyText = (TextView) findViewById(R.id.history_text);
                historyText.setText("");
                return true;
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

    public void equalsPress(View view) {
        TextView historyText = (TextView) findViewById(R.id.history_text);
        ScrollView historyScroll = (ScrollView) findViewById(R.id.history_scroll);

        String allText = historyText.getText().toString();
        int startOfLastLine = allText.lastIndexOf("\n");
        String expression;
        if (startOfLastLine > 0) {
            expression = allText.substring(startOfLastLine);
        } else {
            expression = allText;
        }
        try {
            Calculator.Answer answer = calculator.compute(expression);
            historyText.append("\n\t\t" + answer.toString() + "\n");
        } catch (Expression.ExpressionException ee) {
            historyText.append("\n\t\tError: " + ee.getMessage() +"\n");
        }

        // scroll to bottom, with animation
        historyScroll.fullScroll(ScrollView.FOCUS_DOWN);
    }

    public void numpadPress(View view) {
        TextView historyText = (TextView) findViewById(R.id.history_text);
        ScrollView historyScroll = (ScrollView) findViewById(R.id.history_scroll);

        String button_symbol = ((TextView) view).getText().toString();
        String display_symbol = parser.convert(button_symbol);

        historyText.append(display_symbol);

        // scroll to bottom, with animation
        historyScroll.fullScroll(ScrollView.FOCUS_DOWN);
    }

    /**
     * Using strings.xml and the enum DisplaySymbol, make a table mapping button symbols
     * to the symbols used in text
     *
     * @return HashMap mapping button symbols to display symbols
     */
    private HashMap<String, String> getAllSymbols() {
        HashMap<String, String> symbols = new HashMap<>();
        int sLength = "symbol_".length();

        // Get all the symbol values from res/values/strings.xml
        Field[] symbols_field = R.string.class.getFields();
        for (Field symbol : symbols_field) {
            String symbol_name = symbol.getName();
            if (symbol_name.startsWith("symbol_")) {
                try {
                    //int symbol_id = getResources().getIdentifier(symbol_name, "strings", getPackageName());
                    int symbol_id = symbol.getInt(null);
                    String symbol_value = getResources().getString(symbol_id);
                    symbols.put(symbol_value,
                            DisplaySymbol.valueOf(
                                    symbol_name.substring(sLength)).getDisplayValue());
                } catch (IllegalAccessException iae) {
                    // ignore TODO empty catch
                }

            }
        }
        return symbols;
    }
}
