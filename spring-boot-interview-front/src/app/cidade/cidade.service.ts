import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CidadeResorce } from './cidade';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

export const API_URL = environment.apiUrl;

@Injectable({
  providedIn: 'root'
})
export class CidadeService {

  constructor(private httpClient : HttpClient) {

  }

  public cadastrar (cidade :CidadeResorce) : Observable<CidadeResorce>{
    return this.httpClient.post<CidadeResorce>(`${API_URL}/cidade/cadastrar`, cidade);
  }

  public consultarPorNome (nome :string) : Observable<CidadeResorce>{
     return this.httpClient.get<CidadeResorce>(`${API_URL}/cidade/${nome}/consultarPorNome`);
  }

  public consultarPorEstado (estado :string) : Observable<CidadeResorce[]>{
    return this.httpClient.get<CidadeResorce[]>(`${API_URL}/cidade/${estado}/consultarPeloEstado`);
  }

  public consultarTodas() : Observable<CidadeResorce[]>{
    return this.httpClient.get<CidadeResorce[]>(`${API_URL}/cidade/consultarTodas`);
  }


}

