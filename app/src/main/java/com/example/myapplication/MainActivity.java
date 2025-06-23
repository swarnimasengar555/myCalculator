package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView resultTv;
    TextView workingTv;

    String formula = "";
    String workings = "";
    String tempFormula = "";

    boolean leftBracket = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        initTextView();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void initTextView() {
        workingTv = findViewById(R.id.workingsTextView);
        resultTv = findViewById(R.id.resultTextView);
    }

    private void setWorking(String givenValue) {
        workings += givenValue;
        workingTv.setText(workings);
    }

    private void CheckForPower() {
        ArrayList<Integer> indexOfPower = new ArrayList<>();
        for (int i = 0; i < workings.length(); i++) {
            if (workings.charAt(i) == '^') {
                indexOfPower.add(i);
            }
        }

        formula = workings;
        tempFormula = workings;

        for (int index : indexOfPower) {
            ChangeFormula(index);
        }

        formula = tempFormula;
    }

    private void ChangeFormula(Integer index) {
        String NumberLeft = "";
        String NumberRight = "";

        // Right side
        for (int i = index + 1; i < workings.length(); i++) {
            if (isNumeric(workings.charAt(i))) {
                NumberRight += workings.charAt(i);
            } else break;
        }

        // Left side
        for (int i = index - 1; i >= 0; i--) {
            if (isNumeric(workings.charAt(i))) {
                NumberLeft = workings.charAt(i) + NumberLeft;
            } else break;
        }

        String original = NumberLeft + "^" + NumberRight;
        String changed = "Math.pow(" + NumberLeft + "," + NumberRight + ")";
        tempFormula = tempFormula.replace(original, changed);
    }

    private boolean isNumeric(char c) {
        return (c >= '0' && c <= '9') || c == '.';
    }

    public void clearOnclick(View view) {
        workings = "";
        workingTv.setText("");
        resultTv.setText("");
        leftBracket = true;
    }

    public void bracketOnclick(View view) {
        if (leftBracket) {
            setWorking("(");
            leftBracket = false;
        } else {
            setWorking(")");
            leftBracket = true;
        }
    }

    public void powerOfOnclick(View view) { setWorking("^"); }
    public void zeroOnclick(View view) { setWorking("0"); }
    public void oneOnclick(View view) { setWorking("1"); }
    public void twoOnclick(View view) { setWorking("2"); }
    public void threeOnclick(View view) { setWorking("3"); }
    public void fourOnclick(View view) { setWorking("4"); }
    public void fiveOnclick(View view) { setWorking("5"); }
    public void sixOnclick(View view) { setWorking("6"); }
    public void sevenOnclick(View view) { setWorking("7"); }
    public void eightOnclick(View view) { setWorking("8"); }
    public void nineOnclick(View view) { setWorking("9"); }

    public void plusOnclick(View view) { setWorking("+"); }
    public void minusOnclick(View view) { setWorking("-"); }
    public void multiplyOnclick(View view) { setWorking("*"); }
    public void divideOnclick(View view) { setWorking("/"); }
}
