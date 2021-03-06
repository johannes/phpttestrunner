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
package de.schlueters.phpttestrunner.results.failedonly;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import de.schlueters.phpttestrunner.results.Result;

/**
 *
 * @author johannes
 */
public class FailedResults extends Result {
    public FailedResults(File file) throws Exception {
        super(file);
    }
    
    protected void parse(File resultfile) throws IOException, FileNotFoundException {
        String phpt;
        String outfilesbase;
        String line;
        
        BufferedReader rdr = new BufferedReader(new FileReader(resultfile));
        while ((line = rdr.readLine()) != null) {
            if (line.startsWith("#")) {
                phpt = line.substring(line.lastIndexOf(": ")+2, line.length());
                String path = line.substring("# ".length(), line.lastIndexOf(": ")-"# ".length());
                path = path.substring(0, path.lastIndexOf("/"));
                outfilesbase = path
                             + line.substring(line.lastIndexOf("/"), line.length()-"phpt".length());
            } else {
                phpt = line;
                outfilesbase = line.substring(0, line.length()-"phpt".length());
            }
            
            
            testExecuted(new FailedTest(phpt, outfilesbase));
        }
    }
}
