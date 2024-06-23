import {Injectable, numberAttribute} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {AccountDetails} from "../model/account.model";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class AccountsService {
  constructor(private http:HttpClient) { }

  public getAccount(accountId : string,page : number,size : number):Observable<AccountDetails>{
    return this.http.get<AccountDetails>(environment.backendHost+"/accounts/"+accountId+"/pageOperations?page="+page+"&size="+size);
  }
  public debit(accountId : string,amount:number,description:string):Observable<AccountDetails>{

  let data={accountId:accountId,amount:amount,description:description}
      return this.http.post<AccountDetails>(environment.backendHost+"/accounts/debit",data);
    }
  public credit(accountId : string,amount:number,description:string):Observable<AccountDetails>{

    let data={accountId:accountId,amount:amount,description:description}
    return this.http.post<AccountDetails>(environment.backendHost+"/accounts/credit",data);
  }
  public transfer(accountSource : string,accountDestination : string,amount:number,description:string):Observable<AccountDetails>{

    let data={accountSource,accountDestination,amount,description}
    return this.http.post<AccountDetails>(environment.backendHost+"/accounts/transfer",data);
  }
}