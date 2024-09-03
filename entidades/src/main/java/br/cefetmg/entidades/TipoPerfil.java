package br.cefetmg.entidades;

public enum TipoPerfil {

    ADMINISTRADOR, ATENDENTE, ENTREGUE;
    
    public static TipoPerfil tipoFuncionario(String tipo) {
        
        TipoPerfil tipoPerfil;
        tipoPerfil = TipoPerfil.valueOf(tipo.toUpperCase());
        
        return tipoPerfil;
    }
}
