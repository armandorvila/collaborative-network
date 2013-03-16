package com.armandorv.cnpd.web.client.view.util.properties;

import java.util.Date;

import com.armandorv.cnpd.web.shared.model.MessageInfo;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface MessageInfoProperties extends PropertyAccess<MessageInfo>
{

   ModelKeyProvider<MessageInfo> id();

   ValueProvider<MessageInfo, String> sender();

   ValueProvider<MessageInfo, Date> date();

   ValueProvider<MessageInfo, Boolean> read();

}
