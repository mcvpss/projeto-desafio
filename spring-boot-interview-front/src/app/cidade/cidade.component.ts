import { Component, OnInit } from '@angular/core';
import { CidadeService } from './cidade.service';
import { CidadeResorce } from './cidade';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { SnotifyService } from 'ng-snotify';

@Component({
  selector: 'app-cidade',
  templateUrl: './cidade.component.html',
  styleUrls: ['./cidade.component.css']
})
export class CidadeComponent implements OnInit {

  public cidades: CidadeResorce[] ;
  cidadeResource : CidadeResorce;
  formularioInclusao :FormGroup;
  formularioConsulta :FormGroup;

  constructor(
    private service : CidadeService,
    private formBuilder : FormBuilder,
    private notificationService : SnotifyService
  ) { }

  ngOnInit(): void {
    this.criaFormularioConsulta();
    this.criaFormularioInclusao();
  }

  // ------- Consulta -------------
  criaFormularioConsulta(){
    this.formularioConsulta = this.formBuilder.group({
      nome: ['', [ Validators.maxLength(45)]],
      estado: ['', [Validators.maxLength(45)]]
    });
  }

  consultar(){
    this.cidadeResource = this.formularioConsulta.getRawValue();
    if(this.cidadeResource.nome){
      this.service.consultarPorNome(this.cidadeResource.nome).subscribe(
        response => {
          this.cidades = [];
          if (response) {
            this.cidades.push(response);
            console.log('Sucesso na consulta!');
          }
        }, error => {
          console.log('Erro na consulta por nome: ' + error)
          this.notificationService.error('Erro na consulta por nome:')
        }
      )
    } else if(this.cidadeResource.estado){
      this.service.consultarPorEstado(this.cidadeResource.estado).subscribe(
        response => {
          if (response) {
            this.cidades = response;
            console.log('Sucesso na consulta!');
          }
        }, error =>{
          console.log('Erro na consulta por estado: ' + error)
          this.notificationService.error('Erro na consulta por estado:')
        }
      )
    }
  }

  limparFormularioConsulta() {
    this.cidades = null;
    this.formularioConsulta.get("nome").setValue(null);
    this.formularioConsulta.get("estado").setValue(null);
  }

  // ------- Inclusão -------------
  criaFormularioInclusao(){
    this.formularioInclusao = this.formBuilder.group({
      nome: ['', [Validators.required, Validators.maxLength(45)]],
      estado: ['', [Validators.required, Validators.maxLength(45)]]
    });
  }

  salvar(){
    this.cidadeResource = this.formularioInclusao.getRawValue();
    this.service.cadastrar(this.cidadeResource).subscribe(
      response => {
        console.log('Cidade incluida com sucesso!')
        this.notificationService.info("Cidade incluída com sucesso!")
        this.formularioInclusao.get("nome").setValue(null);
        this.formularioInclusao.get("estado").setValue(null);
      },
        error => {console.log('Erro na inclusão:' + error)}
    )
  }

}
