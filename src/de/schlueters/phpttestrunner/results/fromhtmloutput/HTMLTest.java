/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.schlueters.phpttestrunner.results.fromhtmloutput;

import de.schlueters.phpttestrunner.results.Test;
import de.schlueters.phpttestrunner.results.TestResult;

import org.w3c.dom.NodeList;

/**
 *
 * @author johannes
 */
public class HTMLTest extends Test {
    NodeList nodes;
    String redirect;
    
    public HTMLTest(NodeList nodes, String redirect) {
        this.nodes    = nodes;
        this.redirect = redirect;
    }
    
    @Override
    public String getTestname() {
        return nodes.item(1).getFirstChild().getNodeValue();
    }
    
    @Override
    public String getFilename() {
        if (redirect == null) {
            return nodes.item(5).getFirstChild().getTextContent();
        } else {
            String file = nodes.item(5).getFirstChild().getTextContent();
            return redirect+"/"+file;
        }
    }
    
    public String getExpectedFilename() {
        String phptname = nodes.item(5).getFirstChild().getTextContent();
        return phptname.substring(0, phptname.length()-"phpt".length())+"exp";
    }
    
    public String getActualFilename() {
        String phptname = nodes.item(5).getFirstChild().getTextContent();
        return phptname.substring(0, phptname.length()-"phpt".length())+"out";
    }
    
    @Override
    public de.schlueters.phpttestrunner.results.TestResult getResult() {
        String result = nodes.item(0).getFirstChild().getTextContent();
        if (result.equals("FAIL")) {
            return TestResult.FAIL;
        } else if (result.equals("PASS")) {
            return TestResult.PASS;
        } else if (result.equals("XFAIL")) {
            return TestResult.XFAIL;
        } else if (result.equals("SKIP")) {
            return TestResult.SKIP;
        }
        return null;
    }
}
