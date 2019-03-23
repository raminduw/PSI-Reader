package psi.sp.android.raminduweeraman.com.Models.ViewModels;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by ramindu.weeraman on 21/4/18.
 */

public class MarkerViewModel {

    private String tittle;
    private String description;
    private LatLng latLng;

    public MarkerViewModel(String tittle, String description, LatLng latLng) {
        this.tittle = tittle;
        this.description = description;
        this.latLng = latLng;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

}
