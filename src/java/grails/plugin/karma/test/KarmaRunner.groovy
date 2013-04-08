package grails.plugin.karma.test

class KarmaRunner {
	String pathToKarma
	CommandRunner commandRunner
	
	public KarmaRunner(String pathToKarma, CommandRunner commandRunner) {
		this.pathToKarma = pathToKarma
		this.commandRunner = commandRunner
	}
	
	public KarmaRunner(String pathToKarma) {
		this.pathToKarma = pathToKarma
		this.commandRunner = new CommandRunner()
	}
	
	def execute(pathToConfigFile) {
		commandRunner.execute(pathToKarma, "start", pathToConfigFile)
	}
}
