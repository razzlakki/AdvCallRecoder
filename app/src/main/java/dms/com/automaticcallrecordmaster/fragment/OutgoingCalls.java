package dms.com.automaticcallrecordmaster.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dms.com.automaticcallrecordmaster.R;

/**
 * Created by rpeela on 12/8/15.
 */
public class OutgoingCalls extends BaseFragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;
    private View view;
    public static IncomingCalls newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        IncomingCalls fragment = new IncomingCalls();
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
        Log.e("BlankFragmentTwo", "onCreateView");
        this.view = inflater.inflate(R.layout.all_calls_fragment, container, false);

        return view;
    }
}
