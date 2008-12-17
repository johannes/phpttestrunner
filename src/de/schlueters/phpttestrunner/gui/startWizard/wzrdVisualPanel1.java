/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.schlueters.phpttestrunner.gui.startWizard;

import de.schlueters.phpttestrunner.phptAction;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.prefs.Preferences;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

public final class wzrdVisualPanel1 extends JPanel {
    private static File myphpt = null;

    /** Creates new form wzrdVisualPanel1 */
    public wzrdVisualPanel1(Preferences prefs) {
        initComponents();

		runtestsTextField.  setText(prefs.get("runtests", ""));
		testedTextField.    setText(prefs.get("tested", ""));
		testingTextField.   setText(prefs.get("testing", ""));
		testsTextField.     setText(prefs.get("tests", ""));
		arguementsTextField.setText(prefs.get("argss", ""));
    }

    @Override
    public String getName() {
        return "Step #1";
    }

	public File getruntestsFileName() throws IOException {
        File phpt;
		if (runtestBundledRadioButton.isSelected()) {
            if (myphpt == null) {
                InputStream in = phptAction.class.getResourceAsStream("run-tests.php");
                myphpt = File.createTempFile("run-tests.", ".php");
                myphpt.deleteOnExit();
                FileOutputStream out = new FileOutputStream(myphpt);
                byte[] data = new byte[in.available()];
                in.read(data);
                out.write(data);
            }
            phpt = myphpt;
		} else {
            phpt = new File(runtestsTextField.getText());
            if (!phpt.exists()) {
                throw new IOException();
            }
        }
        return phpt;
	}

	public String getTestedBinaryFileName() {
		return testedTextField.getText();
	}

	public String getTestingBinaryFileName() {
		return testingTextField.getText();
	}

	public String getTestsDirName() {
		return testsTextField.getText();
	}

