/*
 * Copyright 2012-2013 the original author or authors.
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

package org.lotzy.sample.cli;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.lotzy.sample.cli.service.HelloWorldService;

/**
 * <pre>
 * Title: SpringbootApacheCliApplication class
 * Description: Spring Boot Main class illustrating the use of Apache commons-cli library to parse command line arguments and using them in your code
 * </pre>
 *
 * @author Lotzy
 * @version 1.0
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class SpringbootApacheCliApplication implements CommandLineRunner {

	@Autowired
	private HelloWorldService helloWorldService;

	@Override
	public void run(String... args) throws Exception {

		Option help = Option.builder("h").required(false).hasArg(false).longOpt("help").build();
		Option guest = Option.builder("g").required(false).hasArg(true).argName("Guest user").longOpt("guest").build();

		Options options = new Options();
		options.addOption(help);
		options.addOption(guest);

		CommandLineParser parser = new DefaultParser();
		CommandLine line = parser.parse(options, args);

		if (line.hasOption("h") || args.length == 0) {

			HelpFormatter formatter = new HelpFormatter();
			// automatically generate the help statement
			formatter.printHelp("java -jar springboot-apache-cli.jar", options, true);
			return;
		}

		System.out.println(this.helloWorldService.getHelloMessage(line.getOptionValue("g")));
	}

	public static void main(String[] args) throws Exception {

		SpringApplication.run(SpringbootApacheCliApplication.class, args);
	}
}
