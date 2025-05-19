pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                script {
                    sh './mvn clean install'  // ou 'mvn clean install', dependendo do gerenciador de build usado
                }
            }
        }
        
        stage('SonarQube Analysis') {
            steps {
                script {
                    withSonarQubeEnv('SonarQube Server Name') {
                        sh './mvn sonarqube' // ou a execução correspondente para Maven
                    }
                }
            }
        }
        
        stage('Deploy') {
            steps {
                script {
                    // executar Script instala.sh a ser criado...
                }
            }
        }
    }
    
    post {
        always {
            script {
                cleanWs()
            }
        }
    }
}
