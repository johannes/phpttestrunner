/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.schlueters.phpttestrunner.gui;

import de.schlueters.phpttestrunner.results.Result;
import java.io.Serializable;
import java.util.logging.Logger;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

import java.util.List;
import java.util.LinkedList;

import java.awt.Component;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ListCellRenderer;
import javax.swing.JList;

import org.openide.loaders.DataObject;
import org.openide.cookies.EditorCookie;

import de.schlueters.phpttestrunner.results.Test;
import de.schlueters.phpttestrunner.results.TestResult;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.modules.InstalledFileLocator;
import org.openide.util.ImageUtilities;
import org.openide.windows.Mode;


/**
 * Top component which displays something.
 */
public final class TestResultsTopComponent extends TopComponent {
    private List<Test> tests;
    
    private static TestResultsTopComponent instance;

    static final String ICON_PATH = "de/schlueters/phpttestrunner/php.gif";

    private static final String PREFERRED_ID = "TestResultsTopComponent";

    private TestResultsTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(TestResultsTopComponent.class, "CTL_TestResultsTopComponent"));
        setToolTipText(NbBundle.getMessage(TestResultsTopComponent.class, "HINT_TestResultsTopComponent"));
        setIcon(ImageUtilities.loadImage(ICON_PATH));
    }

	public static void showResults(Result res)
	{
		try {
			//Result res = new FailedResults("/tmp/phptresult.html");
			//Result res = new HTMLResult(new File("/tmp/phptresult.html"));
			final List<Test> executedTests = res.getExecutedTests();

			Mode myMode = WindowManager.getDefault().findMode("output");
			TestResultsTopComponent comp = (TestResultsTopComponent)WindowManager.getDefault().findTopComponent("TestResultsTopComponent");
			myMode.dockInto(WindowManager.getDefault().findTopComponent("TestResultsTopComponent"));
			comp.open();
			comp.setVisible(true);
			comp.setTests(executedTests);
		 } catch (Exception e) {
			e.printStackTrace();
			NotifyDescriptor ex_dlg = new NotifyDescriptor.Message(e, NotifyDescriptor.ERROR_MESSAGE);
			DialogDisplayer.getDefault().notify(ex_dlg);
		 }
	}

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        listPopupMenu = new javax.swing.JPopupMenu();
        showphptMenuItem = new javax.swing.JMenuItem();
        openDiffMenuItem = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        resultList = new javax.swing.JList();
        javax.swing.JToolBar jToolBar1 = new javax.swing.JToolBar();
        passBtn = new javax.swing.JToggleButton();
        skipBtn = new javax.swing.JToggleButton();
        failBtn = new javax.swing.JToggleButton();
        xfailBtn = new javax.swing.JToggleButton();
        summaryLabel = new javax.swing.JLabel();

        org.openide.awt.Mnemonics.setLocalizedText(showphptMenuItem, org.openide.util.NbBundle.getMessage(TestResultsTopComponent.class, "TestResultsTopComponent.showphptMenuItem.text")); // NOI18N
        showphptMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showphptMenuItemActionPerformed(evt);
            }
        });
        listPopupMenu.add(showphptMenuItem);

        org.openide.awt.Mnemonics.setLocalizedText(openDiffMenuItem, org.openide.util.NbBundle.getMessage(TestResultsTopComponent.class, "TestResultsTopComponent.openDiffMenuItem.text")); // NOI18N
        openDiffMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openDiffMenuItemActionPerformed(evt);
            }
        });
        listPopupMenu.add(openDiffMenuItem);

        resultList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        resultList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                test(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                resultListMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(resultList);

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        passBtn.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(passBtn, org.openide.util.NbBundle.getMessage(TestResultsTopComponent.class, "TestResultsTopComponent.passBtn.text")); // NOI18N
        passBtn.setFocusable(false);
        passBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        passBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        passBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toggleBtnActionPerformed(evt);
            }
        });
        jToolBar1.add(passBtn);

        skipBtn.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(skipBtn, org.openide.util.NbBundle.getMessage(TestResultsTopComponent.class, "TestResultsTopComponent.skipBtn.text")); // NOI18N
        skipBtn.setFocusable(false);
        skipBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        skipBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        skipBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toggleBtnActionPerformed(evt);
            }
        });
        jToolBar1.add(skipBtn);

        failBtn.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(failBtn, org.openide.util.NbBundle.getMessage(TestResultsTopComponent.class, "TestResultsTopComponent.failBtn.text")); // NOI18N
        failBtn.setFocusable(false);
        failBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        failBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        failBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toggleBtnActionPerformed(evt);
            }
        });
        jToolBar1.add(failBtn);

        xfailBtn.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(xfailBtn, org.openide.util.NbBundle.getMessage(TestResultsTopComponent.class, "TestResultsTopComponent.xfailBtn.text")); // NOI18N
        xfailBtn.setFocusable(false);
        xfailBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        xfailBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        xfailBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toggleBtnActionPerformed(evt);
            }
        });
        jToolBar1.add(xfailBtn);

        org.openide.awt.Mnemonics.setLocalizedText(summaryLabel, org.openide.util.NbBundle.getMessage(TestResultsTopComponent.class, "TestResultsTopComponent.summaryLabel.text")); // NOI18N
        jToolBar1.add(summaryLabel);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jToolBar1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jToolBar1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

	private void showDiffOfSelectedTest() {
		Object selected = resultList.getSelectedValue();
		assert(selected instanceof Test);
		TestResult result = ((Test)selected).getResult();
		if (result == TestResult.FAIL || result == TestResult.XFAIL) {
			FailedTestDisplayerComponent.diff((Test)selected);
		}
	}

	private void showphptEditorOfSelectedTest() {
		Object selected = resultList.getSelectedValue();
		assert(selected instanceof Test);
		Test test = (Test)selected;

		try {
			java.io.File f = new java.io.File(test.getFilename());
			org.openide.filesystems.FileObject fo = org.openide.filesystems.FileUtil.toFileObject(f);
			DataObject d = DataObject.find(fo);
			EditorCookie ec;
			ec= (EditorCookie)d.getCookie(EditorCookie.class);
			ec.open();
			javax.swing.text.StyledDocument doc = ec.openDocument();
		} catch (Exception e) {
			e.getMessage();
		}
	}

