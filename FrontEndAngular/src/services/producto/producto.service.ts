import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map,catchError } from 'rxjs/operators';
import { Producto } from '../../interfaces/producto.interface';
import { Observable, of } from 'rxjs';
import { UrlsEnum } from 'src/model/enum/UrlsEnum';

@Injectable({
  providedIn: 'root'
})
export class ProductoService /*extends BaseService*/ {

  constructor(public http:HttpClient) {
    
  }

  saveProduct(product:Producto){
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
        return this.http.post(UrlsEnum.SAVE_PRODUCTO_URL.url,product,{headers: headers})
            .pipe(map(data => {
                return data;
            }),
          catchError(this.handleError<any>('saveProduct')));
  }

  updateProduct(product:Producto){
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
        return this.http.put(UrlsEnum.UPDATE_PRODUCTO_URL.url,product,{headers: headers})
            .pipe(map(data => {
                return data;
            }),
          catchError(this.handleError<any>('updateProduct')));
  }

  getProducts(){
      return this.http.get(UrlsEnum.SEARCH_PRODUCTO_URL.url)
          .pipe(map(data => {
              return data;
          }),
        catchError(this.handleError<any>('getProducts')));
  }

  getProductsByFilter(productString:string, withStock:string) {
    return this.http.get(UrlsEnum.PRODUCTO_ALL_BY_FILTER.url+'/'+productString+'/'+withStock)
        .pipe(map(data => {
            return data;
        }),
      catchError(this.handleError<any>('getProductsByFilter')));
  }

  deleteProduct(idProduct:number){
    return this.http.delete(UrlsEnum.DELETE_PRODUCTO_URL.url + "/" + idProduct)
        .pipe(map(data => {
            return data;
        }),
     catchError(this.handleError<any>('deleteProduct')));
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
