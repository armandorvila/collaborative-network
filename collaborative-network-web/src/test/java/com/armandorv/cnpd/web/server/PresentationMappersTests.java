package com.armandorv.cnpd.web.server;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.armandorv.cnpd.web.server.mapper.ContactMapperTest;
import com.armandorv.cnpd.web.server.mapper.DiscussionMapperTest;
import com.armandorv.cnpd.web.server.mapper.MessageMapperTest;
import com.armandorv.cnpd.web.server.mapper.NotificationMapperTest;
import com.armandorv.cnpd.web.server.mapper.ProjectMapperTest;
import com.armandorv.cnpd.web.server.mapper.ReferenceMapperTest;
import com.armandorv.cnpd.web.server.mapper.ResourceMapperTest;
import com.armandorv.cnpd.web.server.mapper.UserMapperTest;

@RunWith(Suite.class)
@SuiteClasses(
{ContactMapperTest.class, DiscussionMapperTest.class, MessageMapperTest.class, NotificationMapperTest.class,
      ProjectMapperTest.class, ReferenceMapperTest.class, ResourceMapperTest.class, UserMapperTest.class})
public class PresentationMappersTests
{

}
