package king.com.recyler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @work: ViewHolder的基类
 * @author: hg_liuzl(hg_liuzl@qq.com)
 * @date: created at 2016/12/21 11:22
 */

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
