grails-karma
============
Run javascript tests in the Grails build!

The main goal for Karma is to bring a productive environment for javascript testing. 
The grails-karma plugin integrates this spectacular tool with Grails so unit and functional tests written in javascript can be executed during the build.

## Instalation
First, install Karma as described in the [Karma's official documentation](http://karma-runner.github.io/0.10/index.html). 
Make sure karma is on your path and working properly.
Then execute the following command to install the grails-karma plugin <code>grails install-plugin grails-karma</code>.

When installed, the configuration files for karma will be created under <code>conf/karma</code>. Also, two folders for will be created for your test code: <code>test/js-unit</code> and <code>test/js-functional</code>

## Usage
grails-karma is ready to be executed right after its installation. 
Just put your test files under the <code>test/js-unit/</code> or <code>test/js-functional/</code> directories and execute <code>grails test-app</code>.
You can also use <code>grails test-app karma:unit</code> or <code>grails test-app karma:functional</code> to execute tests form a specific phase.

## Configuration
By default, grails-karma has a functional out of the box configuration for unit and functional tests. 
It uses [Jasmine](http://pivotal.github.io/jasmine/) to execute the tests and [Angular](http://docs.angularjs.org/guide/dev_guide.e2e-testing) to interact with the browser on functional tests.

Any changes in the configuration can be made in the <code>unit.conf.js</code> and <code>functional.conf.js</code> files under <code>conf/karma</code>. 
There is a few things though that must be kept so the plugin can work properly:
* The entires <code>test/js-unit/**/*.js</code> and <code>test/js-functional/**/*.js</code> should't be removed form the list of included files of their respective tests
* The report output .. (TODO)
