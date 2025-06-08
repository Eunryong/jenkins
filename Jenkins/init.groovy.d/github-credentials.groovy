import jenkins.model.Jenkins
import com.cloudbees.plugins.credentials.impl.*
import com.cloudbees.plugins.credentials.*
import com.cloudbees.plugins.credentials.domains.*
import hudson.util.Secret

def user = System.getenv("GITHUB_USER")
def token = System.getenv("GITHUB_TOKEN")

println "--> GITHUB_USER = ${user}"
println "--> GITHUB_TOKEN exists = ${token != null}"

if (user && token) {
    def store = Jenkins.instance.getExtensionList(
      'com.cloudbees.plugins.credentials.SystemCredentialsProvider'
    )[0].getStore()

    println "--> Adding GitHub credentials for '${user}'"

    def creds = new UsernamePasswordCredentialsImpl(
        CredentialsScope.GLOBAL,
        "github-token", "GitHub Personal Access Token",
        user, token
    )
    store.addCredentials(Domain.global(), creds)
} else {
    println "Error: GITHUB_USER or GITHUB_TOKEN is missing. Skipping GitHub credential creation."
}