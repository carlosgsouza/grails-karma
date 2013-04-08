package grails.plugin.karma.test

class CommandRunner {
	
	def execute(executablePath) {
		def process = executablePath.execute()
		process.consumeProcessOutput(System.out, System.err)
		process.waitFor()
	}
	
	def execute(executablePath, String... args) {
		def joinedArgs = args.join(" ")
		def cmd = " $executablePath $joinedArgs"
		
		execute(cmd)
	}
}
