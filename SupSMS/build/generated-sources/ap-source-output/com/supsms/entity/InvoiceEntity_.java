package com.supsms.entity;

import com.supsms.entity.UserEntity;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-12-19T02:40:42")
@StaticMetamodel(InvoiceEntity.class)
public class InvoiceEntity_ { 

    public static volatile SingularAttribute<InvoiceEntity, Date> date;
    public static volatile SingularAttribute<InvoiceEntity, Double> price;
    public static volatile SingularAttribute<InvoiceEntity, Long> id;
    public static volatile SingularAttribute<InvoiceEntity, Double> taxe;
    public static volatile SingularAttribute<InvoiceEntity, UserEntity> user;

}