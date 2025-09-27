pipeline {
  agent any

  stages {
    stage('Build') {
      steps {
        script {
          def mvnHome = tool 'Maven 3.9.11' // Use the exact name as in Maven installations
          withEnv(["PATH+MAVEN=${mvnHome}/bin"]) {
            echo 'Building...'
            sh 'mvn clean compile'
          }
        }
      }
    }
    stage('Test') {
      steps {
        script {
          def mvnHome = tool 'Maven 3.9.11'
          withEnv(["PATH+MAVEN=${mvnHome}/bin"]) {
            echo 'Testing...'
            sh 'mvn test'
          }
        }
      }
    }
  }
}
