/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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

