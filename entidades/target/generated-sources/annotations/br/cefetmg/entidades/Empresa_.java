package br.cefetmg.entidades;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-09-02T18:11:03", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Empresa.class)
public class Empresa_ { 

    public static volatile SingularAttribute<Empresa, String> nome;
    public static volatile SingularAttribute<Empresa, Long> CNPJ;
    public static volatile SingularAttribute<Empresa, Double> porcentagemComissaoEntregador;

}