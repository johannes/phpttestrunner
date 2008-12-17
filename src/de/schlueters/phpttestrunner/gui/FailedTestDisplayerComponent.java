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
package de.schlueters.phpttestrunner.gui;

import java.awt.Component;
import javax.swing.SwingUtilities;
import org.openide.windows.TopComponent;
import org.openide.util.HelpCtx;
import de.schlueters.phpttestrunner.results.Test;

/*
import org.openide.text.CloneableEditorSupport;
import javax.swing.text.EditorKit;
*/
import javax.swing.JEditorPane;
import org.netbeans.api.diff.Diff;
import org.netbeans.api.diff.DiffView;
import org.netbeans.api.diff.Difference;
import org.netbeans.api.diff.StreamSource;

/**
 *
 * @author johannes
 */
public class FailedTestDisplayerComponent extends TopComponent {
    /** Creates a new instance of DiffTopComponent */
    public FailedTestDisplayerComponent(Component editorPanel, Component diffPanel) {
        setLayout(new java.awt.GridLayout(0,1));
        add(diffPanel);
    }

    public FailedTestDisplayerComponent(Component editorPanel, DiffView view) {
        this(editorPanel, view.getComponent());
    }
    @Override
    public int getPersistenceType(){
        return TopComponent.PERSISTENCE_NEVER;
    }
    @Override
    protected String preferredID(){
        return "FailedTestDiasplayerComponent";    //NOI18N
    }
    @Override
    public HelpCtx getHelpCtx() {
        return new HelpCtx(getClass());
    } 

    public static void diff(final Test test){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    StreamSource local  = new phptStreamSource(test, ResultType.EXPECTED);
                    StreamSource remote = new phptStreamSource(test, ResultType.ACTUAL);
                    
                    DiffView view = Diff.getDefault().createDiff(local, remote);

                    showDiff(test.getTestname(), null, view);
                } catch (java.io.IOException ex) {
                    //Logger.getLogger(FailedTestDisplayerComponent.class.getName()).throwing(ex);
                }
            }
        });
    }
        
    public static void showDiff(final String title, final JEditorPane editor, final DiffView diff){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //create our panel with our view
                //right now I am just going to use the diff component 
                // instead of a panel
                //create a topcomponent with our panel
                
                FailedTestDisplayerComponent tc = new FailedTestDisplayerComponent(editor, diff);
                tc.setName("MY_DIFF");
                tc.setDisplayName(title);
                tc.open();
                tc.requestActive();
            }
        });
    }
    
    private enum ResultType {
        EXPECTED,
        ACTUAL,
        MEMORY,
        DIFF,
        SCRIPT
    }
    
    final private static class phptStreamSource extends StreamSource {
        private Test test;
        private ResultType type;
        private String file;
        
        public phptStreamSource(Test test, ResultType type) {
            this.test = test;
            this.type = type;
            
            switch (type) {
                case EXPECTED:
                    file = test.getExpectedFilename();
                    break;
                case ACTUAL:
                    this.file = test.getActualFilename();
                    break;
            }
        }
        
        public String getTitle() {
            return type == ResultType.EXPECTED ? "Expected Output" : "Actual Output";
        }
        
        public String getName() {
            return "mein toller name";
        }
        
        public String getMIMEType() {
            return "text/plain";
        }
                
        public java.io.Reader createReader() throws java.io.IOException {
            return new java.io.InputStreamReader(new java.io.FileInputStream(file));
        }
        
        public java.io.Writer createWriter(Difference[] conflicts) {
            return null;
        }
    }
}
