package Model;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ensim.snowtam.R;

public class PageFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;
    public String dec;
    public String raw;

    public static PageFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PageFragment fragment = new PageFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);


        SnowtamRecuperation recup = SnowtamRecuperation.getInstance();
        SnowtamDecode decode = new SnowtamDecode();
        Log.d("GetSet PageFragment :", "Index : " + recup.getIndex() + " et listAirport" + recup.getListAirport());
        if (recup.getListAirport() != null) {
            dec = decode.DecodeSnowtam((recup.getListAirport()).get(recup.getIndex()).getSnowtam(), (recup.getListAirport()).get(recup.getIndex()).getName());
            raw = (recup.getListAirport()).get(recup.getIndex()).getSnowtam();
        } else {
            dec = "Decodage Null";
            raw = "Raw Null";
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page, container, false);
        TextView textView = (TextView) view;

        if(mPage == 1)
            textView.setText(raw);
        if(mPage == 2)
            textView.setText(dec);
        return view;
    }
}