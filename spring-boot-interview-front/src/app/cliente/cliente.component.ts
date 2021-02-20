import  * as VMasker  from 'vanilla-masker';
import { CidadeService } from './../cidade/cidade.service';
import { SnotifyService } from 'ng-snotify';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ClienteService } from './cliente.service';
import { ClienteResorce } from './cliente';
import { Component, OnInit } from '@angular/core';
import { CidadeResorce } from '../cidade/cidade';
import { NgbModalRef, NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-cliente',
  templateUrl: './cliente.component.html',
  styleUrls: ['./cliente.component.css']
})
export class ClienteComponent implements OnInit {

  public clientes: ClienteResorce[];
  public cidades: CidadeResorce[];
  clienteResource : ClienteResorce = new ClienteResorce();
  formularioConsulta :FormGroup;
  formularioInclusao :FormGroup;
  formularioEdicao :FormGroup;
  modal: NgbModalRef;

  constructor(
    private service : ClienteService,
    private serviceCidade : CidadeService,
    private formBuilder : FormBuilder,
    private notificationService : SnotifyService,
    private modalService : NgbModal
  ) { }

  ngOnInit(): void {
    this.criaFormularioConsulta();
    this.criaFormularioInclusao();
    this.criarFormularioEdicao();

    this.carregarCidades();
  }

  carregarCidades(){
    this.serviceCidade.consultarTodas().subscribe(
       response => {
          this.cidades = response;
          console.log('Cidades carregadas')
      }, error => {
        console.log('Erro na consulta de cidades: ' + error)
        this.notificationService.error('Erro na consulta por nome:')
      }
    )
  }

  //- Consulta ------------------------------------------------
  criaFormularioConsulta(){
    this.formularioConsulta = this.formBuilder.group({
      id: ['', [ Validators.maxLength(15)]],
      nome: ['', [Validators.maxLength(45)]]
    });
  }

  consultar(){
    this.clienteResource = this.formularioConsulta.getRawValue();
    if(this.clienteResource.nome){
      this.service.consultarPorNome(this.clienteResource.nome).subscribe(
        response => {
          this.clientes = [];
          if (response) {
            this.clientes.push(response);
            console.log('Sucesso na consulta!');
          }
        }, error => {
          console.log('Erro na consulta por nome: ' + error)
          this.notificationService.error('Erro na consulta por nome:')
        }
      )
    } else if(this.clienteResource.id){
      this.service.consultarPorId(this.clienteResource.id).subscribe(
        response => {
          this.clientes = [];
          if (response) {
            this.clientes.push(response);
            console.log('Sucesso na consulta!');
          }
        }, error =>{
          console.log('Erro na consulta por id: ' + error)
          this.notificationService.error('Erro na consulta por id:')
        }
      )
    }
  }

  limparFormularioConsulta() {
    this.clientes = null;
    this.formularioConsulta.get("id").setValue(null);
    this.formularioConsulta.get("nome").setValue(null);
  }



   //- Cadastramento ------------------------------------------------
   criaFormularioInclusao(){
    this.formularioInclusao = this.formBuilder.group({
      nome: ['', [Validators.required, Validators.maxLength(45)]],
      sexo: ['', [Validators.required]],
      dataNascimento: ['', [Validators.required, Validators.maxLength(10)]],
      cidadeId: ['', [Validators.required]],
    });
  }

  selecionarSexo(event:any){
    this.clienteResource.sexo = event.currentTarget.value;
  }

  selecionarCidade(event:any){
    this.clienteResource.cidadeId = this.formularioInclusao.controls['cidadeId'].value;
  }

  formatarData(htmlInput: any){
    const mascaraData : string = '99/99/9999';
    VMasker(htmlInput).maskPattern(mascaraData)
  }

  changeDataNascimento(){
    this.clienteResource.dataNascimento = this.formularioInclusao.controls['dataNascimento'].value;
  }

  salvar(){
    this.clienteResource = this.formularioInclusao.getRawValue();
    this.service.cadastrar(this.clienteResource).subscribe(
      response => {
        console.log('Cliente incluido com sucesso!');
        this.notificationService.success("Cliente incluído com sucesso!");

        this.formularioInclusao.reset();
      },
        error => {console.log('Erro na inclusão:' + error)}
    )
  }

  // Edição
  criarFormularioEdicao(){
    this.formularioEdicao = this.formBuilder.group({
      nome: ['', [Validators.required, Validators.maxLength(45)]]
    });
  }

  editar(){
    let cliEmEdicao = new  ClienteResorce();
    cliEmEdicao.id = this.clienteResource.id;
    cliEmEdicao.nome = this.formularioEdicao.get('nome').value;
    cliEmEdicao.dataNascimento = this.clienteResource.dataNascimento;
    cliEmEdicao.idade = this.clienteResource.idade;
    cliEmEdicao.sexo = this.clienteResource.sexo;
    cliEmEdicao.cidade = this.clienteResource.cidade;
    cliEmEdicao.cidadeId = this.clienteResource.cidadeId;
    debugger
    this.service.alterarNome(cliEmEdicao).subscribe(
      response => {
        console.log('Nome do cliente alterado com sucesso!');
        this.notificationService.success("Nome do cliente alterado com sucesso!");
        this.formularioEdicao.get('nome').setValue(null);
        this.limparFormularioConsulta();
      },
        error => {console.log('Erro na alteração do nome do cliente:' + error)}
    )
  }

  // Modal de alteração
  confirmacao(conteudo: any, cliente : ClienteResorce) {
    this.clienteResource = cliente;
    this.modal = this.modalService.open(conteudo, {
      centered: true,
      backdrop: 'static'
    });
  }

  fechar() {
    this.modal.close();
  }

  // Exclusão
  excluir(){
    this.service.excluir(this.clienteResource.id).subscribe(
      response => {
        console.log('Cliente excluido com sucesso!');
        this.notificationService.success("Cliente excluido com sucesso!");
        this.limparFormularioConsulta();
      },
        error => {console.log('Erro na inclusão:' + error)}
    )
  }

}
