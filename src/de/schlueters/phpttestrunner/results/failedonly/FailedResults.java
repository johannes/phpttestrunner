/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
