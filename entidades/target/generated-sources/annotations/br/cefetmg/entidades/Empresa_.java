package br.cefetmg.entidades;

import br.cefetmg.entidades.Funcionario;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-09-08T18:36:49", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Empresa.class)
public class Empresa_ { 

    public static volatile SingularAttribute<Empresa, String> nome;
    public static volatile SingularAttribute<Empresa, Long> CNPJ;
    public static volatile SingularAttribute<Empresa, Double> porcentagemComissaoEntregador;
    public static volatile ListAttribute<Empresa, Funcionario> funcionarios;

}