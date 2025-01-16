pipeline {
    agent none
    stages {
        stage('Clone Repository') {
            agent {
                docker { image 'kapil0123/git' }
            }
            steps {
                git 'https://github.com/Kapil987/test_maven.git'
            }
        }
        stage('Back-end') {
            agent {
                docker { image 'maven:3.8.1-adoptopenjdk-11' }
            }
            steps {
                sh 'mvn clean install'
            }
        }
        stage('Front-end') {
            agent {
                docker { image 'node:16-alpine' }
            }
            steps {
                echo "last stage"
            }
        }
        stage('Deploy') {
            agent {
                docker {
                    image 'docker.io/kapil0123/my-dind-with-git:latest'
                    args '--user root -v /var/run/docker.sock:/var/run/docker.sock'
                }
            }
            steps {
                script {
                    sh '''
                        docker ps -q --filter "ancestor=tomcat:latest" | xargs -r docker stop
                        docker ps -a -q --filter "ancestor=tomcat:latest" | xargs -r docker rm
                    '''
                    sh '''
                        docker run -d -p 8090:8080 --name my-tomcat-container tomcat:latest
                        docker cp target/my-app-1.0-SNAPSHOT.jar my-tomcat-container:/usr/local/tomcat/webapps/
                    '''
                }
            }
        }
    }
}
