pipeline {
    agent any
    stages {

        stage('docker host connect') {
            agent {
                docker {
                    image 'docker.io/kapil0123/my-dind-with-git:latest'
                    args '--user root -v /var/run/docker.sock:/var/run/docker.sock'
                }
            }
            steps {
                script {
                    sh '''
                        docker ps -a
                        docker images
                    '''
                    git 'https://github.com/Kapil987/test_maven.git'
                }
            }
        }

        stage('Build') {
            agent {
                docker { image 'maven:3.8.1-adoptopenjdk-11' }
            }
            steps {
                // sh 'mvn clean install'
                 sh '''
                 java --version
                 mvn --version
                '''
            }
        }
    }
}
