import jenkins.model.*
import hudson.tools.*
import jenkins.plugins.nodejs.tools.*

def nodejsName = 'nodejs-24'
def nodeVersion = '24.4.1'

def installers = [ new NodeJSInstaller(nodeVersion, "", 72) ]
def props = [ new InstallSourceProperty(installers) ]
def installations = [ new NodeJSInstallation(nodejsName, "", props) ]

def instance = Jenkins.getInstance()
def desc = instance.getDescriptor(jenkins.plugins.nodejs.tools.NodeJSInstallation)

desc.setInstallations(installations as NodeJSInstallation[])
desc.save()

println "--> NodeJS Tool 자동 등록 완료: ${nodejsName} (${nodeVersion})"