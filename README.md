***PSI - Android***
========================
This repository contains a simple sample android app using the PSI API 

Libraries and Frameworks
========================
 <br />Android SDK
 <br />Java SDK 1.8
 <br />Android material design Icons/Colours
 <br />io.reactivex:rxandroid:1.2.1
 <br />com.squareup.retrofit2:retrofit:2.2.0
 <br />com.squareup.retrofit2:converter-gson:2.2.0
 <br />com.squareup.retrofit2:adapter-rxjava:2.2.0
 <br />org.mockito:mockito-core:2.7.22
 <br />junit:junit:4.12
 <br />com.google.android.gms:play-services:11.0.1


Application Configuration
=========================
 <br />compileSdkVersion 26
 <br />buildToolsVersion "26.0.2"
 <br />minSdkVersion 19
 <br />targetSdkVersion 26
 <br />applicationId "exam.sp.android.raminduweeraman.spexam"
    
Architecture
=============
This sample android application is develop based on MVP(Model-View-Presenter) architecture.

Existing Functionality
======================
<br />01.Application will show Splash screen when loading.
<br />02.Application will show PSI details on map based on the region.
<br />03.History and Settings not implemented.

Unit Test
=========
<br />Unit test covers the presentation,api and utils. 
<br />Used org.mockito:mockito-core:2.7.22 and Junit to implement test cases.

API end points
===============
<br />@GET("https://api.data.gov.sg/v1/environment/psi")

### Screens:
![screen_3](https://user-images.githubusercontent.com/5441853/54861667-08bc3380-4d68-11e9-932c-664b004ad2a7.png)
![screen_2](https://user-images.githubusercontent.com/5441853/54861668-0a85f700-4d68-11e9-854f-26a10e13ad4a.png)
![screen_1](https://user-images.githubusercontent.com/5441853/54861669-0d80e780-4d68-11e9-8f41-c7709f0f1909.png)

