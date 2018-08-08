package example.sunny.functioncheck;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class SuperviserRecyclerAdapter extends RecyclerView.Adapter<SuperviserRecyclerAdapter.ViewHolder>{

    private static final String TAG = "SupervRecyclerAdapt";

    private ArrayList<Zayavka> mZayavkas;
    private Callback mCallback;

    public SuperviserRecyclerAdapter (ArrayList<Zayavka> zayavkas) {
        this.mZayavkas = zayavkas;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclable_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Log.d(TAG, "onBindViewHolder: called");

        viewHolder.tvDate.setText(mZayavkas.get(i).getDate());
        viewHolder.tvNomer.setText(mZayavkas.get(i).getNomer());

        viewHolder.tvZakazchik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCallback != null) {
                    mCallback.onClickItem(i);
                }
            }
        });

        viewHolder.tvContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCallback != null) {
                    mCallback.onClickItem(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
       return mZayavkas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvDate, tvNomer, tvZakazchik, tvContact;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDate = itemView.findViewById(R.id.recycle_data);
            tvNomer = itemView.findViewById(R.id.recycle_nomer);
            tvContact = itemView.findViewById(R.id.recycle_contacts);
            tvZakazchik = itemView.findViewById(R.id.recycle_zakazchik);
            parentLayout = itemView.findViewById(R.id.recycle_relative_layout);
        }

    }

    public void setCallback(Callback mCallback) {
        this.mCallback = mCallback;
    }

    public interface Callback {
        void onClickItem(int position);
    }
}
