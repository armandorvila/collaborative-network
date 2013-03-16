package com.armandorv.cnpd.persistence.impl;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(
{DiscussionDaoTest.class, KnowledgeAreaDaoTest.class, MeetingDaoTest.class, MessageDaoTest.class,
      MilestoneDaoTest.class, NotificationDaoTest.class, ProjectDaoTest.class, ReferenceDaoTest.class,
      ResourceDaoTest.class, UserDaoTest.class})
public class PersistenceTests
{

}
