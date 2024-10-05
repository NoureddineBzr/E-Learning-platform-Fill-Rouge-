pipeline {
    agent any

    tools {
        maven 'mvn'
    }

    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerHubCredentials')
        SONARQUBE_TOKEN = 'squ_57e48b587170f9011b19918d492a3f03d2b9e7b3'
        SONARQUBE_SERVER = 'http://localhost:9000'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/NoureddineBzr/E-Learning-platform-Fill-Rouge-.git'
            }
        }

        stage('Build & Test filRouge') {
            steps {
                dir('BackEnd') {
                    withMaven(maven: 'mvn') {
                        bat 'mvn clean install'
                    }
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                script {
                    def scannerHome = tool 'SonarQubeScanner'
                    dir('BackEnd') {
                        bat "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=BackEnd -Dsonar.sources=. -Dsonar.host.url=${SONARQUBE_SERVER} -Dsonar.login=${SONARQUBE_TOKEN} -Dsonar.java.binaries=target/classes"
                    }
                }
            }
        }

        stage('Build Docker Image & Push') {
            steps {
                dir('BackEnd') {
                    script {
                        try {
                            bat 'docker context use default'

                            def imageTag = env.TAG_VERSION ?: 'latest'
                            def dockerImage = docker.build("noureddinebzr/filrouge:${imageTag}")

                            docker.withRegistry('https://index.docker.io/v1/', 'dockerHubCredentials') {
                                dockerImage.push()
                            }
                        } catch (Exception e) {
                            error "Docker build or push failed: ${e.message}"
                        }
                    }
                }
            }
        }

        stage('Deploy with Docker Compose') {
            steps {
                dir('BackEnd') {
                    bat 'docker-compose up -d'
                }
            }
        }
    }
}