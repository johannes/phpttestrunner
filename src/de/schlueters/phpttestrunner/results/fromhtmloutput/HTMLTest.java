/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2008 Johannes Schlüter. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common
 * Development and Distribution License("CDDL") (collectively, the
 * "License"). You may not use this file except in compliance with the
 * License. You can obtain a copy of the License at
 * http://www.netbeans.org/cddl-gplv2.html. See the License for the
 * specific language governing permissions and limitations under the
 * License. Johannes Schlüter designates this particular file as subject
 * to the "Classpath" exception as provided by Sun in the GPL Version 2
 * section of the License file that accompanied this code.
 *
 * If you wish your version of this file to be governed by only the CDDL
 * or only the GPL Version 2, indicate your decision by adding
 * "[Contributor] elects to include this software in this distribution
 * under the [CDDL or GPL Version 2] license." If you do not indicate a
 * single choice of license, a recipient has the option to distribute
 * your version of this file under either the CDDL, the GPL Version 2 or
 * to extend the choice of license to its licensees as provided above.
 * However, if you add GPL Version 2 code and therefore, elected the GPL
 * Version 2 license, then the option applies only if the new code is
 * made subject to such option by the copyright holder.
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
