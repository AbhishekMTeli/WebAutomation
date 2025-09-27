pipeline {
  agent any

  stages {
    stage('Build') {
      steps {
        echo 'Building...'
        sh 'mvn clean compile'
      }
    }
    stage('Test') {
      steps {
        echo 'Testing...'
        sh 'mvn test'
      }
    }
  }
}
