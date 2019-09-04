import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map,catchError } from 'rxjs/operators';
import { Producto } from '../../interfaces/producto.interface';
import { Observable, of } from 'rxjs';
import { UrlsEnum } from 'src/model/enum/UrlsEnum';
import { Kardexout } from 'src/interfaces/kardexout.interface';

@Injectable({
  providedIn: 'root'
})
export class KardexoutService {

  constructor(public http:HttpClient) {
    
  }

  saveKardex(kardexout:Kardexout){
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
        return this.http.post(UrlsEnum.SAVE_KARDEXOUT_URL.url,kardexout,{headers: headers})
            .pipe(map(data => {
                return data;
            }),
          catchError(this.handleError<any>('saveKardex')));
  }

  getLastSequence(){
    return this.http.get(UrlsEnum.KARDEXOUT_LAST_SECUENCE.url)
        .pipe(map(data => {
            return data;
        }),
      catchError(this.handleError<any>('getLastSequence')));
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
