# Jenkins and its Integration with others 📘

This repository contains various **Jenkinsfiles** demonstrating different use cases and integrations, as well as instructions for setting up **Nexus Repository Manager** and configuring Maven for deployments.

---

## Jenkinsfiles 📝

- **Jenkinsfile1-git**: For basic Jenkinsfile understanding with Git.
- **Jenkinsfile2-multi-agent**: Simple multiple Docker agent demo (requires the Docker Pipeline plugin).
- **Jenkinsfile3-dind.groovy**: Demonstrates Docker client and host connectivity.
- **Jenkinsfile4-mvn-jdk-git-sonarcloud**: Showcases custom Dockerfile, Maven, and SonarCloud integration.
  - Installed plugin: [Eclipse Temurin Installer](https://plugins.jenkins.io/adoptopenjdk/) for multiple JDK versions.
- **Jenkinsfile5-multi-agent**: Demonstrates triggering a downstream job and passing variables to downstream jobs.

---

## Nexus Repository Manager Setup 📦

### Installation Steps

1. **Download Nexus**:

   ```sh
   wget https://download.sonatype.com/nexus/3/latest-unix.tar.gz
   tar -xf latest-unix.tar.gz
   ```

2. **Start Nexus**:

   ```sh
   cd nexus-3.76.1-01/bin
   chmod +x nexus
   ./nexus start
   ```

3. **Access Nexus Web UI**:

   - Open a web browser and navigate to `http://localhost:8081`.

---

## Configuring Maven for Nexus Deployment 🚀

### POM File Configuration

In your `pom.xml` file, include the following information:

```xml
<project>
  <properties>
    <!-- CHANGE HERE to your team's Nexus server -->
    <nexus.url>http://54.86.68.166:8081</nexus.url>  
    
    <!-- CHANGE HERE to your team's Nexus repository IDs -->
    <release.repo.id>maven-releases</release.repo.id>
    <snapshot.repo.id>maven-snapshots</snapshot.repo.id>
  </properties>

  <distributionManagement>
    <repository>
      <id>${release.repo.id}</id>  
      <url>${nexus.url}/repository/${release.repo.id}/</url>
    </repository>
    <snapshotRepository>
      <id>${snapshot.repo.id}</id>  
      <url>${nexus.url}/repository/${snapshot.repo.id}/</url>
    </snapshotRepository>
  </distributionManagement>

  <repositories>
    <repository>
      <id>${release.repo.id}</id>
      <name>${release.repo.id}</name>
      <url>${nexus.url}/repository/${release.repo.id}/</url>
    </repository>
    <repository>
      <id>${snapshot.repo.id}</id>
      <name>${snapshot.repo.id}</name>
      <url>${nexus.url}/repository/${snapshot.repo.id}/</url>
    </repository>
  </repositories>
</project>
```

### Additional Configuration

```xml
<distributionManagement>
  <repository>
    <id>maven-releases</id>
    <!-- CHANGE HERE to your team's Nexus server -->
    <url>http://54.86.68.166:8081/repository/maven-releases/</url>
  </repository>
  <snapshotRepository>
    <id>maven-snapshots</id>
    <!-- CHANGE HERE to your team's Nexus server -->
    <url>http://54.86.68.166:8081/repository/maven-releases/</url>
  </snapshotRepository>
</distributionManagement>
```

```xml
<repository>
  <id>maven-releases</id>
  <name>maven-releases</name>
  <url>http://54.86.68.166:8081/repository/maven-releases/</url>
</repository>
```

---

## Nexus Authentication using `settings.xml` 🔑

To authenticate with Nexus using a `settings.xml` file, include the following configuration:

```xml
<settings>
  <servers>
    <server>
      <id>nexus-releases</id>
      <username>your-username</username>
      <password>your-password</password>
    </server>
    <server>
      <id>nexus-snapshots</id>
      <username>your-username</username>
      <password>your-password</password>
    </server>
  </servers>
</settings>
```

Replace `your-username` and `your-password` with your Nexus credentials. Ensure this `settings.xml` file is placed in the appropriate location on your system.

---

## Security Aspect 🔐

### Docker Warning

> **WARNING!** Your password will be stored unencrypted in `/var/lib/jenkins/.docker/config.json`.

To view the stored password:

```sh
sudo cat /var/lib/jenkins/.docker/config.json
```

---

## Extra 🌟

### Using Maven Profiles

You can use Maven profiles to define different sets of properties for different environments (e.g., development, testing, production).

```xml
<profiles>
  <profile>
    <id>dev</id>
    <properties>
      <nexus.url>http://dev-nexus:8081</nexus.url>
    </properties>
  </profile>
  <profile>
    <id>prod</id>
    <properties>
      <nexus.url>http://prod-nexus:8081</nexus.url>
    </properties>
  </profile>
</profiles>
```

Activate a profile using the `-P` option:

```sh
mvn deploy -P prod
```

This will use the `nexus.url` defined in the `prod` profile.

---

## Running Nexus as a Service ⚙️

### Step 1: Create a Systemd Service File

Create a new service file for Nexus:

```sh
sudo nano /etc/systemd/system/nexus.service
```

### Step 2: Add Service Configuration

Paste the following configuration into the file:

```ini
[Unit]
Description=Nexus Repository Manager
After=network.target

[Service]
Type=forking
LimitNOFILE=65536
User=ubuntu
Group=ubuntu
ExecStart=/home/ubuntu/nexus/nexus-3.76.1-01/bin/nexus start
ExecStop=/home/ubuntu/nexus/nexus-3.76.1-01/bin/nexus stop
ExecReload=/home/ubuntu/nexus/nexus-3.76.1-01/bin/nexus restart
Restart=on-failure

[Install]
WantedBy=multi-user.target
```

### Step 3: Reload Systemd

Reload systemd to apply the new service file:

```sh
sudo systemctl daemon-reload
```

### Step 4: Enable and Start Nexus Service

Enable the Nexus service to start on boot and then start the service:

```sh
sudo systemctl enable nexus
sudo systemctl start nexus
```

### Step 5: Check Service Status

Check the status of the Nexus service to ensure it is running correctly:

```sh
sudo systemctl status nexus
```

Now you can start and stop Nexus as a service.

---

## Additional Information ℹ️

For detailed explanations and configurations, refer to the comments in the provided code snippets. Ensure that you replace placeholder values (like IP addresses and usernames) with your actual server details.

---

Feel free to explore and adapt the Jenkinsfiles and Nexus configurations to suit your project's needs!