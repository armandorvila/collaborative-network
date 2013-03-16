package com.armandorv.cnpd.web.server;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.armandorv.cnpd.web.server.remote.ChatServiceTest;
import com.armandorv.cnpd.web.server.remote.ContactsServiceTest;
import com.armandorv.cnpd.web.server.remote.DisccussionsServiceTest;
import com.armandorv.cnpd.web.server.remote.InformationServiceTest;
import com.armandorv.cnpd.web.server.remote.LoadingServiceTest;
import com.armandorv.cnpd.web.server.remote.MeetingsServiceTest;
import com.armandorv.cnpd.web.server.remote.MilestonesServiceTest;
import com.armandorv.cnpd.web.server.remote.ProjectServiceTest;
import com.armandorv.cnpd.web.server.remote.ReferencesServiceTest;
import com.armandorv.cnpd.web.server.remote.ResourcesServiceTest;
import com.armandorv.cnpd.web.server.remote.SingupServiceTest;
import com.armandorv.cnpd.web.server.remote.UsersServiceTest;

@RunWith(Suite.class)
@SuiteClasses(
{UsersServiceTest.class, SingupServiceTest.class, ResourcesServiceTest.class, ReferencesServiceTest.class,
      ProjectServiceTest.class, MilestonesServiceTest.class, MeetingsServiceTest.class, LoadingServiceTest.class,
      InformationServiceTest.class, ChatServiceTest.class, ContactsServiceTest.class, DisccussionsServiceTest.class})
public class PresentationTests
{

}
