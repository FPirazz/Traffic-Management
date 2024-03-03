# Versioning and Licensing

## Licensing

For this project, we have opted to use the [MIT License](https://opensource.org/licenses/MIT). The MIT License is known for its permissive nature, allowing users to freely use, modify, and distribute the software, with minimal restrictions. This choice aligns with our commitment to an open and collaborative development environment.

## Versioning

To manage versioning effectively, we have incorporated the Gradle plugin **org.danilopianini.git-sensitive-semantic-versioning** into our project. This plugin, currently at version 0.1.0, provides a sophisticated approach to versioning that takes into account the sensitivity of the changes made in the Git repository.

### Features of the Plugin:

- **Semantic Versioning:** The plugin follows the principles of semantic versioning (SemVer), ensuring version numbers reflect the nature of changes made.
  
- **Git Sensitivity:** By analyzing Git commit messages, the plugin intelligently determines the impact of changes on versioning. This sensitivity allows for a more accurate versioning strategy.

- **Automated Version Bumping:** With each commit, the plugin automatically bumps the version based on the identified changes. This automation streamlines the versioning process, reducing the burden on developers.

- **Integration with Gradle:** Seamless integration into the Gradle build system facilitates easy adoption and incorporation into the overall development workflow.

By leveraging the capabilities of this Gradle plugin, we aim to maintain a clear and meaningful version history that reflects the evolution of our project in a systematic and informative manner.
