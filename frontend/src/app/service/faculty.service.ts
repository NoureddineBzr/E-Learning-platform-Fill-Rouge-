import { HttpClient } from "@angular/common/http";
import { BehaviorSubject, Observable } from "rxjs";
import { Injectable } from "@angular/core";
import { baseURL } from "../constants/constants"; 
import { User } from "../model/user,model";

@Injectable({ providedIn: 'root' })
export class FacultyService {

  constructor(private http: HttpClient) { }
  getAll(): Observable<any> {
    return this.http.get(baseURL + '/faculties');
  }
  public save(requestbody: any): Observable<any> {
    return this.http.post<any>(baseURL + '/faculties/save', requestbody);
  }

  public update(requestbody: any): Observable<any> {
    return this.http.put<any>(baseURL + '/faculties/update', requestbody);
  }
/*  
  



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