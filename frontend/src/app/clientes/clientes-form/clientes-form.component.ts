import { Component, OnInit } from '@angular/core';

import { Cliente } from '../Cliente';
import { ClientesService } from '../../clientes.service';
import { Router, ActivatedRoute } from '@angular/router';
@Component({
  selector: 'app-clientes-form',
  templateUrl: './clientes-form.component.html',
  styleUrls: ['./clientes-form.component.css']
})
export class ClientesFormComponent implements OnInit {

  cliente: Cliente;
  sucesso: boolean = false;
  errors: String[];
  id: number;
  mensagem: String;

  constructor(private service: ClientesService,
    private router: Router,
    private activatedRoute: ActivatedRoute
    ) {
    this.cliente = new Cliente();
    
    
  }

  onSubmit() {
    
    if(this.id) {
      this.service.atualizar(this.cliente)
      .subscribe(Response => {
        this.sucesso = true;
        this.errors = null;
        //MENSAGEM AQUI
       this.mensagem = 'atualizado';
      }, errorResponse => {
        this.errors = ['Erro ao atualizar o cliente!'];
        this.sucesso = false;
      })   

    }else {
      this.service.salvar(this.cliente)
      .subscribe( Response => {
        this.sucesso = true;
        this.errors = null;
        this.cliente = Response;
        //MENSAGEM AQUI
        this.mensagem = 'cadastrado';
      }, errorResponse => {
          this.sucesso = false;
          this.errors = errorResponse.error.errors;
          
          console.log(errorResponse.error.errors);
      })
    }
  }


  ngOnInit(): void {
      let params = this.activatedRoute.params;

      params.subscribe(urlParams => {
        this.id = urlParams['id'];
        if(this.id){
          this.service.getClienteById(this.id)
          .subscribe( 
            response => this.cliente = response,
            errorResponse => this.cliente = new Cliente()
          )
        }
      })
  }
  
  voltarParaListagem() {
      this.router.navigate(['/clientes/lista']);
  }

}
