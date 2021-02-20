import { Observable } from 'rxjs';
import { ClienteResorce } from './cliente';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Injectable } from '@angular/core';

export const API_URL = environment.apiUrl;

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  constructor(private httpClient : HttpClient) { }

  public cadastrar (cliente :ClienteResorce) : Observable<ClienteResorce>{
    return this.httpClient.post<ClienteResorce>(`${API_URL}/cliente/cadastrar`, cliente);
  }

  public consultarPorNome (nome :string) : Observable<ClienteResorce>{
     return this.httpClient.get<ClienteResorce>(`${API_URL}/cliente/${nome}/consultarPorNome`);
  }

  public consultarPorId (id :number) : Observable<ClienteResorce>{
    return this.httpClient.get<ClienteResorce>(`${API_URL}/cliente/${id}/consultarPorId`);
  }

  public excluir (id :number) : Observable<ClienteResorce[]>{
    return this.httpClient.get<ClienteResorce[]>(`${API_URL}/cliente/${id}/excluir`);
  }

  public alterarNome (cliente :ClienteResorce) : Observable<ClienteResorce>{
    return this.httpClient.post<ClienteResorce>(`${API_URL}/cliente/alterarNome`, cliente);
  }


}
