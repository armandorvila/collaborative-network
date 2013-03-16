package com.armandorv.cnpd.web.shared.qualifiers;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

/**
 * Qualifier to identify ChatView or ChatPresenter related, contextual
 * instances.
 * 
 * @author armandorv
 * 
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target(
{ElementType.FIELD, ElementType.TYPE, ElementType.METHOD, ElementType.PARAMETER})
public @interface ContactMinus {

}