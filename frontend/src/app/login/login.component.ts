import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { OauthService } from '../oauth.service';
import { Usuario } from './Usuario';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  username: string;
  password: string;
  cadastrando: boolean;
  mensagemSucesso: string;
  errors: String[];

  constructor(
    private router: Router,
    private OauthService: OauthService
    ) { 
    
  }

  onsubmit() {
    this.OauthService.tentarLogar(this.username, this.password)
    .subscribe(Response => {
      const access_token = JSON.stringify(Response);
      localStorage.setItem('access_token', access_token); 
      this.router.navigate(['/home']);
    }, errorResponse => {
      this.errors = ['Usuário e/ou senha incorrretos!'];
    })
    
  }

  preparaCadastrar(event){
    event.preventDefault();
    this.cadastrando = true;
  }

  cancelaCadastro(){
    this.cadastrando = false;
  }

  cadastraUsuario() {
    const usuario: Usuario = new Usuario;
    usuario.username = this.username;
    usuario.password = this.password;
    this.OauthService.salvar(usuario)
    .subscribe( response =>{
      this.mensagemSucesso = "Cadastro realizado com sucesso!";
      this.cadastrando = false;
      this.username = '';
      this.password = '';
      this.errors = [];
    }, errorResponse => {
      this.errors = errorResponse.error.errors;
      this.mensagemSucesso = null;
    })
  }

}
