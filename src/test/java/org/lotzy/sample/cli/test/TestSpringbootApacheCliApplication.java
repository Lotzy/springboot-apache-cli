/*
 * Copyright 2012-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.lotzy.sample.cli.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lotzy.sample.cli.SpringbootApacheCliApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.OutputCaptureRule;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Tests for {@link SpringbootApacheCliApplication}.
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=SpringbootApacheCliApplication.class)
public class TestSpringbootApacheCliApplication {

	@Rule
	public OutputCaptureRule outputCapture = new OutputCaptureRule();

	private String profiles;

	@Before
	public void init() {
		this.profiles = System.getProperty("spring.profiles.active");
	}

	@After
	public void after() {
		if (this.profiles != null) {
			System.setProperty("spring.profiles.active", this.profiles);
		}
		else {
			System.clearProperty("spring.profiles.active");
		}
	}

	/**
	 * Test for default spring boot profile, that is 'test' and no command line arguments
	 * @throws Exception if goes wrong
	 */
	@Test
	public void testDefaultProfileNoCliArgs() throws Exception {
		SpringbootApacheCliApplication.main(new String[0]);
		String output = this.outputCapture.toString();
		assertTrue("Wrong output: " + output, output.contains("usage: java -jar springboot-apache-cli.jar"));
	}

	/**
	 * Test for default spring boot profile, that is 'test' and -g command line argument
	 * @throws Exception if goes wrong
	 */
	@Test
	public void testDefaultProfileCliArgs() throws Exception {
		SpringbootApacheCliApplication.main(new String[] { "-g Mary"});
		String output = this.outputCapture.toString();
		assertTrue("Wrong output: " + output, output.contains("Hello from Lotzy_test to  Mary"));
	}

	/**
	 * Test for 'prod' spring active profile and -g command command line argument
	 * @throws Exception
	 */
	@Test
	public void testProdProfileCommandLineArgs() throws Exception {
		System.setProperty("spring.profiles.active", "prod");
		SpringbootApacheCliApplication.main(new String[] { "-g Mary"});
		String output = this.outputCapture.toString();
		assertTrue("Wrong output: " + output, output.contains("Hello from Lotzy_prod to  Mary"));
	}

}
