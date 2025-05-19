pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                script {
                    sh 'mvn clean install'  // ou 'mvn clean install', dependendo do gerenciador de build usado
                }
            }
        }
        
        stage('SonarQube Analysis') {

            steps {
                script {
                    def mvn = tool 'Default Maven';
                    withSonarQubeEnv('SonarQube Server Name') {
                        sh "${mvn}/bin/mvn clean verify sonar:sonar -Dsonar.projectKey=apiuser_back"
                    }
                }
            }
        }
        
        //stage('Deploy') {
        //    steps {
        //        script {
        //            // executar Script instala.sh a ser criado...
        //        }
        //    }
        // }
    }
    
    post {
        always {
            script {
                cleanWs()
            }
        }
    }
}
