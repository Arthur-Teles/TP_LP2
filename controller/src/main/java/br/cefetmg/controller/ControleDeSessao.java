package br.cefetmg.controller;

import br.cefetmg.entidades.utils.SessaoUsuario;

public class ControleDeSessao {
    public void setInfosUsuario(String username, String senha, int tipoConta) {
        SessaoUsuario sessao = SessaoUsuario.getInstance();
        sessao.setUsername(username);
        sessao.setSenha(senha);
        sessao.setTipoConta(tipoConta);
    }
    
    public void getUsernameSessao() {
        SessaoUsuario.getInstance().getUsername();
    }
    
    public void getSenhaSessao() {
        SessaoUsuario.getInstance().getSenha();
    }
    
    public void getTipoContaSessao() {
        SessaoUsuario.getInstance().getTipoConta();
    }
}