	public String getArguements() {
		return arguementsTextField.getText();
	}

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        testsTextField = new javax.swing.JTextField();
        testedTextField = new javax.swing.JTextField();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
        runtestsTextField = new javax.swing.JTextField();
        arguementsTextField = new javax.swing.JTextField();
        testingTextField = new javax.swing.JTextField();
        testedButton = new javax.swing.JButton();
        testsButton = new javax.swing.JButton();
        runtestsButton = new javax.swing.JButton();
        testingButton = new javax.swing.JButton();
        runtestBundledRadioButton = new javax.swing.JRadioButton();
        runtestsCustomRadioButton = new javax.swing.JRadioButton();

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                formComponentHidden(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(wzrdVisualPanel1.class, "wzrdVisualPanel1.jLabel1.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(wzrdVisualPanel1.class, "wzrdVisualPanel1.jLabel2.text")); // NOI18N

        testsTextField.setText(org.openide.util.NbBundle.getMessage(wzrdVisualPanel1.class, "wzrdVisualPanel1.testsTextField.text")); // NOI18N
        testsTextField.setToolTipText(org.openide.util.NbBundle.getMessage(wzrdVisualPanel1.class, "wzrdVisualPanel1.testsTextField.toolTipText")); // NOI18N

        testedTextField.setText(org.openide.util.NbBundle.getMessage(wzrdVisualPanel1.class, "wzrdVisualPanel1.testedTextField.text")); // NOI18N
        testedTextField.setToolTipText(org.openide.util.NbBundle.getMessage(wzrdVisualPanel1.class, "wzrdVisualPanel1.testedTextField.toolTipText")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(wzrdVisualPanel1.class, "wzrdVisualPanel1.jLabel3.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(wzrdVisualPanel1.class, "wzrdVisualPanel1.jLabel4.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(wzrdVisualPanel1.class, "wzrdVisualPanel1.jLabel5.text")); // NOI18N

        runtestsTextField.setText(org.openide.util.NbBundle.getMessage(wzrdVisualPanel1.class, "wzrdVisualPanel1.runtestsTextField.text")); // NOI18N
        runtestsTextField.setToolTipText(org.openide.util.NbBundle.getMessage(wzrdVisualPanel1.class, "wzrdVisualPanel1.runtestsTextField.toolTipText")); // NOI18N

        arguementsTextField.setText(org.openide.util.NbBundle.getMessage(wzrdVisualPanel1.class, "wzrdVisualPanel1.arguementsTextField.text")); // NOI18N
        arguementsTextField.setToolTipText(org.openide.util.NbBundle.getMessage(wzrdVisualPanel1.class, "wzrdVisualPanel1.arguementsTextField.toolTipText")); // NOI18N

        testingTextField.setText(org.openide.util.NbBundle.getMessage(wzrdVisualPanel1.class, "wzrdVisualPanel1.testingTextField.text")); // NOI18N
        testingTextField.setToolTipText(org.openide.util.NbBundle.getMessage(wzrdVisualPanel1.class, "wzrdVisualPanel1.testingTextField.toolTipText")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(testedButton, org.openide.util.NbBundle.getMessage(wzrdVisualPanel1.class, "wzrdVisualPanel1.testedButton.text")); // NOI18N
        testedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                testedButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(testsButton, org.openide.util.NbBundle.getMessage(wzrdVisualPanel1.class, "wzrdVisualPanel1.testsButton.text")); // NOI18N
        testsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                testsButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(runtestsButton, org.openide.util.NbBundle.getMessage(wzrdVisualPanel1.class, "wzrdVisualPanel1.runtestsButton.text")); // NOI18N
        runtestsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runtestsButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(testingButton, org.openide.util.NbBundle.getMessage(wzrdVisualPanel1.class, "wzrdVisualPanel1.testingButton.text")); // NOI18N
        testingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                testingButtonActionPerformed(evt);
            }
        });

        buttonGroup1.add(runtestBundledRadioButton);
        runtestBundledRadioButton.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(runtestBundledRadioButton, org.openide.util.NbBundle.getMessage(wzrdVisualPanel1.class, "wzrdVisualPanel1.runtestBundledRadioButton.text")); // NOI18N

        buttonGroup1.add(runtestsCustomRadioButton);
        org.openide.awt.Mnemonics.setLocalizedText(runtestsCustomRadioButton, org.openide.util.NbBundle.getMessage(wzrdVisualPanel1.class, "wzrdVisualPanel1.runtestsCustomRadioButton.text")); // NOI18N
        runtestsCustomRadioButton.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                runtestsCustomRadioButtonStateChanged(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel2)
                            .add(jLabel1))
                        .add(18, 18, 18)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(testsTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(testsButton))
                            .add(layout.createSequentialGroup()
                                .add(testedTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(testedButton))))
                    .add(layout.createSequentialGroup()
                        .add(jLabel3)
                        .add(18, 18, 18)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(runtestsCustomRadioButton)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(runtestsTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 276, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(runtestsButton))
                            .add(runtestBundledRadioButton)))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel5)
                            .add(jLabel4))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(arguementsTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
                            .add(layout.createSequentialGroup()
                                .add(testingTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(testingButton)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(testedTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel1)
                    .add(testedButton))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(testsTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(testsButton))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel3)
                    .add(runtestBundledRadioButton))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(runtestsCustomRadioButton)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(runtestsTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(runtestsButton)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(arguementsTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel4))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel5)
                    .add(testingTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(testingButton))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

	private void formComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentHidden

	}//GEN-LAST:event_formComponentHidden

	private void showFileDlg(String title, JTextField field, FileFilter filter) {
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle(title);
		chooser.setFileFilter(filter);
		chooser.setSelectedFile(new File(field.getText()));
		chooser.setMultiSelectionEnabled(false);
		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			field.setText(chooser.getSelectedFile().getAbsolutePath());
		}
	}

	private void testedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_testedButtonActionPerformed
		showFileDlg("Choose PHP Binary", testedTextField, new ExecuteableFilesOnlyFilter());
}//GEN-LAST:event_testedButtonActionPerformed

	private void testsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_testsButtonActionPerformed
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Select tests");
		chooser.setSelectedFile(new File(testsTextField.getText()));
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			testsTextField.setText(chooser.getSelectedFile().getAbsolutePath());
		}
}//GEN-LAST:event_testsButtonActionPerformed

	private void runtestsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runtestsButtonActionPerformed
		showFileDlg("Choose run-tests.php script", runtestsTextField, new ExecuteableFilesOnlyFilter());
	}//GEN-LAST:event_runtestsButtonActionPerformed

	private void testingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_testingButtonActionPerformed
		showFileDlg("Choose PHP Binary", testingTextField, new ExecuteableFilesOnlyFilter());
}//GEN-LAST:event_testingButtonActionPerformed

	private void runtestsCustomRadioButtonStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_runtestsCustomRadioButtonStateChanged
		Boolean enabled = runtestsCustomRadioButton.isSelected();
		runtestsTextField.setEnabled(enabled);
		runtestsButton.setEnabled(enabled);
	}//GEN-LAST:event_runtestsCustomRadioButtonStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField arguementsTextField;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton runtestBundledRadioButton;
    private javax.swing.JButton runtestsButton;
    private javax.swing.JRadioButton runtestsCustomRadioButton;
    private javax.swing.JTextField runtestsTextField;
    private javax.swing.JButton testedButton;
    private javax.swing.JTextField testedTextField;
    private javax.swing.JButton testingButton;
    private javax.swing.JTextField testingTextField;
    private javax.swing.JButton testsButton;
    private javax.swing.JTextField testsTextField;
    // End of variables declaration//GEN-END:variables

    private class ExecuteableFilesOnlyFilter extends FileFilter {
		@Override
		public boolean accept(File f) {
			return f.canExecute();
		}

		@Override
		public String getDescription() {
			return "Executeable Files";
		}
	}
}

