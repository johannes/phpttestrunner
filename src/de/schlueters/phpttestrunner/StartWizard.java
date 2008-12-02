package de.schlueters.phpttestrunner;

import de.schlueters.phpttestrunner.gui.TestResultsTopComponent;
import de.schlueters.phpttestrunner.runner.FailedOnlyRunner;
import de.schlueters.phpttestrunner.results.Result;
import de.schlueters.phpttestrunner.results.fromhtmloutput.HTMLResult;
import de.schlueters.phpttestrunner.results.Test;
import de.schlueters.phpttestrunner.gui.startWizard.*;

import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.actions.CallableSystemAction;

import org.openide.windows.Mode;
import org.openide.windows.WindowManager;

import org.openide.util.Task;
import org.openide.util.RequestProcessor;
import java.awt.Dialog;
import org.openide.NotifyDescriptor;
import org.openide.DialogDisplayer;

import org.openide.WizardDescriptor;

import java.io.File;
import java.util.List;

public final class StartWizard extends CallableSystemAction {

    public void performAction() {
        File resultfile = null;
        try {
            resultfile = File.createTempFile("phptresult.", ".html");
        } catch (Exception e) {
            NotifyDescriptor ex_dlg = new NotifyDescriptor.Message(e.toString()+" Couldn't create temp file!", NotifyDescriptor.ERROR_MESSAGE);
            DialogDisplayer.getDefault().notify(ex_dlg);
        }
        
        WizardDescriptor.Panel[] panel = { new wzrdWizardPanel1() };
        WizardDescriptor descr = new WizardDescriptor(panel);
        
        descr.setTitleFormat(new java.text.MessageFormat("Run phpt tests"));
        
        Dialog d = DialogDisplayer.getDefault().createDialog(descr);
        d.setVisible(true);
        d.toFront();

        if (descr.getValue() != WizardDescriptor.FINISH_OPTION) {
            return;
        }

        FailedOnlyRunner runner = new FailedOnlyRunner(
            resultfile,
            "php",
            "/home/johannes/src/php/PHP_5_3/ext/standard/tests",
            "/home/johannes/src/php/PHP_5_3/run-tests.php", 
            "", // args
            "/home/johannes/src/php/build/5.3-debug-notsrm/sapi/cli/php");
        
        Task task = new Task(runner);
//        task.addTaskListener(new RunnerListener());
        RequestProcessor.getDefault().post(task);
        
        
        task.waitFinished();
        
        try {
            //Result res = new FailedResults("/tmp/result.txt");
            Result res = new HTMLResult(resultfile);
            final List<Test> executedTests = res.getExecutedTests();

            Mode myMode = WindowManager.getDefault().findMode("bottomSlidingSide");
            TestResultsTopComponent comp = (TestResultsTopComponent)WindowManager.getDefault().findTopComponent("TestResultsTopComponent");
            myMode.dockInto(WindowManager.getDefault().findTopComponent("TestResultsTopComponent"));
            comp.open();
            comp.setVisible(true);
            comp.setTests(executedTests);                
         } catch (Exception e) {
            NotifyDescriptor ex_dlg = new NotifyDescriptor.Message(e, NotifyDescriptor.ERROR_MESSAGE);
            DialogDisplayer.getDefault().notify(ex_dlg);
         }
        
        resultfile.delete();
    }
    
    public String getName() {
        return NbBundle.getMessage(StartWizard.class, "CTL_StartWizard");
    }

    @Override
    protected String iconResource() {
        return "de/schlueters/phpttestrunner/php.gif";
    }

    public HelpCtx getHelpCtx() {
        return HelpCtx.DEFAULT_HELP;
    }

    @Override
    protected boolean asynchronous() {
        return false;
    }
    /*
    class RunnerListener implements TaskListener{
        public void taskFinished(Task task) {
            try {
                FailedResults res = new FailedResults("/tmp/result.txt");
                res.getExecutedTests();
                
                Mode myMode = WindowManager.getDefault().findMode("bottomSlidingSide");
                myMode.dockInto(WindowManager.getDefault().findTopComponent("TestResultsTopComponent"));
                WindowManager.getDefault().findTopComponent("TestResultsTopComponent").open();
                WindowManager.getDefault().findTopComponent("TestResultsTopComponent").setVisible(true);
            } catch (Exception e) {
            }
        }
    }
    */
}