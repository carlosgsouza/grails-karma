grails-karma
============
Run javascript tests in the Grails build!

The main goal for Karma is to bring a productive environment for javascript testing. 
The grails-karma plugin integrates this spectacular tool with Grails so unit and functional tests written in javascript can be executed during the build.

## Instalation
First, install Karma as described in the [Karma's official documentation](http://karma-runner.github.io/0.10/index.html). 
Make sure karma is on your path and working properly.
Then execute the following command to install the grails-karma plugin <code>grails install-plugin grails-karma</code>.

## Usage
grails-karma is ready to be executed right after its installation. 
Just put your test files under the <code>test/js-unit/</code> or <code>test/js-functional/</code> directories and execute <code>grails test-app</code>.
You can also use <code>grails test-app karma:unit</code> or <code>grails test-app karma:functional</code> to execute tests form a specific phase.

*During development, I recommend using karma directly form the command line since it will execute tests for you as you change your files.*

## Configuration
The default karma configuration uses [Jasmine](http://pivotal.github.io/jasmine/) as the testing framework and [Angular](http://docs.angularjs.org/guide/dev_guide.e2e-testing) to interact with the browser on functional tests.
You can change any settings in the <code>unit.conf.js</code> and <code>functional.conf.js</code> files under <code>conf/karma</code>. 
There are only a few items, followed by a <code>// can't touch this</code> comment, which the plugin relies on and should be left alone.

*Don't worry aobut setting <code>autoWatch</code>. grails-karma will override this option so tests are executed only once during the Grails build.*
