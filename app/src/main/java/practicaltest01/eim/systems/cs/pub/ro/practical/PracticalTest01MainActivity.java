package practicaltest01.eim.systems.cs.pub.ro.practical;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PracticalTest01MainActivity extends AppCompatActivity {



    Button leftButton;
    Button rightButton;
    EditText leftText, rightText;
    Button nextActivity;

    private final static int SECONDARY_ACTIVITY_REQUEST_CODE = 1;

    private LeftClickListener leftClickListener = new LeftClickListener();
    private class LeftClickListener implements View.OnClickListener {


        @Override
        public void onClick(View view) {
            int number = Integer.parseInt(leftText.getText().toString());
            number ++;
            leftText.setText(Integer.toString(number));
        }
    }

    private NextActivityListener nextActivityListener = new NextActivityListener();
    private class NextActivityListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), PracticalTest01SecondaryActivity.class);
            int numberOfClicks = Integer.parseInt(leftText.getText().toString())  + Integer.parseInt(rightText.getText().toString());
            intent.putExtra("numberOfClicks", numberOfClicks);
            startActivityForResult(intent, SECONDARY_ACTIVITY_REQUEST_CODE);
        }
    }

    private RightClickListener rightClickListener = new RightClickListener();
    private class RightClickListener implements View.OnClickListener {


        @Override
        public void onClick(View view) {
            int number = Integer.parseInt(rightText.getText().toString());
            number ++;
            rightText.setText(Integer.toString(number));
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_practical_test01_main);

        leftButton =  (Button)findViewById(R.id.left_button);
        leftButton.setOnClickListener(leftClickListener);

        rightButton = (Button)findViewById(R.id.right_button);
        rightButton.setOnClickListener(rightClickListener);

        leftText = (EditText)findViewById(R.id.left_edit_text);
        rightText = (EditText)findViewById(R.id.right_edit_text);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("leftCount")) {
                leftText.setText(savedInstanceState.getString("leftCount"));
            } else {
                leftText.setText(String.valueOf(0));
            }

            if (savedInstanceState.containsKey("rightCount")) {
                rightText.setText(savedInstanceState.getString("rightCount"));
            } else {
                rightText.setText(String.valueOf(0));
            }
        } else {
            leftText.setText(String.valueOf(0));
            rightText.setText(String.valueOf(0));
        }

        nextActivity = (Button)findViewById(R.id.navigate_to_secondary_activity_button);
        nextActivity.setOnClickListener(nextActivityListener);

    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent intent) {
        if (requestCode == SECONDARY_ACTIVITY_REQUEST_CODE) {
            Toast.makeText(this, "Result: " + resultCode, Toast.LENGTH_LONG).show();
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString("leftCount", leftText.getText().toString());
        savedInstanceState.putString("rightCount", rightText.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        if (savedInstanceState.containsKey("leftCount")) {
            leftText.setText(savedInstanceState.getString("leftCount"));
        } else {
            leftText.setText(String.valueOf(0));
        }

        if (savedInstanceState.containsKey("rightCount")) {
            rightText.setText(savedInstanceState.getString("rightCount"));
        } else {
            rightText.setText(String.valueOf(0));
        }

    }
}
