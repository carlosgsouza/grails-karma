import grails.plugin.karma.test.GrailsKarmaTestType
 
eventAllTestsStart = {
	if(!phasesToRun.contains("unit")) {
		phasesToRun << "unit"
	}
	
	if(!binding.variables["unitTests"]) {
		binding.variables["unitTests"] = []
	}
	
	binding.variables["unitTests"] << new GrailsKarmaTestType()
}



// 4. Create pre and post closures
customTestPhasePreparation = {
    unitTestPhasePreparation()
}
customTestPhaseCleanUp = {
    unitTestPhaseCleanUp()
}