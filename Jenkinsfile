pipeline {
    agent any
    environment {
        // Diretório de instalação onde o seu build será copiado
        INSTALL_DIR = "/opt/jenkins/apiuser"
        // Nome do arquivo JAR gerado pelo build
        JAR_FILE = "apiuser-1.0.0.jar"
    }

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
                    def mvn = tool 'Maven3_9_9';
                    withSonarQubeEnv('QubeServer') {
                        sh "mvn clean verify sonar:sonar -Dsonar.projectKey=apiuser_back"
                    }
                }
            }
        }
        stage('Deploy') {
            steps {
                // Criar o diretório de instalação, se não existir
                sh """
                mkdir -p ${INSTALL_DIR}
                """
                // Copiar o jar para o diretório de instalação
                sh """
                cp target/${JAR_FILE} ${INSTALL_DIR}/
                """
            }
        }
        stage('Iniciar Aplicação') {
            steps {
                // Parar um possível processo em execução (opcional, dependendo do seu cenário)
                sh '''
                PIDS=$(ps -ef | grep ${INSTALL_DIR}/${JAR_FILE} | grep -v grep | awk '{print $2}')
                if [ -n "$PIDS" ]; then
                    echo "Parando processo existente..."
                    kill -9 $PIDS
                fi
                '''

                // Iniciar a aplicação com o comando java -jar
                sh '''
                nohup java -jar ${INSTALL_DIR}/${JAR_FILE} > ${INSTALL_DIR}/apiuser.log 2>&1 &
                echo $! > ${INSTALL_DIR}/apiuser.pid
                '''
            }
        }
        
        //stage('Deploy') {
        //    steps {
        //        script {
        //              // executar Script instala.sh a ser criado...
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
