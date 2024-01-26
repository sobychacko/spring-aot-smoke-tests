/*
 * Copyright 2022-2024 the original author or authors.
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

package org.springframework.aot.gradle;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.artifacts.Configuration;
import org.gradle.api.tasks.Sync;
import org.gradle.api.tasks.TaskProvider;

/**
 * Plugin for a project that aggregates the smoke tests to provide status and a CI
 * pipeline.
 *
 * @author Andy Wilkinson
 */
public class AotSmokeTestAggregatorPlugin implements Plugin<Project> {

	@Override
	public void apply(Project project) {
		Configuration workflows = project.getConfigurations().create("workflows");
		TaskProvider<Sync> updateGitHubActionWorkflows = project.getTasks()
			.register("updateGitHubActionWorkflows", Sync.class, (sync) -> {
				sync.into(".github/workflows");
				sync.from(workflows);
				syncFromClasspath("smoke-test-jvm.yml", sync);
				syncFromClasspath("smoke-test-native.yml", sync);
			});
		project.getTasks().register("updateInfrastructure", (task) -> task.dependsOn(updateGitHubActionWorkflows));
	}

	private void syncFromClasspath(String name, Sync sync) {
		sync.from(sync.getProject().getResources().getText().fromUri(getClass().getClassLoader().getResource(name)),
				(spec) -> spec.rename((temp) -> name));
	}

}
