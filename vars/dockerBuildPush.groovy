def call(Map config, String imageName) {
    echo "=== START: Build and Push Docker Image Stage ==="
    echo "Docker Registry: ${config.dockerRegistry}"
    echo "Docker Credentials ID: ${config.dockerCreds}"
    echo "Image Name: ${imageName}"

    stage('Build and Push Docker Image') {
        echo "[Docker] Logging into registry: ${config.dockerRegistry}"
        docker.withRegistry("https://${config.dockerRegistry}", config.dockerCreds) {
            echo "[Docker] Building image: ${imageName}"
            sh "docker build -t ${imageName} ."

            echo "[Docker] Creating image handle for push"
            def dockerImage = docker.image(imageName)

            echo "[Docker] Pushing image: ${imageName}"
            dockerImage.push()

            echo "[Docker] Image push completed successfully."
        }
    }

    echo "=== END: Build and Push Docker Image Stage ==="
}




// def call(Map config, String imageName) {
//     stage('Build and Push Docker Image') {
//         docker.withRegistry("https://${config.dockerRegistry}", config.dockerCreds) {
//             sh "docker build -t ${imageName} ."
//             def dockerImage = docker.image(imageName)
//             dockerImage.push()
//         }
//     }
// }
