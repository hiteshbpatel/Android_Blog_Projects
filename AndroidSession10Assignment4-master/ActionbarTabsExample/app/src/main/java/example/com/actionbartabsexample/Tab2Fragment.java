package example.com.actionbartabsexample;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by HPWorld on 6/7/2017.
 */

public class Tab2Fragment extends Fragment {
    public Tab2Fragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Inflate the layout for this fragment
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab2,container,false);
    }
}