private void test(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_test
	if (evt.getClickCount() == 2) {
		showphptEditorOfSelectedTest();
		showDiffOfSelectedTest();
	}
}//GEN-LAST:event_test

private void toggleBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toggleBtnActionPerformed
    displayResults();
}//GEN-LAST:event_toggleBtnActionPerformed

private void resultListMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resultListMousePressed
	if (evt.getButton() == java.awt.event.MouseEvent.BUTTON3) {
		JList list = (JList)evt.getComponent();
		int idx = list.locationToIndex(evt.getPoint());
		list.setSelectedIndex(idx);

		if (idx > -1) {
			Object selected = resultList.getSelectedValue();
			assert(selected instanceof Test);

			Test test = (Test)resultList.getSelectedValue();
			if (test.getResult() != TestResult.FAIL && test.getResult() != TestResult.XFAIL) {
				openDiffMenuItem.setEnabled(false);
			} else {
				openDiffMenuItem.setEnabled(true);
			}

			listPopupMenu.show(this, evt.getX(), evt.getY());
		}
	}
}//GEN-LAST:event_resultListMousePressed

private void openDiffMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openDiffMenuItemActionPerformed
	showDiffOfSelectedTest();
}//GEN-LAST:event_openDiffMenuItemActionPerformed

private void showphptMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showphptMenuItemActionPerformed
	showphptEditorOfSelectedTest();
}//GEN-LAST:event_showphptMenuItemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton failBtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu listPopupMenu;
    private javax.swing.JMenuItem openDiffMenuItem;
    private javax.swing.JToggleButton passBtn;
    private javax.swing.JList resultList;
    private javax.swing.JMenuItem showphptMenuItem;
    private javax.swing.JToggleButton skipBtn;
    private javax.swing.JLabel summaryLabel;
    private javax.swing.JToggleButton xfailBtn;
    // End of variables declaration//GEN-END:variables
    /**
     * Gets default instance. Do not use directly: reserved for *.settings files only,
     * i.e. deserialization routines; otherwise you could get a non-deserialized instance.
     * To obtain the singleton instance, use {@link findInstance}.
     */
    public static synchronized TestResultsTopComponent getDefault() {
        if (instance == null) {
            instance = new TestResultsTopComponent();
        }
        return instance;
    }

    /**
     * Obtain the TestResultsTopComponent instance. Never call {@link #getDefault} directly!
     */
    public static synchronized TestResultsTopComponent findInstance() {
        TopComponent win = WindowManager.getDefault().findTopComponent(PREFERRED_ID);
        if (win == null) {
            Logger.getLogger(TestResultsTopComponent.class.getName()).warning(
                    "Cannot find " + PREFERRED_ID + " component. It will not be located properly in the window system.");
            return getDefault();
        }
        if (win instanceof TestResultsTopComponent) {
            return (TestResultsTopComponent) win;
        }
        Logger.getLogger(TestResultsTopComponent.class.getName()).warning(
                "There seem to be multiple components with the '" + PREFERRED_ID +
                "' ID. That is a potential source of errors and unexpected behavior.");
        return getDefault();
    }

    @Override
    public int getPersistenceType() {
        return TopComponent.PERSISTENCE_NEVER;
    }

    /** replaces this in object stream */
    @Override
    public Object writeReplace() {
        return new ResolvableHelper();
    }

    @Override
    protected String preferredID() {
        return PREFERRED_ID;
    }
    
    private void displayResults() {
        if (tests == null) {
            return;
        }
        final Boolean showPass  = passBtn.isSelected();
        final Boolean showSkip  = skipBtn.isSelected();
        final Boolean showFail  = failBtn.isSelected();
        final Boolean showXFail = xfailBtn.isSelected();
        
        final List<Test> showTests = new LinkedList<Test>();
        resultList.removeAll();
        
        for (Test test : tests) {
            if (test.getResult() == test.getResult().FAIL && !showFail) {
                continue;
            }
            if (test.getResult() == test.getResult().XFAIL && !showXFail) {
                continue;
            }
            if (test.getResult() == test.getResult().PASS && !showPass) {
                continue;
            }
            if (test.getResult() == test.getResult().SKIP && !showSkip) {
                continue;
            }
            
            showTests.add(test);
        }

        resultList.setCellRenderer(new TestCellRenderer());
        resultList.setModel(new javax.swing.AbstractListModel() {
            private List<Test> names = showTests;
            @Override public int getSize() { return names.size(); }
            @Override public Object getElementAt(int i) { return names.get(i); }
        });
    }
    
    public void setTests(List<Test> tests) {
        this.tests = tests;

		int pass = 0, skip = 0, fail = 0, xfail = 0;

		for(Test t : tests) {
			switch (t.getResult()) {
				case PASS:  pass++;  break;
				case SKIP:  skip++;  break;
				case FAIL:  fail++;  break;
				case XFAIL: xfail++; break;
			}
		}

		summaryLabel.setText("("+pass+" PASS, "+skip+" SKIP, "+fail+" FAIL, "+xfail+ " XFAIL)");

        displayResults();
    }
    
    final private static class ResolvableHelper implements Serializable {

        private static final long serialVersionUID = 1L;

        public Object readResolve() {
			InstalledFileLocator.getDefault().locate("run-tests.php", "", false);
            return TestResultsTopComponent.getDefault();
        }
    }
    
    final private class TestCellRenderer extends JLabel implements ListCellRenderer {
        @Override
		public Component getListCellRendererComponent(
            JList list,
            Object value,
            int index,
            boolean isSelected,
            boolean cellHasFocus)
         {
            assert(value instanceof Test);
            
            String s = value.toString();
            setText(s);
            //setIcon((s.length() > 10) ? longIcon : shortIcon);
            if (isSelected) {
                setBackground(list.getSelectionBackground());
			} else {
				setBackground(list.getBackground());
			}
            
            switch (((Test)value).getResult()) {
                case FAIL:
                    setForeground(Color.RED);
                    break;
                case XFAIL:
                    setForeground(Color.ORANGE);
                    break;
                case SKIP:
                    setForeground(Color.GRAY);
                    break;
                default:
                    setForeground(list.getForeground());
                    break;
            }
			
			setEnabled(list.isEnabled());
			setFont(list.getFont());
            setOpaque(true);
            return this;
        }
    }
}
