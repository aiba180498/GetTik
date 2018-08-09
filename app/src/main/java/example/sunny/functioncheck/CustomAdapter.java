package example.sunny.functioncheck;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;



import java.util.List;

import example.sunny.functioncheck.Models.DummyModel;



    public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

        private List<DummyModel> dataList;

        public CustomAdapter(List<DummyModel> dataList){
            this.dataList = dataList;
        }

        public class CustomViewHolder extends RecyclerView.ViewHolder {

            TextView txtTag;
            TextView txtText;

            CustomViewHolder(View itemView) {
                super(itemView);

                txtTag = (TextView)itemView.findViewById(R.id.textView5);
                txtText = (TextView)itemView.findViewById(R.id.textView6);

            }
        }

        @Override
        public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cusom_row, parent, false);
            CustomViewHolder customViewHolder = new CustomViewHolder(view);
            return customViewHolder;
        }

        @Override
        public void onBindViewHolder(CustomViewHolder holder, final int position) {
//            final DummyModel message = dataList.get(position);
//            holder.txtTag.setText(message.getTitle());
//            holder.txtText.setText(message.getId());

            holder.txtTag.setText(dataList.get(position).getId());
            holder.txtText.setText(dataList.get(position).getTitle());

        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }
    }

