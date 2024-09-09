package br.cefetmg.controller;

import br.cefetmg.entidades.utils.SessaoUsuario;

public class ControleDeSessao {
    public void setInfosUsuario(String username, String senha, int tipoConta) {
        SessaoUsuario sessao = SessaoUsuario.getInstance();
        sessao.setUsername(username);
        sessao.setSenha(senha);
        sessao.setTipoConta(tipoConta);
    }
    
    public String getUsernameSessao() {
        return SessaoUsuario.getInstance().getUsername();
    }
    
    public String getSenhaSessao() {
        return SessaoUsuario.getInstance().getSenha();
    }
    
    public int getTipoContaSessao() {
        return SessaoUsuario.getInstance().getTipoConta();
    }
}