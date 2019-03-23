package psi.sp.android.raminduweeraman.com.Models.NetworkResponseModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ramindu.weeraman on 21/4/18.
 */

public class ApiResponse {

    @SerializedName("region_metadata")
    @Expose
    private List<RegionMetadatum> regionMetadata = null;
    @SerializedName("items")
    @Expose
    private List<Item> items = null;
    @SerializedName("api_info")
    @Expose
    private ApiInfo apiInfo;

    public List<RegionMetadatum> getRegionMetadata() {
        return regionMetadata;
    }

    public void setRegionMetadata(List<RegionMetadatum> regionMetadata) {
        this.regionMetadata = regionMetadata;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public ApiInfo getApiInfo() {
        return apiInfo;
    }

    public void setApiInfo(ApiInfo apiInfo) {
        this.apiInfo = apiInfo;
    }
}
