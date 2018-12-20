package soexample.umeng.com.zjh.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import soexample.umeng.com.zjh.R;
import soexample.umeng.com.zjh.bean.Food;
import soexample.umeng.com.zjh.weight.MyView;

public class RightAdapter extends RecyclerView.Adapter<RightAdapter.ViewHolder> {

    private List<Food.DataBean.SpusBean> list;
    private Context context;

    public RightAdapter(List<Food.DataBean.SpusBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RightAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.rightitem, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RightAdapter.ViewHolder viewHolder, final int i) {
        Glide.with(context).load(list.get(i).getPic_url()).into(viewHolder.cimg);
        viewHolder.cname.setText(list.get(i).getName());
        viewHolder.cprice.setText(list.get(i).getSkus().get(0).getPrice()+"");

        viewHolder.myView.setCount(list.get(i).getPraise_num());
        viewHolder.myView.getNum(new MyView.CountCallBack() {  //通过自定义view获取bean类的数量
            @Override
            public void setNum(int num) {
                list.get(i).setPraise_num(num);
                notifyDataSetChanged();
                adapterCallBack.shuaxin();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView cimg;
        private TextView cname;
        private TextView cprice;
        private MyView myView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cimg = itemView.findViewById(R.id.cimg);
            cname = itemView.findViewById(R.id.cname);
            cprice = itemView.findViewById(R.id.cprice);
            myView=itemView.findViewById(R.id.my_view);
        }
    }


    //接口回调
    public interface AdapterCallBack{
        void shuaxin();
    }
    private AdapterCallBack adapterCallBack;;
    public void setCallBack(AdapterCallBack adapterCallBack){
        this.adapterCallBack = adapterCallBack;
    }



    //计算商品价钱
    public float getGoodsPrice(){
        float price = 0;
        for (int i = 0; i < list.size(); i++) {
            price+=list.get(i).getPraise_num()*Float.valueOf(list.get(i).getSkus().get(0).getPrice());
        }
        return price;
    }
}
