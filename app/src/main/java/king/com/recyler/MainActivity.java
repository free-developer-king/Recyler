package king.com.recyler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button:
                enterAcitivty(RecyclerVerticalActivity.class);
                break;
            case R.id.button2:
                enterAcitivty(RecyclerhorizontalActivity.class);
                break;
            case R.id.button3:
                enterAcitivty(RecyclerGridActivity.class);
                break;
            case R.id.button4:
                enterAcitivty(RecyclerStaggeredGridActivity.class);
                break;

        }
    }

    private void enterAcitivty(Class<?> clazz){
        Intent intent = new Intent(MainActivity.this,clazz);
        startActivity(intent);
    }
}
