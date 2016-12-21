package king.com.recyler;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import java.util.List;

/**
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
