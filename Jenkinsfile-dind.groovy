pipeline {
    agent none
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
                }
            }
        }
    }
}
