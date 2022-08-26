/*
 * Copyright 2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.security.oauth2authorizationserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * OAuth Authorization Server Application.
 *
 * @author Steve Riesenberg
 */
@SpringBootApplication
public class OAuth2AuthorizationServerApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(OAuth2AuthorizationServerApplication.class, args);
		Thread.currentThread().join(); // To be able to measure memory consumption
	}

}
