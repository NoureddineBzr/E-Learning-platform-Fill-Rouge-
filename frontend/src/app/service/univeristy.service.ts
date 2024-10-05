import { HttpClient } from "@angular/common/http";
import { BehaviorSubject, Observable } from "rxjs";
import { Injectable } from "@angular/core";
import { baseURL } from "../constants/constants"; 
import { User } from "../model/user,model";

@Injectable({ providedIn: 'root' })
export class UniversityService {

  constructor(private http: HttpClient) { }
  getAll(): Observable<any> {
    return this.http.get(baseURL + '/universities');
  }

  public save(requestbody: any): Observable<any> {
    return this.http.post<any>(baseURL + '/universities/save', requestbody);
  }

  public update(requestbody: any): Observable<any> {
    return this.http.put<any>(baseURL + '/universities/update', requestbody);
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