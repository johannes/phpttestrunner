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
package de.schlueters.phpttestrunner;

import de.schlueters.phpttestrunner.gui.TestResultsTopComponent;
import de.schlueters.phpttestrunner.gui.startWizard.wzrdVisualPanel1;
import de.schlueters.phpttestrunner.gui.startWizard.wzrdWizardPanel1;
import de.schlueters.phpttestrunner.results.Result;
import de.schlueters.phpttestrunner.results.fromhtmloutput.HTMLResult;

import de.schlueters.phpttestrunner.util.ExternalProcessRunner;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.actions.CallableSystemAction;

import java.awt.Dialog;
import org.openide.NotifyDescriptor;
import org.openide.DialogDisplayer;

import org.openide.WizardDescriptor;

import java.io.File;
import java.io.IOException;
import javax.swing.SwingWorker;

public final class phptAction extends CallableSystemAction {
    @Override
    public void performAction() {
        File resultfile = null;
        try {
            resultfile = File.createTempFile("phptresult.", ".html");
            resultfile.deleteOnExit();
        } catch (Exception e) {
            NotifyDescriptor ex_dlg = new NotifyDescriptor.Message(e.toString()+" Couldn't create temp file!", NotifyDescriptor.ERROR_MESSAGE);
            DialogDisplayer.getDefault().notify(ex_dlg);
        }

		wzrdWizardPanel1 wpanel = new wzrdWizardPanel1();
        WizardDescriptor.Panel[] panel = { wpanel };
        WizardDescriptor descr = new WizardDescriptor(panel);
        
        descr.setTitleFormat(new java.text.MessageFormat("Run phpt tests"));
        
        Dialog d = DialogDisplayer.getDefault().createDialog(descr);
        d.setVisible(true);
        d.toFront();

        if (descr.getValue() != WizardDescriptor.FINISH_OPTION) {
            return;
        }

		wzrdVisualPanel1 vpanel = (wzrdVisualPanel1)wpanel.getComponent();

        try {
            TestWorker runner = new TestWorker(
                    resultfile,
                    vpanel.getTestingBinaryFileName(),
                    vpanel.getTestsDirName(),
                    vpanel.getruntestsFileName().getAbsolutePath(),
                    vpanel.getArguements(),
                    vpanel.getTestedBinaryFileName());

            runner.execute();
        } catch (IOException e) {
            e.printStackTrace();
            // TODO: Error reporting to user!
        }
    }

    @Override
    public String getName() {
        return NbBundle.getMessage(phptAction.class, "CTL_StartWizard");
    }

    @Override
    protected String iconResource() {
        return "de/schlueters/phpttestrunner/php.gif";
    }

    @Override
    public HelpCtx getHelpCtx() {
        return HelpCtx.DEFAULT_HELP;
    }

    @Override
    protected boolean asynchronous() {
        return false;
    }
    
    private class TestWorker extends SwingWorker {
        private ProcessBuilder command;
        private File resultfile;
        private String testBinary;
        private String tests;
        private String runtests;
        private String args;
        private String testingBinary;

        public TestWorker(File resultfile, String testBinary, String tests, String runtests, String args, String testingBinary) {
            this.resultfile = resultfile;
            this.testBinary = testBinary;
            this.tests = tests;
            this.runtests = runtests;
            this.args = args;
            this.testingBinary = testBinary;
        }

        @Override
        public Object doInBackground() {
            File testdir = new File(tests);
            if (!testdir.isDirectory()) testdir = testdir.getParentFile();

            command = new ProcessBuilder(testingBinary, runtests, "--html", resultfile.getAbsolutePath(), /*args,*/ tests);
            command.environment().put("TEST_PHP_EXECUTABLE", testingBinary);
            try {
                ExternalProcessRunner.launchProcess("run-tests.php", command);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void done() {
            try {
                //Result res = new FailedResults("/tmp/phptresult.html");
                //Result res = new HTMLResult(new File("/tmp/phptresult.html"));
                Result res = new HTMLResult(resultfile);
                TestResultsTopComponent.showResults(res);
            } catch (Exception e) {
                e.printStackTrace();
                NotifyDescriptor ex_dlg = new NotifyDescriptor.Message(e, NotifyDescriptor.ERROR_MESSAGE);
                DialogDisplayer.getDefault().notify(ex_dlg);
            } finally {
                resultfile.delete();
            }
        }
    }
}