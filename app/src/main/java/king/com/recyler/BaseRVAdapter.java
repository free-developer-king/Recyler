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
