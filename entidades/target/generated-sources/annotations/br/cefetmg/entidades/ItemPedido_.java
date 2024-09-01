package br.cefetmg.entidades;

import br.cefetmg.entidades.Produto;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-08-31T16:28:47", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(ItemPedido.class)
public class ItemPedido_ { 

    public static volatile ListAttribute<ItemPedido, Produto> produtos;
    public static volatile SingularAttribute<ItemPedido, Integer> id;
    public static volatile SingularAttribute<ItemPedido, Integer> idPedido;
    public static volatile SingularAttribute<ItemPedido, Integer> quantidade;
    public static volatile SingularAttribute<ItemPedido, Double> valorUnitario;

}