pipeline {
    agent any

    tools {
        jdk 'jdk21'
    }

    stages {
        stage('Build & Test') {
            steps {
                bat 'gradlew clean test build'
            }
        }

        stage('Publish Test Results') {
            steps {
                junit 'build/test-results/test/*.xml'
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
