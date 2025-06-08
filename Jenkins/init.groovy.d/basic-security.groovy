import jenkins.model.*
import hudson.security.*

def instance = Jenkins.getInstance()
def user = System.getenv("JENKINS_USER")
def pass = System.getenv("JENKINS_PASS")

def hudsonRealm = new HudsonPrivateSecurityRealm(false)
hudsonRealm.createAccount(user, pass)
instance.setSecurityRealm(hudsonRealm)

def strategy = new FullControlOnceLoggedInAuthorizationStrategy()
strategy.setAllowAnonymousRead(false)
instance.setAuthorizationStrategy(strategy)

// Jenkins URL 설정
instance.setSystemMessage("Gap-Year Jenkins 자동 구성 완료")
instance.setRootUrl(System.getenv("JENKINS_URL"))
instance.save()