import { HttpClient } from "@angular/common/http";
import { BehaviorSubject, Observable } from "rxjs";
import { Injectable } from "@angular/core";
import { baseURL } from "../constants/constants"; 
import { User } from "../model/user,model";

@Injectable({ providedIn: 'root' })
export class AcademicSpecializationService {

  constructor(private http: HttpClient) { }
  getAll(): Observable<any> {
    return this.http.get(baseURL + '/academic_specialization');
  }

  public save(requestbody: any): Observable<any> {
    return this.http.post<any>(baseURL + '/academic_specialization/save', requestbody);
  }

  public update(requestbody: any): Observable<any> {
    return this.http.put<any>(baseURL + '/academic_specialization/update', requestbody);
  }

/*  
  

  public save(requestbody: UserSaveRequest): Observable<any> {
    return this.http.post<any>(baseURL + '/users', requestbody);
  }

  findById(id: number): Observable<any> {
    return this.http.get(baseURL + '/users/' + id)
  }

  searchUser(searchText: string): Observable<any> {
    return this.http.get(baseURL + '/users/search/' + searchText)
  }
 
  public delete(id: number): Observable<any> {
    return this.http.post<any>(baseURL + '/persons/delete/' + id, null);
  }
  */

}