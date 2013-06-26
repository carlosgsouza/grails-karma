loadGrailsKarmaTestTypeClass = { ->
	def doLoad = { -> classLoader.loadClass('grails.plugin.karma.test.GrailsKarmaTestType') }
	try {
	  doLoad()
	} catch (ClassNotFoundException e) {
	  includeTargets << grailsScript("_GrailsCompile")
	  compile()
	  doLoad()
	}
}

eventAllTestsStart = {
	def grailsKarmaTestTypeClass = loadGrailsKarmaTestTypeClass()
	
	if(!phasesToRun.contains("unit")) {
		phasesToRun << "unit"
	}
	
	if(!binding.variables["unitTests"]) {
		binding.variables["unitTests"] = []
	}
	
	binding.variables["unitTests"] << grailsKarmaTestTypeClass.newInstance("unit")
	
	if(!phasesToRun.contains("functional")) {
		phasesToRun << "functional"
	}
	
	if(!binding.variables["functionalTests"]) {
		binding.variables["functionalTests"] = []
	}
	
	binding.variables["functionalTests"] << grailsKarmaTestTypeClass.newInstance("functional")
}




// 4. Create pre and post closures
customTestPhasePreparation = {
    unitTestPhasePreparation()
}
customTestPhaseCleanUp = {
    unitTestPhaseCleanUp()
}