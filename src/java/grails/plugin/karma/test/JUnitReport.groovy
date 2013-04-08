package grails.plugin.karma.test

class JUnitReport {

	def report
	int tests
	int failures
	int errors
	
	public JUnitReport(reportFile) {
		this.report = new XmlSlurper().parse(reportFile)
		
		this.tests = 0
		this.failures = 0
		this.errors = 0
		
		report.testsuite.each { testsuite ->
			this.tests += Integer.valueOf testsuite.@tests.toString()
			this.failures += Integer.valueOf testsuite.@failures.toString()
			this.errors += Integer.valueOf testsuite.@errors.toString()
		}
	}
}
