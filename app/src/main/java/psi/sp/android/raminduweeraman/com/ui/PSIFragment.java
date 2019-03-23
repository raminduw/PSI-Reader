package psi.sp.android.raminduweeraman.com.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.MarkerOptions;

import psi.sp.android.raminduweeraman.com.Models.ViewModels.MarkerViewModel;
import psi.sp.android.raminduweeraman.com.Models.ViewModels.PSIViewModel;
import psi.sp.android.raminduweeraman.com.R;
import psi.sp.android.raminduweeraman.com.network.PSIApi;
import psi.sp.android.raminduweeraman.com.network.PSIApiImpl;
import psi.sp.android.raminduweeraman.com.presenter.PSIPresenter;
import psi.sp.android.raminduweeraman.com.utils.ViewModelCreator;
import psi.sp.android.raminduweeraman.com.views.PSIView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ramindu.weeraman on 20/4/18.
 */

public class PSIFragment extends Fragment implements OnMapReadyCallback, PSIView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private SupportMapFragment mapFragment;
    private PSIPresenter psiPresenter;
    private Context context;
    private GoogleMap googleMap;
    private ProgressDialog dialog;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PSIFragment newInstance(String param1, String param2) {
        PSIFragment fragment = new PSIFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    public PSIFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        return view;

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        //Call presenter to retrieve dara from API
        PSIApi psiApi = new PSIApiImpl();
        psiPresenter = new PSIPresenter(this, psiApi, AndroidSchedulers.mainThread(), Schedulers.io());
        psiPresenter.setViewModelCreator(new ViewModelCreator(context));
        psiPresenter.showDetails();
    }

    @Override
    public void showProgressBar() {
        dialog = ProgressDialog.show(context, "",
                context.getString(R.string.loading), true);

    }

    @Override
    public void showErrorMsg(int stringResourceId) {
        hideProgress();
        Toast.makeText(context, stringResourceId, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showPSIDetails(PSIViewModel psiViewModel) {
        hideProgress();
        //show view models in map
        for (MarkerViewModel markerViewModel : psiViewModel.getMarkerViewModels()) {
            googleMap.addMarker(new MarkerOptions()
                    .position(markerViewModel.getLatLng())
                    .title(markerViewModel.getTittle())
                    .snippet(markerViewModel.getDescription()));
        }
        //set center point and zoom level
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(psiViewModel.getCenterPoint(), psiViewModel.getZoomLevel()));
    }

    private void hideProgress() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
