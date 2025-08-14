def call(Map config, String imageName) {
    stage('Build and Push Docker Image') {
        docker.withRegistry("https://${config.dockerRegistry}", config.dockerCreds) {
            sh "docker build -t ${imageName} ."
            def dockerImage = docker.image(imageName)
            dockerImage.push()
        }
    }
}
