package psi.sp.android.raminduweeraman.com;

import android.content.Context;

import com.google.android.gms.maps.model.LatLng;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import exam.sp.android.raminduweeraman.spexam.R;
import psi.sp.android.raminduweeraman.com.Models.NetworkResponseModels.ApiResponse;
import psi.sp.android.raminduweeraman.com.Models.NetworkResponseModels.PsiTwentyFourHourly;
import psi.sp.android.raminduweeraman.com.Models.ViewModels.MarkerViewModel;
import psi.sp.android.raminduweeraman.com.Models.ViewModels.PSIViewModel;
import psi.sp.android.raminduweeraman.com.network.PSIApiImpl;
import psi.sp.android.raminduweeraman.com.presenter.PSIPresenter;
import psi.sp.android.raminduweeraman.com.utils.SPConstants;
import psi.sp.android.raminduweeraman.com.utils.ViewModelCreator;
import psi.sp.android.raminduweeraman.com.views.PSIView;
import rx.Observable;
import rx.schedulers.Schedulers;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ramindu.weeraman on 21/4/18.
 */
@RunWith(MockitoJUnitRunner.class)
public class PSIPresenterTest {

    @Mock
    Context context;
    @Mock
    PSIApiImpl psiApi;
    @Mock
    PSIView psiView;
    @Mock
    ViewModelCreator viewModelCreator;

    PSIPresenter psiPresenter;

    @Before
    public void before() throws Exception {
        psiPresenter = new PSIPresenter(psiView, psiApi, Schedulers.immediate(), Schedulers.immediate());
        psiPresenter.setViewModelCreator(viewModelCreator);
    }

    @Test
    public void testGetErrorPSIDetails() {
        Exception exception = new Exception();
        when(psiApi.getPSIDetails())
                .thenReturn(Observable.<ApiResponse>error(exception));
        psiPresenter.showDetails();
        InOrder inOrder = Mockito.inOrder(psiView);
        inOrder.verify(psiView, times(1)).showErrorMsg(R.string.error_message);
    }


    @Test
    public void testCreateNullViewModel() throws Exception {
        ViewModelCreator viewModelCreator = new ViewModelCreator(context);
        PSIViewModel viewModel = viewModelCreator.getPSIViewModel(null);
        assertEquals(null, viewModel);
    }

    @Test
    public void testCreateViewModelWithZoomLevel() throws Exception {
        ViewModelCreator viewModelCreator = new ViewModelCreator(context);
        PSIViewModel viewModel = viewModelCreator.getPSIViewModel(new ApiResponse());
        assertEquals(SPConstants.ZOOM_LEVEL,viewModel.getZoomLevel());
    }

    @Test
    public void testCreateViewModelWithCenterPoint() throws Exception {
        ViewModelCreator viewModelCreator = new ViewModelCreator(context);
        PSIViewModel viewModel = viewModelCreator.getPSIViewModel(new ApiResponse());
        assertEquals(SPConstants.CENTER_LAT,viewModel.getCenterPoint().latitude);
        assertEquals(SPConstants.CENTER_LON,viewModel.getCenterPoint().longitude);
    }

    @Test
    public void testGetLocationNull() throws Exception {
        ViewModelCreator viewModelCreator = new ViewModelCreator(context);
        LatLng latLng= viewModelCreator.getLocation(0,0);
        assertEquals(null,latLng);
    }

    @Test
    public void testGetLocationNotNull() throws Exception {
        ViewModelCreator viewModelCreator = new ViewModelCreator(context);
        LatLng latLng= viewModelCreator.getLocation(1.23,103.45);
        assertNotNull(latLng);
    }

    @Test
    public void testGetPSIValueNullPsiObject() throws Exception {
        ViewModelCreator viewModelCreator = new ViewModelCreator(context);
        int psiValue = viewModelCreator.getPSIValue(null,"west");
        assertEquals(0,psiValue);
    }

    @Test
    public void testGetPSIValueNullFiledString() throws Exception {
        ViewModelCreator viewModelCreator = new ViewModelCreator(context);
        int psiValue = viewModelCreator.getPSIValue(new PsiTwentyFourHourly(),null);
        assertEquals(0,psiValue);
    }


    @Test
    public void testGetPSIValue() throws Exception {
        ViewModelCreator viewModelCreator = new ViewModelCreator(context);
        PsiTwentyFourHourly psiTwentyFourHourly  = new PsiTwentyFourHourly();
        psiTwentyFourHourly.setWest(30);
        int psiValue = viewModelCreator.getPSIValue(psiTwentyFourHourly,"west");
        assertEquals(30,psiValue);
    }

    @Test
    public void testGetMarkerViewModelWithNullParams() throws Exception {
        ViewModelCreator viewModelCreator = new ViewModelCreator(context);
        MarkerViewModel markerViewModel = viewModelCreator.getMarkerViewModel("name",10,null);
        assertEquals(null,markerViewModel);
    }


    @Test
    public void testGetMarkerViewModel() throws Exception {
        ViewModelCreator viewModelCreator = new ViewModelCreator(context);
        MarkerViewModel markerViewModel = viewModelCreator.getMarkerViewModel("west",10,viewModelCreator.getLocation(1.23,102.3));
        assertEquals("west",markerViewModel.getTittle());
    }

    @Test
    public void testGetValidPSIDetailsNoError() {
        ApiResponse apiResponse = new ApiResponse();
        when(viewModelCreator.getPSIViewModel(apiResponse))
                .thenReturn(new PSIViewModel());
        when(psiApi.getPSIDetails())
                .thenReturn(Observable.just(apiResponse));
        psiPresenter.showDetails();
        verify(psiView, Mockito.never()).showErrorMsg(R.string.error_message);
    }

    @Test
    public void testGetValidPSIDetailsSuccess() {
        ApiResponse apiResponse = new ApiResponse();
        PSIViewModel psiViewModel = new PSIViewModel();
        when(viewModelCreator.getPSIViewModel(apiResponse))
                .thenReturn(psiViewModel);
        when(psiApi.getPSIDetails())
                .thenReturn(Observable.just(apiResponse));
        psiPresenter.showDetails();
        InOrder inOrder = Mockito.inOrder(psiView);
        inOrder.verify(psiView, times(1)).showPSIDetails(psiViewModel);
    }

}
