package br.cefetmg.entidades;

import br.cefetmg.entidades.TipoPerfil;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-09-08T02:17:50", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Funcionario.class)
public class Funcionario_ { 

    public static volatile SingularAttribute<Funcionario, String> senha;
    public static volatile SingularAttribute<Funcionario, String> telefone;
    public static volatile SingularAttribute<Funcionario, TipoPerfil> tipoPerfil;
    public static volatile SingularAttribute<Funcionario, String> nome;
    public static volatile SingularAttribute<Funcionario, Integer> idEmpresa;
    public static volatile SingularAttribute<Funcionario, Integer> id;

}