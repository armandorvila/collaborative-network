package com.armandorv.cnpd.test.pojo;

import javax.enterprise.context.Dependent;

import com.armandorv.cnpd.test.IPojoBuilder;

@Dependent
@Business
public class BusinessPojoBuilder extends PersistencePojoBuilder implements IPojoBuilder
{

}
