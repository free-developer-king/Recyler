package king.com.recyler;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by liuking on 16/12/20.
 * 创建Adapter
 */

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.FruitViewHolder> {

    private List<FruitBean> fruitBeanList;
    private int mLayoutId;

    public FruitAdapter(List<FruitBean> list,int layoutId){
        this.fruitBeanList = list;
        this.mLayoutId = layoutId;
    }

    /**
     * 构建一个ViewHolder布局
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public FruitAdapter.FruitViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(mLayoutId,parent,false);
        final FruitViewHolder holder = new FruitViewHolder(view);   //创建一个View对象
        Log.i("","-----------------------------oncreate-----------------------");
        holder.mItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                FruitBean fb = fruitBeanList.get(position);
                Toast.makeText(parent.getContext(), "click item"+position, Toast.LENGTH_SHORT).show();
            }
        });

        holder.ivImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                FruitBean fb = fruitBeanList.get(position);
                Toast.makeText(parent.getContext(), "click item "+fb.name, Toast.LENGTH_SHORT).show();

            }
        });
        return holder;
    }


    /**
     * 绑定数据
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(FruitViewHolder holder, int position) {
        final FruitBean bean = fruitBeanList.get(position);
        holder.ivImg.setImageResource(bean.resourceId);
        holder.tvName.setText(bean.name);
        holder.tvNo.setText("编号："+bean.id);
    }


    @Override
    public int getItemCount() {
        return fruitBeanList.size();
    }


    /**
     * ViewHolder用于存储列表项中显示的控件，
     */
    static class FruitViewHolder extends RecyclerView.ViewHolder{
        View mItemView;
        ImageView ivImg;
        TextView tvNo,tvName;

        /**
         * itemView 就是控件外层布局，
         * @param itemView
         */
        public FruitViewHolder(View itemView) {
            super(itemView);
            mItemView = itemView;
            ivImg = (ImageView) itemView.findViewById(R.id.iv_img);
            tvNo = (TextView) itemView.findViewById(R.id.tv_no);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }
}
