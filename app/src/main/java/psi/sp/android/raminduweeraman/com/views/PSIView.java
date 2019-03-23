package psi.sp.android.raminduweeraman.com.views;


import psi.sp.android.raminduweeraman.com.Models.ViewModels.PSIViewModel;

/**
 * Created by ramindu.weeraman on 21/4/18.
 */

public interface PSIView {

    void showProgressBar();

    void showErrorMsg(int stringResourceId);

    void showPSIDetails(PSIViewModel psiViewModel);
}
