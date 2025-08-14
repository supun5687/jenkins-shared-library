def call(Map config) {
    echo "=== START: Shared Library Execution ==="
    echo "Received Config: ${config}"
    echo "Branch to checkout: ${config.gitBranch}"
    echo "Git credentials ID: ${config.gitCreds}"

    stage('Checkout Code') {
        echo "[Checkout Code] Checking out branch '${config.gitBranch}' from repository"
        checkout([
            $class: 'GitSCM',
            branches: [[name: "refs/heads/${config.gitBranch}"]],
            userRemoteConfigs: [[
                url: 'https://github.com/supun5687/spring-hello-API-unit-tests.git',
                credentialsId: config.gitCreds
            ]]
        ])
        echo "[Checkout Code] Repository checkout completed successfully."
    }

    stage('Build & Unit Tests') {
        echo "[Build & Unit Tests] Listing project files..."
        sh 'ls -ltr'

        echo "[Build & Unit Tests] Running Maven build..."
        sh 'mvn clean package'

        echo "[Build & Unit Tests] Publishing JUnit test results..."
        junit '**/target/surefire-reports/*.xml'

        echo "[Build & Unit Tests] Build and tests completed successfully."
    }

    echo "=== END: Shared Library Execution ==="
}




// def call(Map config) {
//     stage('Checkout Code') {
//         checkout([
//             $class: 'GitSCM',
//             branches: [[name: "refs/heads/${config.gitBranch}"]],
//             userRemoteConfigs: [[
//                 url: 'https://github.com/supun5687/spring-hello-API-unit-tests.git',
//                 credentialsId: config.gitCreds
//             ]]
//         ])
//     }

//     stage('Build & Unit Tests') {
//         sh 'ls -ltr'
//         sh 'mvn clean package'
//         junit '**/target/surefire-reports/*.xml'
//     }
// }


