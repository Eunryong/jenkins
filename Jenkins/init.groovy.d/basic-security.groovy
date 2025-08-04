import jenkins.model.*
import hudson.security.*
import jenkins.model.JenkinsLocationConfiguration

def instance = Jenkins.getInstance()
def user = System.getenv("JENKINS_USER") ?: "admin"
def pass = System.getenv("JENKINS_PASS") ?: "1234"
def url  = System.getenv("JENKINS_URL") ?: "http://localhost:8080/"

def hudsonRealm = new HudsonPrivateSecurityRealm(false)
if (hudsonRealm.getAllUsers().isEmpty()) {
    hudsonRealm.createAccount(user, pass)
}
instance.setSecurityRealm(hudsonRealm)

def strategy = new FullControlOnceLoggedInAuthorizationStrategy()
strategy.setAllowAnonymousRead(false)
instance.setAuthorizationStrategy(strategy)

instance.setSystemMessage("Jenkins 자동 구성 완료")

def locationConfig = JenkinsLocationConfiguration.get()
locationConfig.setUrl(url)
locationConfig.save()

instance.save()
