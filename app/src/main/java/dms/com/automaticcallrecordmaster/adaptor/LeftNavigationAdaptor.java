package dms.com.automaticcallrecordmaster.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import dms.com.automaticcallrecordmaster.R;

/**
 * Created by rpeela on 12/7/15.
 */
public class LeftNavigationAdaptor extends ArrayAdapter<String> {

    private final Context context;
    protected String[] mDataset;


    // Provide a suitable constructor (depends on the kind of dataset)
    public LeftNavigationAdaptor(Context context, String[] myDataset) {
        super(context, R.layout.left_navigation_row_layout, myDataset);
        this.context = context;
        this.mDataset = myDataset;
    }

    @Override
    public int getCount() {
        return mDataset.length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.left_navigation_row_layout, parent, false);
        }
        ((TextView)convertView.findViewById(R.id.row_tv)).setText(mDataset[position]);

        return convertView;
    }
}
