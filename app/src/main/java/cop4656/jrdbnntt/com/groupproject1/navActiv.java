package cop4656.jrdbnntt.com.groupproject1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

public class navActiv extends AppCompatActivity {
    TextView courseN, roomN, startT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);
        Bundle extras = getIntent().getExtras();
        String extra1 = extras.getString("class");
        String extra2 = extras.getString("start");
        String extra3 = extras.getString("room");

        courseN = (TextView) findViewById(R.id.cN);
        roomN = (TextView) findViewById(R.id.rN);
        startT = (TextView) findViewById(R.id.startTime);

        courseN.setText(extra1);
        startT.setText(extra2);
        roomN.setText(extra3);
    }

    public void navButton(View view)
    {
        //Intent intent = new Intent(this, navActiv.class); change to the navigation activity, or google maps whatever
        //startActivity(intent);

    }
}

