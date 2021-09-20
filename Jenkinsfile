pipeline {
    agent {
        label 'CI3-Worker-01'
    }

    stages {
        stage ('Build and test') {
            steps {
                sh './gradlew clean test'
            }
        }
    }
}

