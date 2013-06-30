//
// This script is executed by Grails after plugin was installed to project.
// This script is a Gant script so you can use all special variables provided
// by Gant (such as 'baseDir' which points on project base dir). You can
// use 'ant' to access a global instance of AntBuilder
//

def karmaConfDir = new File("${basedir}/grails-app/conf/karma/")
def unitConfFile = new File(karmaConfDir, "unit.conf.js")
def functionalConfFile = new File(karmaConfDir, "functional.conf.js")

def sourceKarmaConfDir = new File("${pluginBasedir}/scripts/karma-conf/")
def sourceUnitConfFile = new File(sourceKarmaConfDir, "unit.conf.js")
def sourceFunctionalConfFile = new File(sourceKarmaConfDir, "functional.conf.js")

if(!karmaConfDir.exists()) {
	println "Creating configuration directory for karma in grails-app/conf/karma/"
	karmaConfDir.mkdirs()
}

if(!unitConfFile.exists()) {
	println "Creating default configuration file for unit tests in grails-app/conf/karma/unit.conf.js"
	ant.copy(file:sourceUnitConfFile.absolutePath, todir: karmaConfDir.absolutePath)
} else {
	println "${karmaConfDir.absolutePath} already exists. Not overwritting it."
}

if(!functionalConfFile.exists()) {
	println "Creating default configuration file for functional tests in grails-app/conf/karma/functional.conf.js"
	ant.copy(file:sourceFunctionalConfFile.absolutePath, todir: karmaConfDir.absolutePath)
} else {
	println "${karmaConfDir.absolutePath} already exists. Not overwritting it."
}


