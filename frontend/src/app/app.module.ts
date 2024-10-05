import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppSideNavbarComponent } from './global/app-side-navbar/app-side-navbar.component';
import {MatSidenavModule} from '@angular/material/sidenav';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {FlexLayoutModule} from '@angular/flex-layout';
import { MaterialModule } from './material/material.module';
import { AppToolbarListComponent } from './global/app-side-navbar/components/app-toolbar-list/app-toolbar-list.component';
import { UserComponent } from './layouts/user/user.component';
import { UserRoutingModule } from './layouts/user/user-routing.module';
import { MAT_FORM_FIELD_DEFAULT_OPTIONS, MatFormFieldDefaultOptions } from '@angular/material/form-field';
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule} from '@angular/common/http';
import {TranslateHttpLoader} from '@ngx-translate/http-loader';
import {TranslateLoader, TranslateModule} from '@ngx-translate/core';
import { AuthComponent } from './layouts/auth/auth.component';
import { AuthRoutingModule } from './layouts/auth/auth-routing.module';
import { AdminRoutingModule } from './layouts/admin/admin-routing.module';
import { AdminComponent } from './layouts/admin/admin.component';
import { AdminToolbarListComponent } from './layouts/admin/global/admin-toolbar-list/admin-toolbar-list.component';
import { AdminSideNavListComponent } from './layouts/admin/global/admin-side-nav-list/admin-side-nav-list.component';
import { MyCommunityComponent } from './layouts/admin/components/my-community/my-community.component';
import { NgApexchartsModule } from "ng-apexcharts";
import { CourseDialogFormComponent  } from './layouts/admin/components/course-dialog-form/course-dialog-form.component';
import { RatingStarComponent } from './global/rating-star/rating-star.component';
import { UsersComponent } from './layouts/admin/components/users/users.component';
import { ValidationMsgComponent } from './global/validation-msg/validation-msg.component';
import { AdminDashboardComponent } from './layouts/admin/components/dashboard/admin-dashboard.component';
import { FacultiesComponent } from './layouts/admin/components/faculties/faculties.component';
import { FacultyCreateDialogComponent } from './layouts/admin/components/faculty-create-dialog/faculty-create-dialog.component';
import { loaderInterceptor } from './interceptor/loader.interceptor';
import { TokenInterceptor } from './interceptor/token.interceptor';
import { LoadingCircularComponent } from './global/loading-circular/loading-circular.component';
import { AuthInterceptor } from './interceptor/auth.interceptor';
import { UniveristiesComponent } from './layouts/admin/components/univeristies/univeristies.component';
import { UniveristyFormDialogComponent } from './layouts/admin/components/univeristy-form-dialog/univeristy-form-dialog.component';
import { AcademicSpecializationsComponent } from './layouts/admin/components/academic-specializations/academic-specializations.component';
import { AcademicSpecializationFormDialogComponent } from './layouts/admin/components/academic-specialization-form-dialog/academic-specialization-form-dialog.component';
import { LangingPageComponent } from './layouts/public/components/langing-page/langing-page.component';
import { CoursesComponent } from './layouts/admin/components/courses/courses.component';
import { UserDashboardComponent } from './layouts/user/components/user-dashboard/user-dashboard.component';
import { ProfileEditComponent } from './layouts/user/components/profile-edit/profile-edit.component';
import { UserSideNavListComponent } from './layouts/user/components/user-side-nav-list/user-side-nav-list.component';
import { CourseCardComponent } from './global/course-card/course-card.component';
import { FooterComponent } from './layouts/public/components/footer/footer.component';
import { Page404Component } from './global/page404/page404.component';
import { MyCoursesComponent } from './layouts/user/components/my-courses/my-courses.component';
import { MyCourseCardComponent } from './global/my-course-card/my-course-card.component';
import { BuyCourseComponent } from './layouts/user/components/buy-course/buy-course.component';
import { LectureCardComponent } from './global/lecture-card/lecture-card.component';
import { toShortText } from './pip/toShortText';
import { LectureDialogFormComponent } from './layouts/admin/components/lecture-dialog-form/lecture-dialog-form.component';
import { secondsToLength } from './pip/secondsToLength';
import { InputStarRatingComponent } from './global/input-star-rating/input-star-rating.component';
import { ReviewDialogFormComponent } from './layouts/user/components/review-dialog-form/review-dialog-form.component';
import { toCeil } from './pip/toCeil';
import { DateConverter } from './pip/dateConverter';
import { DatePipe } from '@angular/common';
import { PaymentComponent } from './global/payment/payment.component';
import { CourseOverviewComponent } from './layouts/user/components/course-overview/course-overview.component';
import { UserToolBarComponent } from './layouts/user/components/user-tool-bar/user-tool-bar.component';
import { NavBarComponent } from './layouts/public/components/nav-bar/nav-bar.component';
import { PaymentSuccessComponent } from './layouts/public/components/payment-success/payment-success.component';

const appearance: MatFormFieldDefaultOptions = {
  appearance: 'outline'
};

export function HttpLoaderFactory(http: HttpClient): TranslateHttpLoader {
  return new TranslateHttpLoader(http);
}

@NgModule({
  declarations: [
    AppComponent,
    AppSideNavbarComponent,
    AppToolbarListComponent,
    AdminToolbarListComponent,
    UserComponent,
    AdminDashboardComponent,
    AdminComponent,
    AuthComponent,
    UserDashboardComponent,
    AdminSideNavListComponent,
    MyCommunityComponent,
    CourseDialogFormComponent,
    CoursesComponent,
    RatingStarComponent,
    CourseOverviewComponent,
    secondsToLength,
    toShortText,
    toCeil,
    DateConverter,
    ValidationMsgComponent,
    UsersComponent,
    FacultiesComponent,
    FacultyCreateDialogComponent,
    LoadingCircularComponent,
    UniveristiesComponent,
    UniveristyFormDialogComponent,
    AcademicSpecializationsComponent,
    AcademicSpecializationFormDialogComponent,
    LangingPageComponent,
    ProfileEditComponent,
    UserSideNavListComponent,
    CourseCardComponent,
    MyCoursesComponent,
    FooterComponent,
    Page404Component,
    MyCourseCardComponent,
    BuyCourseComponent,
    LectureCardComponent,
    LectureDialogFormComponent,
    InputStarRatingComponent,
    ReviewDialogFormComponent,
    PaymentComponent,
    UserToolBarComponent,
    NavBarComponent,
    PaymentSuccessComponent
  ],
  imports: [
    MatSidenavModule,
    BrowserModule,
    AppRoutingModule,
    UserRoutingModule,
    AuthRoutingModule,
    AdminRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    FlexLayoutModule,
    MaterialModule,
    HttpClientModule,
    NgApexchartsModule,

    TranslateModule.forRoot({
        loader: {
            provide: TranslateLoader,
            useFactory: HttpLoaderFactory,
            deps: [HttpClient]
        }
    })
  ],
  providers: [
    DatePipe,
    {
      provide: MAT_FORM_FIELD_DEFAULT_OPTIONS,
      useValue: appearance
    },
    { provide: HTTP_INTERCEPTORS , useClass: AuthInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: loaderInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
