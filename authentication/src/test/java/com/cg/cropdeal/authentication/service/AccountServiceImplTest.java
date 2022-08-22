package com.cg.cropdeal.authentication.service;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@SelectClasses ({SignInTest.class, SignUpTest.class})
@Suite
@SuiteDisplayName ("Test signin and signup with email functionality")
class AccountServiceImplTest {

}