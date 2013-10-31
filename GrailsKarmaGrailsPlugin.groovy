class GrailsKarmaGrailsPlugin {
    def version = "0.1.22"
    def grailsVersion = "1.3 > *"
    def dependsOn = [:]
    def pluginExcludes = ["grails-app/views/error.gsp"]
    def title = "Grails Karma Plugin"
    def author = "Carlos Souza"
    def authorEmail = "carlosgsouza@gmail.com"
    def description = '''\
Run Javascript tests on the Grails build with Karma
'''
    def documentation = "http://carlosgsouza.github.io/grails-karma/"

    def license = "APACHE"
    def developers = [ [ name: "Carlos Souza", email: "carlosgsouza@gmail.com" ], [ name: "Daniel Fernandes", email: "danielpsf@gmail.com" ] ]
    def issueManagement = [ system: "GitHub", url: "https://github.com/carlosgsouza/grails-karma/issues" ]
	def scm = [ url: "https://github.com/carlosgsouza/grails-karma" ]

    def doWithWebDescriptor = { xml ->
        // TODO Implement additions to web.xml (optional), this event occurs before
    }

    def doWithSpring = {
        // TODO Implement runtime spring config (optional)
    }

    def doWithDynamicMethods = { ctx ->
        // TODO Implement registering dynamic methods to classes (optional)
    }

    def doWithApplicationContext = { applicationContext ->
        // TODO Implement post initialization spring config (optional)
    }

    def onChange = { event ->
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    def onConfigChange = { event ->
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }

    def onShutdown = { event ->
        // TODO Implement code that is executed when the application shuts down (optional)
    }
}
