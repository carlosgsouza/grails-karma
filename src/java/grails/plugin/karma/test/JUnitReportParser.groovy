package grails.plugin.karma.test

class JUnitReportParser {

	def parse(reportFile) {
		def report = new XmlSlurper().parse(reportFile)
		
		def tests = 0
		def failures = 0
		def errors = 0
		
		report.testsuite.each { testsuite ->
			tests += Integer.valueOf testsuite.@tests.toString()
			failures += Integer.valueOf testsuite.@failures.toString()
			errors += Integer.valueOf testsuite.@errors.toString()
		}
		
		new GrailsKarmaTestTypeResult(failCount: failures + errors, passCount: tests - failures - errors)
	}
}
