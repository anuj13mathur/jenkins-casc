# jenkins-casc
Jenkins setup using CasC plugin

docker build -t stateless-jenkins:<tag> .
docker run -p 8080:8080 --rm -e JENKINS_USER_ADMIN_PASSWORD=<password> -e JENKINS_USER_DEV_PASSWORD=<password> \
stateless-jenkins:<tag>
