pipeline {
    agent any
    stages {
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
                sh "mvn test -f test_maven"

            }
        }
        stage('Deploy') {
            steps {
                sh "mvn package -f test_maven"

            }
        }
    }
}
