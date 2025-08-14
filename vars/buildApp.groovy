def call(Map config) {
    stage('Checkout Code') {
        checkout([
            $class: 'GitSCM',
            branches: [[name: "refs/heads/${config.gitBranch}"]],
            userRemoteConfigs: [[
                url: 'https://github.com/supun5687/spring-hello-API-unit-tests.git',
                credentialsId: config.gitCreds
            ]]
        ])
    }

    stage('Build & Unit Tests') {
        sh 'ls -ltr'
        sh 'mvn clean package'
        junit '**/target/surefire-reports/*.xml'
    }
}


