package com.armandorv.cnpd.web.shared.qualifiers;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

/**
 * Qualifier to identify Messages related contextual instances, for example @MessagesView.
 * 
 * @author armandorv
 * 
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target(
{ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE})
public @interface Messages {

}
