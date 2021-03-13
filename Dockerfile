FROM jenkins/jenkins:2.222.4-lts-slim
USER jenkins

COPY --chown=jenkins:jenkins jenkins.yaml /var/jenkins_home/casc_configs/jenkins.yaml
COPY --chown=jenkins:jenkins allow-script-run-jobdsl.groovy /usr/share/jenkins/ref/init.groovy.d/allow-script-run-jobdsl.groovy
COPY --chown=jenkins:jenkins plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt
ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false
ENV CASC_JENKINS_CONFIG=/var/jenkins_home/casc_configs