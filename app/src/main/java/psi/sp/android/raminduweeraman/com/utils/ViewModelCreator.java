package psi.sp.android.raminduweeraman.com.utils;


import android.content.Context;

import com.google.android.gms.maps.model.LatLng;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import psi.sp.android.raminduweeraman.com.Models.NetworkResponseModels.ApiResponse;
import psi.sp.android.raminduweeraman.com.Models.NetworkResponseModels.PsiTwentyFourHourly;
import psi.sp.android.raminduweeraman.com.Models.NetworkResponseModels.RegionMetadatum;
import psi.sp.android.raminduweeraman.com.Models.ViewModels.MarkerViewModel;
import psi.sp.android.raminduweeraman.com.Models.ViewModels.PSIViewModel;
import psi.sp.android.raminduweeraman.com.R;

/**
 * Created by ramindu.weeraman on 21/4/18.
 */

public class ViewModelCreator {

    private Context context;

    public ViewModelCreator(Context context) {
        this.context = context;
    }

    public PSIViewModel getPSIViewModel(ApiResponse apiResponse) {
        PSIViewModel psiViewModel = null;
        List<MarkerViewModel> markerViewModels;
        if (apiResponse == null) {
            return null;
        }

        psiViewModel = new PSIViewModel();
        //set zoom level
        psiViewModel.setZoomLevel(SPConstants.ZOOM_LEVEL);
        //set center point
        psiViewModel.setCenterPoint(new LatLng(SPConstants.CENTER_LAT, SPConstants.CENTER_LON));

        List<RegionMetadatum> regionMetadataList = apiResponse.getRegionMetadata();
        if (regionMetadataList != null && apiResponse.getItems() != null) {
            markerViewModels = new ArrayList<>();
            PsiTwentyFourHourly psiTwentyFourHourly = apiResponse.getItems().get(0).getReadings().getPsiTwentyFourHourly();
            MarkerViewModel markerViewModel = null;
            for (RegionMetadatum regionMetadatum : regionMetadataList) {
                int psiValue = getPSIValue(psiTwentyFourHourly, regionMetadatum.getName());
                LatLng latLng = getLocation(regionMetadatum.getLabelLocation().getLatitude(), regionMetadatum.getLabelLocation().getLongitude());
                markerViewModel =  getMarkerViewModel(regionMetadatum.getName(),psiValue,latLng);
                if (markerViewModel != null) {
                    markerViewModels.add(markerViewModel);
                }
            }
            psiViewModel.setMarkerViewModels(markerViewModels);
        }
        return psiViewModel;
    }


    public MarkerViewModel getMarkerViewModel(String name,int psiValue ,LatLng latLng){
        MarkerViewModel markerViewModel = null;
        if (latLng != null && name!=null) {
            String description = context.getString(R.string.psi_value, String.valueOf(psiValue));
            markerViewModel = new MarkerViewModel(name, description, latLng);
        }
        return markerViewModel;
    }


    public LatLng getLocation(double lat, double lon) {
        LatLng latLng = null;
        //if national return null
        if (lat != 0 && lon != 0) {
            latLng = new LatLng(lat, lon);
        }
        return latLng;
    }

    public int getPSIValue(PsiTwentyFourHourly psiTwentyFourHourly, String fieldString) {
        int psiValue = 0;
        if (psiTwentyFourHourly == null || fieldString == null) {
            return psiValue;
        }
        try {
            Field field = psiTwentyFourHourly.getClass().getDeclaredField(fieldString);
            field.setAccessible(true);
            Object value = field.get(psiTwentyFourHourly);
            psiValue = (Integer) value;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return psiValue;
    }
}
