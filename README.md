


**首先查看效果图，依次是线性线性垂直布局，水平布局，网络布局，瀑布流布局**

![线性布局](http://img2.ph.126.net/lNT2Iump42_yoWpTWk1oaA==/6631735169260656455.png)
![image](http://img0.ph.126.net/gsx7cESdlpXes-OpgI-FTQ==/6631595531283937452.png)
![image](http://img1.ph.126.net/5w5mBlXV_UmBKgxrg5O3sQ==/6631848418955221018.png)
![image](http://img2.ph.126.net/Tn8BI51BlRGo7QNNCjRFHg==/6632050729094736458.png)



#打造常用RecyclerAdapter与RecyclerView.ViewHoder基类




###1. recyclerView.ViewHoder类构建
首先我们需要创建一个继承于RecyclerView.ViewHolder的类

```

public class RVViewHolder extends RecyclerView.ViewHolder {

    private Context mContext;

    public RVViewHolder(Context context, View itemView) {
        super(itemView);
        this.mContext = context;
    }

    /**
     * 通过viewId获取控件
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = itemView.findViewById(viewId);
        return (T) view;
    }

    public RVViewHolder setImageRes(int viewId, int imgRes) {
        ImageView view = getView(viewId);
        view.setImageResource(imgRes);
        return this;
    }

    public RVViewHolder setImageUrl(int viewId, String imageUrl) {
        ImageView view = getView(viewId);
        return this;
    }

    public RVViewHolder setText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    public RVViewHolder setTextColor(int viewId, int textColor) {
        TextView view = getView(viewId);
        view.setTextColor(textColor);
        return this;
    }

    public RVViewHolder setTextSize(int viewId, float size) {
        TextView view = getView(viewId);
        view.setTextSize(size);
        return this;
    }

    public RVViewHolder setTextColorRes(int viewId, int textColorRes) {
        TextView view = getView(viewId);
        view.setTextColor(mContext.getResources().getColor(textColorRes));
        return this;
    }

    public RVViewHolder setVisible(int viewId, boolean visible) {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    public RVViewHolder setVisiblePlaceHolder(int viewId, boolean visible) {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
        return this;
    }

    /**
     * 功能描述：设置各个子控件的点击事件
     * 作者： hg_liuzl@qq.com
     * 时间：2016/12/21 13:28
     * 参数：
     */
    public RVViewHolder setOnClickListener(int viewId, Object o, View.OnClickListener listener) {
        View view = getView(viewId);
        view.setTag(o);
        view.setOnClickListener(listener);
        return this;
    }
}


```

上面代码说明:

1. 创建RecyclerView.ViewHolder子类
2. 获取每一个子类控件
3. 常用的文字与图片设置方法
4. 子控件的点击事件,我这里传了3个参数，控件的Id,对象，监听，一般用到点击事件的时候，都需要绑定数据


###2. recyclerViewAdapter 基类构造
先上代码
```
package king.com.recyler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @work: 构建RecyclerView.Adapter基类
 * @author: hg_liuzl(hg_liuzl@qq.com)
 * @date: created at 2016/12/21 11:14
 */

public abstract class BaseRVAdapter<T> extends RecyclerView.Adapter<RVViewHolder> {
    protected List<T> mList;      //数据集合
    protected int resLayout;      //布局资源
    protected Context mContext;
    protected IRecyclerViewListener recyclerViewListener;

    public BaseRVAdapter(Context context, List<T> mList, int resLayout) {
        this.mContext = context;
        this.mList = mList;
        this.resLayout = resLayout;
    }


    @Override
    public RVViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(resLayout, parent, false);
        final RVViewHolder viewHolder = new RVViewHolder(mContext, view);
        return viewHolder;
    }

  

    @Override
    public void onBindViewHolder(final RVViewHolder viewHolder, int position) {
        bindAction(viewHolder, mList.get(viewHolder.getLayoutPosition()));

        /**绑定事件 一定要在这里绑定**/

        if (null != recyclerViewListener) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /**注意这里推荐用getAdapterPosition，界面刷新后立即就可以取得新的position**/
                    recyclerViewListener.onItemClick(viewHolder.itemView, viewHolder.getLayoutPosition());
                }
            });

            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    recyclerViewListener.onItemLongClick(viewHolder.itemView, viewHolder.getLayoutPosition());
                    return false;
                }
            });
        }

        bindData(viewHolder, mList.get(position));
    }

    /**
     * 绑定数据
     **/
    public abstract void bindData(RVViewHolder viewHolder, T bean);
    
      /**
     * 绑定动作
     **/
    public abstract void bindAction(RVViewHolder viewHolder, T bean);

    @Override
    public int getItemCount() {
        if (null != mList) {
            return mList.size();
        }
        return 0;
    }

    /**
     * 设置点击事件
     *
     * @param mIRecyclerViewListener
     */
    public void setIRecyclerViewListener(IRecyclerViewListener mIRecyclerViewListener) {
        this.recyclerViewListener = mIRecyclerViewListener;
    }

    public interface IRecyclerViewListener {

        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }
}


```

代码解读:
1. 首先创建RecyclerAdapter的子类的抽象类,把一些需要实现的方法给子类去实现
2. 添加构造方法，其中构造函数包括Context,list,布局资源 这样我们可以根据自己的需要传入相应数据类型，以及布局
3. 在onBindViewHolder中添加数据以及绑定事件，另外事件的回调函数一定要判断不为空才可以调用回调方法

给Item 添加回调，使item可以点击以及长按

###3. recycler的getAdapterPostion与getLayoutPosition()坑点说明
1. 根据官方说明getAdapterPosition是在变化的时候就能获取到position,而getLayoutPosition是在布局完成后可以获取到position，一般我们需要获取某个position的时候，肯定是在布局完成后获取，所以建议使用getLayoutPosition来获取position
2. 另外也不要在onCreateView中获取某个实体类，很容易造成数组越界,因为这个时候getAdapterPosition为-1 在创建之前布局还没有完成,

###在子类中继承万能RecyclerViewAdapter

```

package king.com.recyler;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import java.util.List;

/**
 * 
 * @work: ${功能介绍}
 * @author: hg_liuzl(hg_liuzl@qq.com)
 * @date: created at 2016/12/21 12:48
 */

public class FruitAdapter2 extends BaseRVAdapter implements View.OnClickListener {

    public FruitAdapter2(Context context, List mList, int resLayout) {
        super(context, mList, resLayout);
    }

    @Override
    public void bindAction(RVViewHolder viewHolder, Object o) {
        viewHolder.setOnClickListener(R.id.tv_name, o, this);
    }

    @Override
    public void bindData(RVViewHolder viewHolder, Object o) {
        final FruitBean bean = (FruitBean) o;
        viewHolder.setText(R.id.tv_name, bean.name);
        viewHolder.setText(R.id.tv_no, "编号:" + bean.id);
        viewHolder.setImageRes(R.id.iv_img, bean.resourceId);
    }


    @Override
    public void onClick(View view) {
        final FruitBean bean = (FruitBean) view.getTag();
        switch (view.getId()) {
            case R.id.tv_name:
                Toast.makeText(mContext, "你点击了Item中的" + bean.id + "----name是" + bean.name, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}


```

代码解读：

1. 首先基础FruitAdapter继承于BaseRVAdapter,并实现其2个抽象方法
2. 在bindAction方法中绑定子控件的点击事件,在bindData方法中绑定数据
3. 另外实现OnClick接口，来完成点击事件

最后使用我们创建的万能基类实际调用:

```
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


```

1. 可以看到适配器用的是FruitAdapter2 
2. 给Adaapter绑定回调方法，使item可以点击

最后再看看效果
*![](http://img2.ph.126.net/6-LhmDx6qGAbgJae84Jxtw==/6631883603327322356.gif)*

[csdn地址](http://blog.csdn.net/u014702332/article/details/53785136)
