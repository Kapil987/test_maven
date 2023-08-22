pipeline {
    // agent any
    agent { label 'algoworks-dev-server' }
    // environment { M2_HOME='/opt/apache-maven-3.9.4' }
        environment {
        M2_HOME='/opt/apache-maven-3.9.4'
        PATH="$M2_HOME/bin:$PATH"
        // Add other environment variables as needed
    }
    stages {
	    stage('Git scm checkout') {
            steps {
                git branch: 'master',
		url: 'https://github.com/Kapil987/test_maven.git'
            }
        }
		
        stage('mvn clean') {
            steps {
//                sh "rm -rf test_maven"
//                sh "git clone https://github.com/Kapil987/test_maven.git"
//                sh "mvn clean -f test_maven/pom.xml"
                sh "mvn clean"

            }
        }
        stage('Test') {
            steps {
                sh "mvn test"

            }
        }
        stage('Deploy') {
            steps {
                sh "mvn package"

            }
        }
    }
}
