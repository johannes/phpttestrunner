/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.schlueters.phpttestrunner.results;

/**
 *
 * @author johannes
 */
abstract public class Test {
    abstract public String getTestname();
    abstract public String getFilename();
    abstract public TestResult getResult();
    
    abstract public String getExpectedFilename();
    abstract public String getActualFilename();
    
    static String getResultName(TestResult res) {
        switch (res) {
            case PASS: return "PASS";
            case SKIP: return "SKIP";
            case FAIL: return "FAIL";
            case XFAIL: return "XFAIL";
            default: return "UNKNOWN";
        }
    }
    
    @Override
    public String toString() {
        String filename = getFilename();
        filename = filename.substring(filename.lastIndexOf("/")+1);
        return "["+getResultName(getResult())+"] "+getTestname()+" ("+filename+")";
    }
}
