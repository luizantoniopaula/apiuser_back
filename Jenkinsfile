pipeline {
    agent any
    environment {
        // Diretório de instalação onde o seu build será copiado
        INSTALL_DIR = "/opt/jenkins/apiuser"
        // Nome do arquivo JAR gerado pelo build
        PROJECT_NAME = "apiuser"
    }

    stages {
        stage('Build') {
            steps {
                script {
                    sh 'mvn clean install'  // ou 'mvn clean install', dependendo do gerenciador de build usado
                }
            }
        }
        stage('Identificar JAR') {
            steps {
                // Obter o nome do arquivo jar gerado automaticamente
                script {
                    def files = sh(script: "ls target/${PROJECT_NAME}*.jar", returnStdout: true).trim()
                    // Usa regex para extrair somente o nome do arquivo jar
                    env.JAR_FILE = files.split('\n')[0].trim()
                    echo "Encontrado JAR: ${env.JAR_FILE}"
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
        stage('Configurar systemd') {
            steps {
                // Cria um arquivo de serviço systemd para gerenciar a aplicação
                script {
                    def serviceContent = """
                    [Unit]
                    Description=ApiUser Spring Boot Application
                    After=network.target

                    [Service]
                    User=senai  # Altere para o usuário correto
                    ExecStart=/usr/bin/java -jar ${INSTALL_DIR}/${env.JAR_FILE}
                    Restart=always
                    SuccessExitStatus=143

                    [Install]
                    WantedBy=multi-user.target
                    """
                    writeFile file: '/tmp/apiuser.service', text: serviceContent

                    // Move o arquivo para o diretório do systemd e habilita
                    sh '''
                    sudo mv /tmp/apiuser.service /etc/systemd/system/apiuser.service
                    sudo systemctl daemon-reload
                    sudo systemctl enable apiuser
                    sudo systemctl restart apiuser
                    '''
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
