import jenkins.model.*
import hudson.security.*
import jenkins.model.JenkinsLocationConfiguration

def instance = Jenkins.getInstance()
def user = System.getenv("JENKINS_USER") ?: "admin"
def pass = System.getenv("JENKINS_PASS") ?: "admin1234"
def url  = System.getenv("JENKINS_URL") ?: "http://localhost:8080/"

def hudsonRealm = new HudsonPrivateSecurityRealm(false)
if (hudsonRealm.getAllUsers().isEmpty()) {
    hudsonRealm.createAccount(user, pass)
}
instance.setSecurityRealm(hudsonRealm)

def strategy = new FullControlOnceLoggedInAuthorizationStrategy()
strategy.setAllowAnonymousRead(false)
instance.setAuthorizationStrategy(strategy)

instance.setSystemMessage("Gap-Year Jenkins 자동 구성 완료")

// 바로 아래 두 줄이 root url 설정의 정답!
def locationConfig = JenkinsLocationConfiguration.get()
locationConfig.setUrl(url)
locationConfig.save()

instance.save()
