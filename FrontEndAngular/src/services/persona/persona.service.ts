import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map,catchError } from 'rxjs/operators';
import { Persona } from '../../interfaces/persona.interface';
// import { BaseService } from '../base/base.service';
import { Observable, of } from 'rxjs';
import { UrlsEnum } from 'src/model/enum/UrlsEnum';

@Injectable({
  providedIn: 'root'
})
export class PersonaService /*extends BaseService*/ {

  constructor(public http:HttpClient) {
    // super(http);
  }

  savePerson(person:Persona){
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
        return this.http.post(UrlsEnum.SAVE_PERSONA_URL.url,person,{headers: headers})
            .pipe(map(data => {
                return data;
            }),
          catchError(this.handleError<any>('savePerson')));
  }

  updatePerson(person:Persona){
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
        return this.http.put(UrlsEnum.UPDATE_PERSONA_URL.url,person,{headers: headers})
            .pipe(map(data => {
                return data;
            }),
          catchError(this.handleError<any>('updatePerson')));
  }

  getPersons(){
      return this.http.get(UrlsEnum.SEARCH_PERSONA_URL.url)
          .pipe(map(data => {
              return data;
          }),
        catchError(this.handleError<any>('getPersons')));
  }

  getPersonsByFilter(personString:string) {
    return this.http.get(UrlsEnum.PERSONA_ALL_BY_FILTER.url+'/'+personString)
        .pipe(map(data => {
            return data;
        }),
      catchError(this.handleError<any>('getPersonsByFilter')));
  }

  deletePerson(idPerson:number){
    return this.http.delete(UrlsEnum.DELETE_PERSONA_URL.url + "/" + idPerson)
        .pipe(map(data => {
            return data;
        }),
     catchError(this.handleError<any>('deletePerson')));
  }

  protected handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      // console.error(error.error); // log to console instead

      // TODO: better job of transforming error for user consumption
      // console.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(error.error as T);
    };
  }

}
