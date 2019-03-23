package psi.sp.android.raminduweeraman.com.Models.ViewModels;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by ramindu.weeraman on 21/4/18.
 */

public class PSIViewModel {

    private LatLng centerPoint;
    private List<MarkerViewModel> markerViewModels;
    private int zoomLevel;

    public LatLng getCenterPoint() {
        return centerPoint;
    }

    public void setCenterPoint(LatLng centerPoint) {
        this.centerPoint = centerPoint;
    }

    public int getZoomLevel() {
        return zoomLevel;
    }

    public void setZoomLevel(int zoomLevel) {
        this.zoomLevel = zoomLevel;
    }

    public List<MarkerViewModel> getMarkerViewModels() {
        return markerViewModels;
    }

    public void setMarkerViewModels(List<MarkerViewModel> markerViewModels) {
        this.markerViewModels = markerViewModels;
    }

}
