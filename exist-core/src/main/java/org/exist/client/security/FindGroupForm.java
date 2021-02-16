/*
 *  eXist Open Source Native XML Database
 *  Copyright (C) 2001-2012 The eXist Project
 *  http://exist-db.org
 *
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public License
 *  as published by the Free Software Foundation; either version 2
 *  of the License, or (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 *  $Id$
 */
package org.exist.client.security;

import org.exist.client.DialogCompleteWithResponse;
import org.exist.client.DialogWithResponse;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import org.exist.xmldb.UserManagementService;
import org.xmldb.api.base.XMLDBException;

/**
 *
 * @author <a href="mailto:adam.retter@googlemail.com">Adam Retter</a>
 */
public class FindGroupForm extends javax.swing.JFrame implements DialogWithResponse<String> {
    private final UserManagementService userManagementService;
    private final List<DialogCompleteWithResponse<String>> dialogCompleteWithResponseCallbacks = new ArrayList<DialogCompleteWithResponse<String>>();
    private final Set<String> allGroupNames;
    private DefaultComboBoxModel groupNameModel;

    public FindGroupForm(final UserManagementService userManagementService) throws XMLDBException {
        this.userManagementService = userManagementService;
        
        allGroupNames = new HashSet<String>();
        for(final String groupName : userManagementService.getGroups()) {
            allGroupNames.add(groupName);
        }
        
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblGroupName = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnOk = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        cmbGroupName = new javax.swing.JComboBox();
        AutoCompletion.enable(cmbGroupName);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Find User...");

        lblGroupName.setText("Group:");

        btnOk.setText("Ok");
        btnOk.setEnabled(false);
        btnOk.addActionListener(this::btnOkActionPerformed);

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(this::btnCancelActionPerformed);

        cmbGroupName.setEditable(true);
        cmbGroupName.setModel(getGroupNameModel());
        cmbGroupName.addActionListener(this::cmbGroupNameActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblGroupName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbGroupName, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnOk)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGroupName)
                    .addComponent(cmbGroupName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOk)
                    .addComponent(btnCancel))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        setVisible(false);
        dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void cmbGroupNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbGroupNameActionPerformed
        final String currentGroupName = (String)cmbGroupName.getSelectedItem();
        final boolean isValid = isValidGroupName(currentGroupName);
        btnOk.setEnabled(isValid);
    }//GEN-LAST:event_cmbGroupNameActionPerformed

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        final String currentGroupName = (String)cmbGroupName.getSelectedItem();
        if(!isValidGroupName(currentGroupName)) {
            return;
        }
        
        for(final DialogCompleteWithResponse<String> callback : getDialogCompleteWithResponseCallbacks()) {
            callback.complete(currentGroupName);
        }
        setVisible(false);
        dispose();
    }//GEN-LAST:event_btnOkActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnOk;
    private javax.swing.JComboBox cmbGroupName;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblGroupName;
    // End of variables declaration//GEN-END:variables


    private boolean isValidGroupName(final String groupName) {
        return allGroupNames.contains(groupName);
    }
    
    private ComboBoxModel getGroupNameModel() {
        if(groupNameModel == null) {
            groupNameModel = new DefaultComboBoxModel();
            groupNameModel.addElement("");
            for(final String groupName : allGroupNames) {
               groupNameModel.addElement(groupName);
            }
        }
        
        return groupNameModel;
    }
    
    private UserManagementService getUserManagementService() {
        return userManagementService;
    }
    
    @Override
    public void addDialogCompleteWithResponseCallback(final DialogCompleteWithResponse<String> dialogCompleteWithResponseCallback) {
        getDialogCompleteWithResponseCallbacks().add(dialogCompleteWithResponseCallback);
    }
    
    private List<DialogCompleteWithResponse<String>> getDialogCompleteWithResponseCallbacks() {
        return dialogCompleteWithResponseCallbacks;
    }
}
