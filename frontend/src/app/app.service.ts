import { Injectable } from '@angular/core';
import { Http, Response, Headers, URLSearchParams, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs';

import { App } from './app';

@Injectable()
export class ArticleService {

  allMobilesUrl = 'http://localhost:8080/Mobile/all-Mobile';
  articleUrl = 'http://localhost:8080/user/article';

  // Create constructor to get Http instance
  constructor(private http: Http) {
  }

  getAllMobile(): Observable<App[]> {
    return this.http.get(this.allMobilesUrl)
      .map(this.extractData)
      .catch(this.handleError);

  }

  createMobile(app: App): Observable<number> {
    const cpHeaders = new Headers({ 'Content-Type': 'application/json' });
    const options = new RequestOptions({ headers: cpHeaders });
    return this.http.post(this.articleUrl, app, options)
      .map(success => success.status)
      .catch(this.handleError);
  }

  getMobileById(appID: string): Observable<App> {
    const cpHeaders = new Headers({ 'Content-Type': 'application/json' });
    const cpParams = new URLSearchParams();
    cpParams.set('id', appID);
    const options = new RequestOptions({ headers: cpHeaders, params: cpParams });
    return this.http.get(this.articleUrl, options)
      .map(this.extractData)
      .catch(this.handleError);
  }
  //Update article
  updateMobileById(app: App): Observable<number> {
    const cpHeaders = new Headers({ 'Content-Type': 'application/json' });
    const options = new RequestOptions({ headers: cpHeaders });
    return this.http.put(this.articleUrl, app, options)
      .map(success => success.status)
      .catch(this.handleError);
  }
  //Delete article
  deleteMobileById(appID: string): Observable<number> {
    const cpHeaders = new Headers({ 'Content-Type': 'application/json' });
    const cpParams = new URLSearchParams();
    cpParams.set('id', appID);
    const options = new RequestOptions({ headers: cpHeaders, params: cpParams });
    return this.http.delete(this.articleUrl, options)
      .map(success => success.status)
      .catch(this.handleError);
  }
  private extractData(res: Response) {
    const body = res.json();
    return body;
  }
  private handleError (error: Response | any) {
    console.error(error.message || error);
    return Observable.throw(error.status);
  }
}
