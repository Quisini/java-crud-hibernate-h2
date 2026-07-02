/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package cch.view;

import cch.dao.CidadeDAO;
import cch.model.Cidade;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lucas Motta
 */
public class TelaCidade extends javax.swing.JFrame {
    
    private CidadeDAO cidadeDAO = new CidadeDAO();
    private Long cidadeSelecionadaId = null;
    private javax.swing.table.TableRowSorter<javax.swing.table.DefaultTableModel> sorter;
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaCidade.class.getName());

    /**
     * Creates new form TelaCidade
     */
    public TelaCidade() {
        initComponents();
        txtCod.setEnabled(false);
        txtNome.addKeyListener(new java.awt.event.KeyAdapter() {
        @Override
        public void keyTyped(java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();

        if (!Character.isLetter(c) && c != ' ' && c != java.awt.event.KeyEvent.VK_BACK_SPACE) {
            evt.consume(); 
                }
            }
        });
        txtUF.addKeyListener(new java.awt.event.KeyAdapter() {
        @Override
        public void keyTyped(java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();

        if (!Character.isLetter(c)) {
            evt.consume();
        }else if(txtUF.getText().length() >= 2){
            evt.consume();
        }      
        }
        });
        jTable1.getSelectionModel().addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            @Override
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                if (!evt.getValueIsAdjusting() && jTable1.getSelectedRow() != -1) {
                    carregarLinhaSelecionada();
                }
            }
        });
        
        sorter = new javax.swing.table.TableRowSorter<>((javax.swing.table.DefaultTableModel) jTable1.getModel());
        jTable1.setRowSorter(sorter);
        
        carregarTabela();
    }
    
    private void carregarLinhaSelecionada() {
        int linhaSelecionada = jTable1.convertRowIndexToModel(jTable1.getSelectedRow());
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        
        cidadeSelecionadaId = (Long) modelo.getValueAt(linhaSelecionada, 0);
        txtCod.setText(String.valueOf(cidadeSelecionadaId));
        txtNome.setText(modelo.getValueAt(linhaSelecionada, 1).toString());
        txtUF.setText(modelo.getValueAt(linhaSelecionada, 2).toString());
    }
    
    private void limparCampos() {
        txtNome.setText("");
        txtUF.setText("");
        jTable1.clearSelection();
        cidadeSelecionadaId = null;
        txtCod.setText("");
    }
    
    private void carregarTabela() {
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        modelo.setRowCount(0);
        
        List<Cidade> cidades = cidadeDAO.listarTodas();
        for (Cidade cidade : cidades) {
            modelo.addRow(new Object[]{
                cidade.getId(),
                cidade.getNome(),
                cidade.getUf()
            });
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtCod = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtUF = new javax.swing.JTextField();
        btnNovo = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        txtPesquisar = new javax.swing.JTextField();
        btnOk = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Cadastro de cidade");

        jLabel3.setText("Código");

        jLabel4.setText("Nome");

        txtNome.addActionListener(this::txtNomeActionPerformed);

        jLabel5.setText("UF");

        btnNovo.setText("+ Novo");
        btnNovo.addActionListener(this::btnNovoActionPerformed);

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(this::btnSalvarActionPerformed);

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(this::btnExcluirActionPerformed);

        btnOk.setText("OK");
        btnOk.addActionListener(this::btnOkActionPerformed);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome", "UF"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(405, 405, 405))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnNovo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(65, 65, 65)
                                .addComponent(btnSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(63, 63, 63)
                                .addComponent(btnExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(8, 8, 8))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtCod, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(148, 148, 148))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(49, 49, 49))
                                    .addComponent(txtNome, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(96, 96, 96)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(50, 50, 50))
                                    .addComponent(txtUF)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtPesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(372, 372, 372)))
                        .addGap(22, 22, 22))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOk))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        if (txtNome.getText().trim().isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Preencha o nome da cidade antes de adicionar.");
            return;
        }
        
        String uf = txtUF.getText().trim().toUpperCase();
        if (uf.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "A UF é obrigatória!");
            return;
        }
        if (uf.length() != 2) {
            javax.swing.JOptionPane.showMessageDialog(this, "A UF deve ter exatamente 2 caracteres!");
            return;
        }

        try {
            Cidade cidade = new Cidade(txtNome.getText(), uf);
            cidadeDAO.salvar(cidade);
            
            limparCampos();
            carregarTabela();
            javax.swing.JOptionPane.showMessageDialog(this, "Cidade salva com sucesso!");
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Erro ao salvar cidade: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if (cidadeSelecionadaId == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "Selecione uma cidade na tabela para editar.");
            return;
        }

        String uf = txtUF.getText().trim().toUpperCase();
        if (uf.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "A UF é obrigatória!");
            return;
        }
        if (uf.length() != 2) {
            javax.swing.JOptionPane.showMessageDialog(this, "A UF deve ter exatamente 2 caracteres!");
            return;
        }
        
        try {
            Cidade cidade = cidadeDAO.buscarPorId(cidadeSelecionadaId);
            cidade.setNome(txtNome.getText());
            cidade.setUf(uf);
            cidadeDAO.atualizar(cidade);
            
            limparCampos();
            carregarTabela();
            javax.swing.JOptionPane.showMessageDialog(this, "Cidade atualizada com sucesso!");
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Erro ao atualizar cidade: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        if (cidadeSelecionadaId == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "Selecione uma cidade para excluir.");
            return;
        }
        
        int confirm = javax.swing.JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir esta cidade?", "Confirmação", javax.swing.JOptionPane.YES_NO_OPTION);
        
        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
            try {
                cidadeDAO.excluir(cidadeDAO.buscarPorId(cidadeSelecionadaId));
                limparCampos();
                carregarTabela();
                javax.swing.JOptionPane.showMessageDialog(this, "Cidade excluída com sucesso!");
            } catch (Exception e) {
                javax.swing.JOptionPane.showMessageDialog(this, "Erro ao excluir cidade: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        String texto = txtPesquisar.getText().trim();

        if (texto.isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(javax.swing.RowFilter.regexFilter("(?i)" + java.util.regex.Pattern.quote(texto), 1));
        }
    }//GEN-LAST:event_btnOkActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new TelaCidade().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnOk;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtCod;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtPesquisar;
    private javax.swing.JTextField txtUF;
    // End of variables declaration//GEN-END:variables
}
