package com.cg.cropdeal.authentication;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.springframework.boot.test.context.SpringBootTest;

@SelectPackages ({"com.cg.cropdeal.authentication.service", "com.cg.cropdeal.authentication.model", "com.cg.cropdeal.authentication.controller"})
@Suite
@SpringBootTest
class AuthenticationApplicationTests {


}
