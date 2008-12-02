package de.schlueters.phpttestrunner.gui;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;

/**
 * Action which shows TestResults component.
 */
public class TestResultsAction extends AbstractAction {

    public TestResultsAction() {
        super(NbBundle.getMessage(TestResultsAction.class, "CTL_TestResultsAction"));
//        putValue(SMALL_ICON, new ImageIcon(Utilities.loadImage(TestResultsTopComponent.ICON_PATH, true)));
    }

    public void actionPerformed(ActionEvent evt) {
        TopComponent win = TestResultsTopComponent.findInstance();
        win.open();
        win.requestActive();
    }
}
