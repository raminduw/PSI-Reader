package psi.sp.android.raminduweeraman.com.network;

import psi.sp.android.raminduweeraman.com.Models.NetworkResponseModels.ApiResponse;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by ramindu.weeraman on 21/4/18.
 */

public interface PSIApi {

    @GET("psi")
    Observable<ApiResponse> getPSIDetails();
}
