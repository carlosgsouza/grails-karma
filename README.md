grails-karma
============
Run javascript tests in the Grails build!

The main goal for Karma is to bring a productive environment for javascript testing. Grails-karma allows Grails developers to use this spectacular tool in their application so they can write unit and functional tests.

## Instalation
First, install Karma as described in the [Karma's official documentation](http://karma-runner.github.io/0.10/index.html) and make sure karma is on your path.
Then execute the following command to install the grails-karma plugin <code>grails install-plugin grails-karma</code>.

When installed, the configuration files for karma will be created under <code>conf/karma</code>. Also, two folders for will be created for your test code: <code>test/js-unit</code> and <code>test/js-functional</code>

## Configuration
All configuration is made in the <code>unit.conf.js</code> and <code>functional.conf.js</code> files under <code>conf/karma</code>. These files have a very useful default configuration. Please refer to the karma official documentation(http://karma-runner.github.io/0.10/index.html) for more information.

You may configure your karma tests they way you want by changing the configuration files under conf/karma. However, make sure you don't change any of the following configuration parameters used by the plugin:
> test-report

## Usage
To run the karma plugin, use 
> grails test-app karma:unit
> grails test-app karma:functional

or simply grails test-app

By default, grails-karma will look for javascript tests under the directories js-unit and js-functional and for javascript code under the directory …
