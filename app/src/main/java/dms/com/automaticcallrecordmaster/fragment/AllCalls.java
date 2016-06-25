package dms.com.automaticcallrecordmaster.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import dms.com.automaticcallrecordmaster.R;
import dms.com.automaticcallrecordmaster.adaptor.CallInfoAdaptor;
import dms.com.automaticcallrecordmaster.core.CallRecordInfo;
import dms.com.automaticcallrecordmaster.db.DBHelper;

/**
 * Created by rpeela on 12/8/15.
 */
public class AllCalls extends BaseFragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;
    private View view;

    public static AllCalls newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        AllCalls fragment = new AllCalls();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.all_calls_fragment, container, false);
        RecyclerView listView = (RecyclerView) this.view.findViewById(R.id.recycler_view);
        listView.setLayoutManager(new LinearLayoutManager(getContext()));
        ArrayList<CallRecordInfo> callRecordInfo = DBHelper.getInstatnce(getContext()).getAllCallRecordingInfos(null);
        updateDataToListView(listView, callRecordInfo);
        return view;
    }

    private void updateDataToListView(RecyclerView listView, ArrayList<CallRecordInfo> callRecordInfo) {
        CallInfoAdaptor callInfoAdaptor = new CallInfoAdaptor(getContext(), callRecordInfo);
        listView.setAdapter(callInfoAdaptor);
    }


}
