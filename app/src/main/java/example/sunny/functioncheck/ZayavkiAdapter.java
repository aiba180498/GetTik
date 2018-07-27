package example.sunny.functioncheck;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ZayavkiAdapter extends ArrayAdapter<Zayavka> {

    private Context mContext;
    private int mResource;

    static class ViewHolder{
        TextView date;
        TextView nomer;
    }

    public ZayavkiAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Zayavka> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String date = getItem(position).getDate();
        String nomer = getItem(position).getNomer();

        final View view;
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            holder = new ViewHolder();
            holder.date = (TextView) convertView.findViewById(R.id.data_superviser);
            holder.nomer = (TextView) convertView.findViewById(R.id.nomer_superviser);

            view = convertView;

            convertView.setTag(holder);
        } else {

            holder = (ViewHolder)  convertView.getTag();

            view = convertView;
        }



        holder.date.setText(date);
        holder.nomer.setText(nomer);

        return convertView;
    }
}
