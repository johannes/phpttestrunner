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

import org.xml.sax.InputSource;

import java.util.StringTokenizer;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.apache.xerces.parsers.DOMParser;

import de.schlueters.phpttestrunner.results.Result;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


/**
 *
 * @author johannes
 */
public class HTMLResult extends Result {
    public HTMLResult(File file) throws Exception {
        super(file);
    }
    
    protected void parse(File file) throws Exception {
        DOMParser parser = new DOMParser();
        parser.parse(new InputSource(new BufferedReader(new FileReader(file))));
        Document doc = parser.getDocument();
        String redirectBase = null;
        
        NodeList trs = doc.getElementsByTagName("tr");
        for (int i = 0; i < trs.getLength(); i++) {
            Node tr = trs.item(i);
            NodeList tds = tr.getChildNodes();
            
            if (tds.getLength() == 6) {            
                testExecuted(new HTMLTest(tds, redirectBase));
            } else if (tds.getLength() == 1) {
                StringTokenizer tokenzier = new StringTokenizer(tds.item(0).getFirstChild().getNodeValue());
                
                assert(tokenzier.nextToken().equals("--->"));
                if (redirectBase == null) {
                    redirectBase = tokenzier.nextToken();
                    assert(tokenzier.nextToken().charAt(0) == '(');
                } else {
                    redirectBase = null;
                }
            }
        }
    }
}
