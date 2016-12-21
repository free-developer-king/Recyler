package king.com.recyler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by liuking on 16/12/20.
 */

public class FruitBean{

    public int id;
    public String name;
    public int resourceId;


    /**
     * 创建数据
     * @return
     */
    public static List<FruitBean> getFruitBeans(){


        List<FruitBean> fruitBeanList = new ArrayList<>();
        for(int i = 0;i<100;i++){

            FruitBean bean = new FruitBean();
            bean.id = i;
            switch (i%10){
                case 0:
                    bean.name = "apple";
                    bean.resourceId = R.drawable.apple_pic;
                    break;
                case 1:
                    bean.name = "banana";
                    bean.resourceId = R.drawable.banana_pic;
                    break;
                case 2:
                    bean.name = "cherry";
                    bean.resourceId = R.drawable.cherry_pic;
                    break;
                case 3:
                    bean.name = "grape";
                    bean.resourceId = R.drawable.grape_pic;
                    break;
                case 4:
                    bean.name = "mango";
                    bean.resourceId = R.drawable.mango_pic;
                    break;
                case 5:
                    bean.name = "orange";
                    bean.resourceId = R.drawable.orange_pic;
                    break;
                case 6:
                    bean.name = "pear";
                    bean.resourceId = R.drawable.pear_pic;
                    break;
                case 7:
                    bean.name = "pineapple";
                    bean.resourceId = R.drawable.pineapple_pic;
                    break;
                case 8:
                    bean.name = "strawberry";
                    bean.resourceId = R.drawable.strawberry_pic;
                    break;
                case 9:
                    bean.name = "watermelon";
                    bean.resourceId = R.drawable.watermelon_pic;
                    break;
              default:
                  bean.name = "apple";
                  bean.resourceId = R.drawable.apple_pic;
                  break;
            }
            fruitBeanList.add(bean);

        }

        return fruitBeanList;
    }


    /**
     * 创建数据
     * @return
     */
    public static List<FruitBean> getStaggeredFruitBeans(){


        List<FruitBean> fruitBeanList = new ArrayList<>();
        for(int i = 0;i<100;i++){

            FruitBean bean = new FruitBean();
            bean.id = i;
            switch (i%10){
                case 0:
                    bean.name = "apple";
                    bean.resourceId = R.drawable.apple_pic;
                    break;
                case 1:
                    bean.name = "banana";
                    bean.resourceId = R.drawable.banana_pic;
                    break;
                case 2:
                    bean.name = "cherry";
                    bean.resourceId = R.drawable.cherry_pic;
                    break;
                case 3:
                    bean.name = "grape";
                    bean.resourceId = R.drawable.grape_pic;
                    break;
                case 4:
                    bean.name = "mango";
                    bean.resourceId = R.drawable.mango_pic;
                    break;
                case 5:
                    bean.name = "orange";
                    bean.resourceId = R.drawable.orange_pic;
                    break;
                case 6:
                    bean.name = "pear";
                    bean.resourceId = R.drawable.pear_pic;
                    break;
                case 7:
                    bean.name = "pineapple";
                    bean.resourceId = R.drawable.pineapple_pic;
                    break;
                case 8:
                    bean.name = "strawberry";
                    bean.resourceId = R.drawable.strawberry_pic;
                    break;
                case 9:
                    bean.name = "watermelon";
                    bean.resourceId = R.drawable.watermelon_pic;
                    break;
                default:
                    bean.name = "apple";
                    bean.resourceId = R.drawable.apple_pic;
                    break;
            }

            Random random = new Random();
            int length = random.nextInt(10)+1;

            StringBuilder sb = new StringBuilder();
            for (int j=0;j<length;j++){
                sb.append(bean.name);
            }
            bean.name = sb.toString();
            fruitBeanList.add(bean);

        }

        return fruitBeanList;
    }
}
