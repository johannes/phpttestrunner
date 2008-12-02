/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.schlueters.phpttestrunner.runner;

import java.io.File;
import de.schlueters.phpttestrunner.util.ExternalProcessRunner;


/**
 
 * @author johannes
 */
public class FailedOnlyRunner implements Runnable {
    ProcessBuilder command;
    
    public FailedOnlyRunner(File resultfile, String testBinary, String tests, String runtests, String args, String testingBinary) {
        command = new ProcessBuilder(testingBinary, runtests, "--html", resultfile.getAbsolutePath(), /*args,*/ tests);
        command.environment().put("TEST_PHP_EXECUTABLE", testingBinary);
        command.environment().put("PDO_MYSQL_TEST_DSN",  "mysql:host=127.0.0.1;dbname=test");
        command.environment().put("PDO_MYSQL_TEST_USER", "johannes");
        command.environment().put("PDO_MYSQL_TEST_PASS", "johannes");
        command.directory(new File("/home/johannes/work/mysql/php/trunk/tests/"));        
    }
    
    public void run() {
        try {
            ExternalProcessRunner.launchProcess("run-tests.php", command);
        } catch (java.io.IOException e) {
            e.getMessage();
        }
    }
}
