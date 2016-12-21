package king.com.recyler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;


/**
 * 垂直布局
 */
public class RecyclerVerticalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recycler_view);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        FruitAdapter2 adapter = new FruitAdapter2(this, FruitBean.getFruitBeans(), R.layout.fruit_item_vertical);
        recyclerView.setAdapter(adapter);
        adapter.setIRecyclerViewListener(recyclerViewListener);

    }

    private BaseRVAdapter.IRecyclerViewListener recyclerViewListener = new BaseRVAdapter.IRecyclerViewListener() {
        @Override
        public void onItemClick(View view, int position) {
            Toast.makeText(RecyclerVerticalActivity.this, position + " click",
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onItemLongClick(View view, int position) {
            Toast.makeText(RecyclerVerticalActivity.this, position + " longClick",
                    Toast.LENGTH_SHORT).show();
        }
    };
}
