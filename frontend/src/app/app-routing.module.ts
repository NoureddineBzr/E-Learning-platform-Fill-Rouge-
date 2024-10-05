import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LangingPageComponent } from './layouts/public/components/langing-page/langing-page.component';
import { Page404Component } from './global/page404/page404.component';
const routes: Routes = [
  { path: '', component: LangingPageComponent },
  { path: 'login', redirectTo: '/login', pathMatch: 'full' },
/*   { path: 'user', redirectTo: '/user', pathMatch: 'full' },
  { path: 'admin', redirectTo: '/admin', pathMatch: 'full' },

  { path: '**' , pathMatch   : 'full', component: Page404Component} */

];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
