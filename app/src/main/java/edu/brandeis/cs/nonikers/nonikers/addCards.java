package edu.brandeis.cs.nonikers.nonikers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;

import java.util.ArrayList;

public class addCards extends AppCompatActivity {

    Button doneBtn;
    ListView list;
    String[] chosen;
    ArrayList<String> buffer;
    MonikersAdapter adapter;
    LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cards);
        doneBtn = findViewById(R.id.done);
        list = findViewById(R.id.listview);
        inflater = getLayoutInflater();
        chosen = new String[5];
        buffer = new ArrayList<String>();
        doneBtn.setEnabled(false);
        Bundle extras = getIntent().getExtras();
        String[] chosenFrom = new String[10];
       System.arraycopy(extras.get("cardList"),0,chosenFrom,0,10);
        View.OnClickListener btnListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent result = new Intent();
                result.putExtra("chosen",chosen);
                setResult(RESULT_OK,result);
                finish();
            }
        };
        doneBtn.setOnClickListener(btnListener);
        adapter = new MonikersAdapter(chosenFrom, inflater);
    }

    public void OnCheckBoxClick(View view){
        boolean checked;
        checked = ((CheckBox) view).isChecked();
        if (checked){
            buffer.add((String)((CheckBox) view).getText());
        } else {
            buffer.remove((String)((CheckBox) view).getText());
        }
        if (buffer.size() == 5){
            chosen = (String[])buffer.toArray();
            doneBtn.setEnabled(true);
        } else {
            doneBtn.setEnabled(false);
        }
    }
}
