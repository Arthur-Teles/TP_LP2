package br.cefetmg.entidades;

import br.cefetmg.entidades.StatusPedido;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-09-09T00:27:57", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Pedido.class)
public class Pedido_ { 

    public static volatile SingularAttribute<Pedido, Long> CPFCliente;
    public static volatile SingularAttribute<Pedido, String> obs;
    public static volatile SingularAttribute<Pedido, Date> data;
    public static volatile SingularAttribute<Pedido, Double> valorTotal;
    public static volatile SingularAttribute<Pedido, String> formaPag;
    public static volatile SingularAttribute<Pedido, Integer> qntd;
    public static volatile SingularAttribute<Pedido, Integer> id;
    public static volatile SingularAttribute<Pedido, Integer> idEntregador;
    public static volatile SingularAttribute<Pedido, StatusPedido> status;

}