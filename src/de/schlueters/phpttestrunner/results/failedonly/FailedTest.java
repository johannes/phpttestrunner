/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.schlueters.phpttestrunner.results.failedonly;

import de.schlueters.phpttestrunner.results.Test;
import de.schlueters.phpttestrunner.results.TestResult;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;


/**
 *
 * @author johannes
 */
public class FailedTest extends Test {
    private String testname = null;
    private String filename;
    private String outfilesbase;
    
    public FailedTest(String filename, String outfilesbase) {
        this.filename = filename;
        this.outfilesbase = outfilesbase;
    }
    
    @Override
    public String getTestname() {
        if (testname == null) {
            try {
                String line;
                
                BufferedReader rdr = new BufferedReader(new FileReader(filename));
                while ((line = rdr.readLine()) != null) {
                    if (testname == null && line.startsWith("--TEST--")) {
                        testname = new String();
                    } else {
                        if (line.startsWith("--")) {
                            break;
                        } else {
                            testname += line;
                        }
                   }
                }
            } catch (IOException e) {
                testname = filename;
            }
        }
        return testname;
    }
    
    @Override
    public String getFilename() {
        return filename;
    }
    
    public String getExpectedFilename() {
        return outfilesbase+"exp";
    }
    
    public String getActualFilename() {
        return outfilesbase+"out";
    }
    
    @Override
    public de.schlueters.phpttestrunner.results.TestResult getResult() {
        return TestResult.FAIL;
    }
}
