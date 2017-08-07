package sample.honeywell.com.wedgetest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText=(EditText) findViewById(R.id.editText);
        textView=(TextView)findViewById(R.id.textView);

        editText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                // you can call or do what you want with your EditText here
                textView.setText(textView.getText()+"\n"+getHexedString(s.toString()));
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });


        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Integer iKey = event.getKeyCode();
                String cKey=event.getCharacters();
                if(cKey==null)
                    cKey="";
                else
                    cKey=getHexedString(cKey);
                String sKeycode=KeyEvent.keyCodeToString(keyCode);
                if(iKey==0)
                    return false;
                if (event.getAction()==KeyEvent.ACTION_DOWN) {
                    //do something here
                    textView.setText(textView.getText()+"\nKeyDn: "+ iKey.toString() + ", "+sKeycode+", " + cKey);
                    Log.d("WedgeTest","KeyDn: "+ iKey.toString() + ", "+sKeycode+", " + cKey);
                    return false;
                }
                if (event.getAction()==KeyEvent.ACTION_UP) {
                    //do something here
                    textView.setText(textView.getText()+"\nKeyUp: "+ iKey.toString() + ", "+sKeycode+", " + cKey);
                    Log.d("WedgeTest","KeyUp: "+ iKey.toString() + ", "+sKeycode+", " + cKey);
                    return false;
                }
                return false;
            }
        });

    }


    public static String getHexedString(String input) {
        StringBuilder buffer = new StringBuilder(input.length());
        for (int i = 0; i < input.length(); i++) {
            if ((int) input.charAt(i) > 256) {
                buffer.append("\\u").append(Integer.toHexString((int) input.charAt(i)));
            } else {
                if((int)input.charAt(i)<0x20)
                    buffer.append("<x"+String.format("%02x",(int)input.charAt(i))+">");
                else
                    buffer.append(input.charAt(i));
            }
        }
        return buffer.toString();
    }


}
