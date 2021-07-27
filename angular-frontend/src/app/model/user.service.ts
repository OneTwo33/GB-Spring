import { Injectable } from '@angular/core';
import {User} from "./user";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private identity: number = 6

  // deploy only, for dev uses proxy.json.conf and start node server 'ng serve'
  private url: string = 'http://localhost:8080/spring-boot-app'

  private users: { [key: number]: User } = {
    1: new User(1, 'First user', 18),
    2: new User(2, 'Second user', 27),
    3: new User(3, 'Third user', 34),
    4: new User(4, 'Fourth user', 52),
    5: new User(5, 'Fifth user', 21),
  }

  constructor(public http: HttpClient) { }

  public findAll() {
    return this.http.get<User[]>(this.url + '/api/v1/user/all').toPromise()
    // return new Promise<User[]>((resolve, reject) => {
    //   resolve(
    //     Object.values(this.users)
    //   )
    // })
  }

  public findById(id: number) {
    return this.http.get<User>(this.url + `/api/v1/user/${id}`).toPromise()
    // return new Promise<User>((resolve, reject) => {
    //   resolve(
    //     this.users[id]
    //   )
    // })
  }

  public save(user: User) {
    if (user.id == -1) {
      return this.http.post<User>(this.url + '/api/v1/user', user).toPromise()
    }
    return this.http.put<User>(this.url + '/api/v1/user', user).toPromise()
    // return new Promise<void>((resolve, reject) => {
    //   if (user.id == -1) {
    //     user.id = this.identity++
    //   }
    //   this.users[user.id] = user
    //   resolve()
    // })
  }

  public delete(id: number) {
    return this.http.delete<void>(this.url + `/api/v1/user/${id}`).toPromise()
    // return new Promise<void>((resolve, reject) => {
    //   delete this.users[id]
    //   resolve()
    // })
  }
}
