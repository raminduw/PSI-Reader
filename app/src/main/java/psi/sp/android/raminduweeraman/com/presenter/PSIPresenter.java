package psi.sp.android.raminduweeraman.com.presenter;

import android.support.annotation.NonNull;

import psi.sp.android.raminduweeraman.com.Models.NetworkResponseModels.ApiResponse;
import psi.sp.android.raminduweeraman.com.Models.ViewModels.PSIViewModel;
import psi.sp.android.raminduweeraman.com.R;
import psi.sp.android.raminduweeraman.com.network.PSIApi;
import psi.sp.android.raminduweeraman.com.utils.ViewModelCreator;
import psi.sp.android.raminduweeraman.com.views.PSIView;
import rx.Observer;
import rx.Scheduler;
import rx.functions.Func1;

/**
 * Created by ramindu.weeraman on 21/4/18.
 */

public class PSIPresenter {
    @NonNull
    private PSIView psiView;
    @NonNull
    private PSIApi psiApi;
    @NonNull
    private Scheduler backgroundScheduler;
    @NonNull
    private Scheduler mainScheduler;
    @NonNull
    private ViewModelCreator viewModelCreator;

    public void setViewModelCreator(ViewModelCreator viewModelCreator) {
        this.viewModelCreator = viewModelCreator;
    }

    public PSIPresenter(PSIView psiView, PSIApi psiApi, Scheduler mainScheduler, Scheduler backgroundScheduler) {
        this.psiApi = psiApi;
        this.psiView = psiView;
        this.backgroundScheduler = backgroundScheduler;
        this.mainScheduler = mainScheduler;
    }

    public void showDetails() {
        psiView.showProgressBar();
        psiApi.getPSIDetails().
                subscribeOn(backgroundScheduler).
                map(new Func1<ApiResponse, PSIViewModel>() {
                    @Override
                    public PSIViewModel call(ApiResponse productDetailsResponse) {
                        return viewModelCreator.getPSIViewModel(productDetailsResponse);
                    }
                })
                .observeOn(mainScheduler)
                .subscribe(new Observer<PSIViewModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        psiView.showErrorMsg(R.string.error_message);
                    }

                    @Override
                    public void onNext(PSIViewModel psiViewModel) {
                        if (psiViewModel == null) {
                            psiView.showErrorMsg(R.string.error_message);
                        } else {
                            psiView.showPSIDetails(psiViewModel);
                        }
                    }
                });

    }
}
