import grails.plugin.karma.test.GrailsKarmaTestType
 
eventAllTestsStart = {
	if(!phasesToRun.contains("unit")) {
		phasesToRun << "unit"
	}
	
	if(!binding.variables["unitTests"]) {
		binding.variables["unitTests"] = []
	}
	
	binding.variables["unitTests"] << new GrailsKarmaTestType("unit")
	
	if(!phasesToRun.contains("functional")) {
		phasesToRun << "functional"
	}
	
	if(!binding.variables["functionalTests"]) {
		binding.variables["functionalTests"] = []
	}
	
	binding.variables["functionalTests"] << new GrailsKarmaTestType("functional")
}




// 4. Create pre and post closures
customTestPhasePreparation = {
    unitTestPhasePreparation()
}
customTestPhaseCleanUp = {
    unitTestPhaseCleanUp()
}