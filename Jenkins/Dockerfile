FROM jenkins/jenkins:lts

# 플러그인 설치
COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN jenkins-plugin-cli --plugin-file /usr/share/jenkins/ref/plugins.txt

# 초기화 스크립트 복사
COPY init.groovy.d/ /var/jenkins_home/init.groovy.d/

# Job DSL 스크립트 복사
USER root

RUN chown -R jenkins:jenkins /var/jenkins_home/init.groovy.d

USER jenkins