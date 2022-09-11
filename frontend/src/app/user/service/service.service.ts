import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class ServiceService {

  private getUserApi:string;
  
  private getUserbyUserNameApi:string;

  constructor(private http:HttpClient) { 

       this.getUserApi="http://localhost:8090/user/get-user",
       this.getUserbyUserNameApi="http://localhost:8090/user/get-user-username"
  }

  public getUserById(id:number):Observable<User>{
    return  this.http.get<User>(this.getUserApi+"/"+id);
   }

   public getUserByUserName(username:String):Observable<User>{
    return  this.http.get<User>(this.getUserbyUserNameApi+"/"+username);
   }



}
