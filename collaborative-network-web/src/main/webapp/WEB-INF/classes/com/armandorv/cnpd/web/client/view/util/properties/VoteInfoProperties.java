package com.armandorv.cnpd.web.client.view.util.properties;

import com.armandorv.cnpd.web.shared.model.VoteInfo;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface VoteInfoProperties extends PropertyAccess<VoteInfo>
{
   ModelKeyProvider<VoteInfo> userId();
   
   ValueProvider<VoteInfo, String> voterName();
   
   ValueProvider<VoteInfo, String> option();
   
   ValueProvider<VoteInfo, String> argument();
}
