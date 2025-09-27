pipeline {
  agent any

  stages {
    stage('Build') {
      steps {
        script {
          def mvnHome = tool 'Maven3' // Replace 'Maven3' with your Maven tool name in Jenkins Global Tool Configuration
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
          def mvnHome = tool 'Maven3'
          withEnv(["PATH+MAVEN=${mvnHome}/bin"]) {
            echo 'Testing...'
            sh 'mvn test'
          }
        }
      }
    }
  }
}
