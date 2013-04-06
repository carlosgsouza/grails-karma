includeTargets << grailsScript("_GrailsInit")
includeTool << gant.tools.Execute

target(main: "The description of the script goes here!") {
	def karmaCmd = "C:/Users/carlosags/AppData/Roaming/npm/karma.cmd"
	def confPath = "C:/opt/workspace-gg/cmaas-portal/web-app/testacular.conf.js"
	
	execute.executable "$karmaCmd start $confPath"
}

setDefaultTarget(main)
