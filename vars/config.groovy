def getConfig(String branch){
    switch(branch) {
        case 'main':
            return [
                gitBranch: 'main',
                gitCreds: 'git-https-creds',
                dockerRegistry: 'index.docker.io/v1/',
                dockerCreds: 'docker-cred-prod'
            ]

        case 'dev':
            return [
                gitBranch: 'dev',
                gitCreds: 'git-https-creds',
                dockerRegistry: '',
                dockerCreds: ''
            ] 

        case 'qa':
            return [
                gitBranch: 'qa',
                gitCreds: 'git-https-creds',
                dockerRegistry: '',
                dockerCreds: ''
            ]           
        default:
            error "No config found for branch: ${branch}"

    }

}