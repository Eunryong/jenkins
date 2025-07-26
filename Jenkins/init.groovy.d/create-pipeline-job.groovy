import jenkins.model.*
import org.jenkinsci.plugins.workflow.job.WorkflowJob
import org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition
import hudson.plugins.git.*
import com.cloudbees.plugins.credentials.*
import hudson.plugins.git.extensions.*
import hudson.plugins.git.extensions.impl.* // (선택 사항)
import com.cloudbees.plugins.credentials.domains.*

def jenkins = Jenkins.instance
def jobName = "nest-server"

def repoUrl = System.getenv("REPO_URL") ?: "https://github.com/Eunryong/invest_friends.git"

if (jenkins.getItem(jobName) == null) {
    def job = new WorkflowJob(jenkins, jobName)

    def userRemoteConfig = new UserRemoteConfig(
        repoUrl,
        null,
        null,
        "github-token"  // ✅ credentials ID
    )

    def scm = new GitSCM(
        Collections.singletonList(userRemoteConfig),
        Collections.singletonList(new BranchSpec("*/main")),
        false,
        Collections.<SubmoduleConfig>emptyList(),
        null,
        null,
        Collections.<GitSCMExtension>emptyList() // ✅ 이 타입을 쓰려면 import 필요
    )

    def definition = new CpsScmFlowDefinition(scm, "Jenkinsfile")
    definition.setLightweight(true)
    job.definition = definition

    jenkins.add(job, jobName)
    job.save()
}
