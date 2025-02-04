# Jenkinsfiles

This repository contains various Jenkinsfiles demonstrating different use cases and integrations.

## Jenkinsfiles

- **Jenkinsfile1-git**: For basic jenkinsfile understanding with git.
- **Jenkinsfile2-multi-agent**: Simple multiple Docker agent demo, requires the Docker Pipeline plugin.
- **Jenkinsfile3-dind.groovy**: Demonstrates Docker client and host connectivity.
- **Jenkinsfile4-mvn-jdk-git-sonarcloud**: Showcases custom Dockerfile, Maven, and SonarCloud integration.
  - Installed plugin: [Eclipse Temurin installer](https://plugins.jenkins.io/adoptopenjdk/) for multiple JDK versions.
- **Jenkinsfile5-multi-agent**: Demonstrates trigger to downstream job and passing variable to downstream jobs

## Security Aspect

### Docker Warning
> **WARNING!** Your password will be stored unencrypted in `/var/lib/jenkins/.docker/config.json`.

To view the stored password, use:
```sh
sudo cat /var/lib/jenkins/.docker/config.json
