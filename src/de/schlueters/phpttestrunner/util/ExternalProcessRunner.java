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
package de.schlueters.phpttestrunner.util;

import java.io.*;
import java.awt.EventQueue;
import org.openide.ErrorManager;
import org.openide.util.RequestProcessor;
import org.openide.awt.StatusDisplayer;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;
import org.openide.windows.OutputWriter;

/**
 * http://wiki.netbeans.org/DevFaqOutputWindowExternalProcess
 * @author johannes
 */
public class ExternalProcessRunner {
    static public void launchProcess (String title, ProcessBuilder command) throws IOException {
        if (EventQueue.isDispatchThread()) {
               throw new IllegalStateException ("Cannot be called from EDT");
        }
        final Process process = command.start();

        //Get the standard out
        InputStream out = new BufferedInputStream (process.getInputStream(), 8192);
        //Get the standard in
        InputStream err = new BufferedInputStream (process.getErrorStream(), 8192);

        //Create readers for each
        final Reader outReader = new BufferedReader (new InputStreamReader (out));
        final Reader errReader = new BufferedReader (new InputStreamReader (err));

        InputOutput io = IOProvider.getDefault().getIO(title, false);

        io.select();
        
        OutHandler processSystemOut = new OutHandler (outReader, io.getOut());
        OutHandler processSystemErr = new OutHandler (errReader, io.getErr());

        //Get two different threads listening on the output & err
        RequestProcessor.getDefault().post(processSystemOut);
        RequestProcessor.getDefault().post(processSystemErr);

        try {
            //Hang this thread until the process exits
            process.waitFor();
        } catch (InterruptedException ex) {
            ErrorManager.getDefault().notify(ex);
        }
        
        /*
        processSystemOut.close();
        processSystemErr.close();
        */
        
        if (process.exitValue() == 0) {
            StatusDisplayer.getDefault().setStatusText ("Done");
        } else {
            StatusDisplayer.getDefault().setStatusText ("Failure");
        }
    }
}

class OutHandler implements Runnable {
    private Reader out;
    private OutputWriter writer;
    public OutHandler (Reader out, OutputWriter writer) {
        this.out = out;
        this.writer = writer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                while (!out.ready()) {
                    try {
                        Thread.currentThread().sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        close();
                        return;
                    }
                }
                if (!readOneBuffer() || Thread.currentThread().isInterrupted()) {
                    close();
                    return;
                }
            } catch (IOException ioe) {
                //Stream already closed, this is fine
                return;
            }
        }
    }

    private boolean readOneBuffer() throws IOException {
        char[] cbuf = new char[255];
        int read;
        while ((read = out.read(cbuf)) != -1) {
            writer.write(cbuf, 0, read);
        }
        return read != -1;
    }

    private void close() {
        try {
            out.close();
        } catch (IOException ioe) {
            ErrorManager.getDefault().notify(ioe);
        } finally {
            writer.close();
        }
    }
}

