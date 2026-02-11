pipeline {
    agent any

    stages {

        stage('Build & Test') {
            steps {
                bat 'gradlew clean test build'
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
