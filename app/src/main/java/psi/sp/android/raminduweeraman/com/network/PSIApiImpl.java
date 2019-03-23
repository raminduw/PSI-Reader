package psi.sp.android.raminduweeraman.com.network;

import psi.sp.android.raminduweeraman.com.Models.NetworkResponseModels.ApiResponse;
import psi.sp.android.raminduweeraman.com.utils.SPConstants;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by ramindu.weeraman on 21/4/18.
 */

public class PSIApiImpl implements PSIApi {
    private PSIApi api;
    public PSIApiImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SPConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        this.api = retrofit.create(PSIApi.class);
    }

    @Override
    public Observable<ApiResponse> getPSIDetails() {
        return this.api.getPSIDetails();
    }
}
