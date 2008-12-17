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