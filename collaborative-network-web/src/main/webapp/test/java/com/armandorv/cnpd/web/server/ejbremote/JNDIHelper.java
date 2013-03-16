package com.armandorv.cnpd.web.server.ejbremote;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

import com.armandorv.cnpd.business.remote.IUsersManagerRemote;

/**
 * Only for tests, in runtime @Ejb is used.
 * 
 * @author armandorv
 * 
 */
public class JNDIHelper<T> {

	public JNDIHelper() {

	}

	public T doLookup() {

		try {

			final Hashtable<String, String> jndiProperties = new Hashtable<String, String>();

			jndiProperties.put(Context.URL_PKG_PREFIXES,
					"org.jboss.ejb.client.naming");

			final Context context = new InitialContext(jndiProperties);

			final String appName = "";

			final String moduleName = "collaborative-network-business-0.0.1-SNAPSHOT.jar";

			final String distinctName = "";

			final String beanName = "UsersManager";

			final String viewClassName = IUsersManagerRemote.class.getName();

			Object object = context
					.lookup("ejb:" + appName + "/" + moduleName + "/"
							+ distinctName + "/" + beanName + "!"
							+ viewClassName);

			@SuppressWarnings("unchecked")
			T result = (T) object;

			return result;

		} catch (Exception e) {
			throw new RuntimeException("Error obtain remote ejb.", e);
		}

	}

	public T doLookup(String jndiName) {

		try {

			final Hashtable<String, String> jndiProperties = new Hashtable<String, String>();

			jndiProperties.put(Context.URL_PKG_PREFIXES,
					"org.jboss.ejb.client.naming");

			final Context context = new InitialContext(jndiProperties);

			Object object = context.lookup(jndiName);

			@SuppressWarnings("unchecked")
			T result = (T) object;

			return result;

		} catch (Exception e) {
			throw new RuntimeException("Error obtain remote ejb.", e);
		}

	}

}
