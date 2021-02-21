import { Component, OnInit } from '@angular/core';

import { ClientesService } from 'src/app/clientes.service';
import { Cliente } from '../Cliente';
import { Router } from '@angular/router'; 

@Component({
  selector: 'app-clientes-lista',
  templateUrl: './clientes-lista.component.html',
  styleUrls: ['./clientes-lista.component.css']
})
export class ClientesListaComponent implements OnInit {

  clientes: Cliente[] = [];
  clienteSelecionado: Cliente;
  mensagemSucesso: String;
  mensagemErro: String;
  constructor(private service: ClientesService,
    private router: Router) { }

  ngOnInit(): void {
    this.service.getClientes()
    .subscribe(response => {
      this.clientes = response;
    })
  }

  novoCadastro() {
    this.router.navigate(['/clientes/form'])
  }

  preparaDelecao(cliente: Cliente){
    this.clienteSelecionado = cliente;
  }

  deletarCliente(){
    this.service
    .deletar(this.clienteSelecionado)
    .subscribe(response => {this.mensagemSucesso = 'Cliente deletado com sucesso!'
    this.ngOnInit();},
    error => this.mensagemErro = 'Erro ao deletar o cliente!');
  }

}
