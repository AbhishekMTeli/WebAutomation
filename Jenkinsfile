pipeline {
  agent any

  stages {
    stage('Build') {
      steps {
        script {
          def jdkHome = tool 'Java 21'
          def mvnHome = tool 'Maven 3.9.11'
          withEnv(["PATH+JAVA=${jdkHome}/bin", "PATH+MAVEN=${mvnHome}/bin"]) {
            echo 'Building...'
            sh 'mvn clean compile'
          }
        }
      }
    }
    stage('Test') {
      steps {
        script {
          def jdkHome = tool 'Java 21'
          def mvnHome = tool 'Maven 3.9.11'
          withEnv(["PATH+JAVA=${jdkHome}/bin", "PATH+MAVEN=${mvnHome}/bin"]) {
            echo 'Testing...'
            sh 'mvn test'
          }
        }
      }
    }
  }
}