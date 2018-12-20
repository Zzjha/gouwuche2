package soexample.umeng.com.zjh.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import soexample.umeng.com.zjh.R;
import soexample.umeng.com.zjh.bean.Food;

public class LeftAdapter extends RecyclerView.Adapter<LeftAdapter.ViewHolder> {

    private List<Food.DataBean> list;
    private Context context;

    public LeftAdapter(List<Food.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public LeftAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.leftitem, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull LeftAdapter.ViewHolder viewHolder, final int i) {
        Food.DataBean dataBean = list.get(i);
        viewHolder.name.setText(dataBean.getName());


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(callback!=null){
                    callback.setOnItemClick(v,i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
        }
    }



    //点击事件接口回调
    public interface Callback{
        void setOnItemClick(View view,int position);
    }
    private Callback callback;
    public void setOnClick(Callback callback){
        this.callback = callback;
    }

}
