pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/sunilt808/Order-management-system.git', branch: 'main'
            }
        }

        stage('Build & Test') {
            steps {
                sh './gradlew clean test build'
            }
        }

        stage('Publish TestNG Report') {
            steps {
                publishTestNGResults testResultsPattern: 'build/test-results/testng-results.xml'
            }
        }
    }

    post {
        success {
            echo '✅ Build and Tests Passed!'
        }
        failure {
            echo '❌ Build or Tests Failed!'
        }
    }
}
