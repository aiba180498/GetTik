package example.sunny.functioncheck;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SuperviserRecyclerAdapter extends RecyclerView.Adapter<SuperviserRecyclerAdapter.ViewHolder>{

    private static final String TAG = "SupervRecyclerAdapt";

    private ArrayList<String> mDates = new ArrayList<>();
    private ArrayList<String> mNomera = new ArrayList<>();
    private Context mContext;

    public SuperviserRecyclerAdapter (Context mContext, ArrayList<String> mDates, ArrayList<String> mNomera) {
        this.mDates = mDates;
        this.mNomera = mNomera;
        this.mContext = mContext;
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

        viewHolder.tvDate.setText(mDates.get(i));
        viewHolder.tvNomer.setText(mNomera.get(i));

        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, mDates.get(i), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(mContext, SuperviserItemActivity.class);
                intent.putExtra("nom", mNomera.get(i));
                intent.putExtra("dat", mDates.get(i));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNomera.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvDate, tvNomer;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDate = itemView.findViewById(R.id.recycle_data);
            tvNomer = itemView.findViewById(R.id.recycle_nomer);
            parentLayout = itemView.findViewById(R.id.recycle_relative_layout);
        }

    }
}
