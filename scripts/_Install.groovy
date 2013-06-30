mkdir(new File("${basedir}/grails-app/conf/karma/"))

createConfiguration("unit", basedir, pluginBasedir)
createConfiguration("functional", basedir, pluginBasedir)

def createConfiguration(phase, basedir, pluginBasedir) {
	def karmaConfDir = new File("${basedir}/grails-app/conf/karma/")
	def sourceKarmaConfDir = new File("${pluginBasedir}/src/karma-conf/")
	
	def configFileName = "${phase}.conf.js"
	
	def karmaConfigFile = new File(karmaConfDir, configFileName)
	if(!karmaConfigFile.exists()) {
		karmaConfigFile << new File(sourceKarmaConfDir, configFileName).text
		event "CreatedFile", [karmaConfigFile.absolutePath]
	} 
	
	mkdir(new File("${basedir}/test/js-$phase"))
}

def mkdir(dir) {
	if(!dir.exists()) {
		event "CreatedFile", [dir.absolutePath]
		dir.mkdirs()
	} 
}