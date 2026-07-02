/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package cch.view;

import cch.dao.CidadeDAO;
import cch.dao.ClienteDAO;
import cch.model.Cidade;
import cch.model.Cliente;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

public class TelaCliente extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaCliente.class.getName());

    private ClienteDAO clienteDAO = new ClienteDAO();
    private CidadeDAO cidadeDAO = new CidadeDAO();
    private Long clienteSelecionadoId = null;
    private javax.swing.table.TableRowSorter<javax.swing.table.DefaultTableModel> sorter;
    
    public TelaCliente() {
        initComponents();
        txtCod.setEnabled(false);
        txtCPF.addKeyListener(new java.awt.event.KeyAdapter() {
        @Override
        public void keyTyped(java.awt.event.KeyEvent evt) {
            char c = evt.getKeyChar();

            if (!Character.isDigit(c)) {
                evt.consume();
            } else if (txtCPF.getText().length() >= 11) {
                evt.consume(); 
            }
        }
    });
       txtNome.addKeyListener(new java.awt.event.KeyAdapter() {
        @Override
        public void keyTyped(java.awt.event.KeyEvent evt) {
            char c = evt.getKeyChar();

            if (!Character.isLetter(c) && c != ' ' && c != java.awt.event.KeyEvent.VK_BACK_SPACE) {
                evt.consume(); 
            }
        }
    });
        
        Tabela.getSelectionModel().addListSelectionListener(new javax.swing.event.ListSelectionListener() {
        @Override
        public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
            if (!evt.getValueIsAdjusting() && Tabela.getSelectedRow() != -1) {
                carregarLinhaSelecionada();
            }
        }
    });
        
        sorter = new javax.swing.table.TableRowSorter<>((javax.swing.table.DefaultTableModel) Tabela.getModel());
        Tabela.setRowSorter(sorter);
        
        txtTelefone.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();

                if (!Character.isDigit(c)) {
                    evt.consume();
                } else if (txtTelefone.getText().length() >= 15) {
                    evt.consume();
                }
            }
        });
        
        carregarCidadesComboBox();
        carregarTabela();
    }
    
    private void carregarLinhaSelecionada() {
        int linhaSelecionada = Tabela.convertRowIndexToModel(Tabela.getSelectedRow());
        DefaultTableModel modelo = (DefaultTableModel) Tabela.getModel();
        
        clienteSelecionadoId = (Long) modelo.getValueAt(linhaSelecionada, 0);
        txtCod.setText(String.valueOf(clienteSelecionadoId));
        txtNome.setText(modelo.getValueAt(linhaSelecionada, 1).toString());
        txtCPF.setText(modelo.getValueAt(linhaSelecionada, 2).toString());
        txtTelefone.setText(modelo.getValueAt(linhaSelecionada, 3).toString());
        txtEmail.setText(modelo.getValueAt(linhaSelecionada, 4).toString());
        
        // Encontrar a cidade no ComboBox
        String cidadeStr = modelo.getValueAt(linhaSelecionada, 5).toString();
        for (int i = 0; i < cbCidade.getItemCount(); i++) {
            if (cbCidade.getItemAt(i).toString().equals(cidadeStr)) {
                cbCidade.setSelectedIndex(i);
                break;
            }
        }
    }
    
    private void limparCampos() {
        txtNome.setText("");
        txtCPF.setText("");
        txtTelefone.setText("");
        txtEmail.setText("");
        if (cbCidade.getItemCount() > 0) {
            cbCidade.setSelectedIndex(0);
        }
        Tabela.clearSelection();
        clienteSelecionadoId = null;
        txtCod.setText("");
    }
    
    private void carregarCidadesComboBox() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        List<Cidade> cidades = cidadeDAO.listarTodos();
        
        for (Cidade cidade : cidades) {
            model.addElement(cidade.toString());
        }
        
        cbCidade.setModel(model);
    }
    
    private Cidade getCidadeSelecionada() {
        String cidadeStr = (String) cbCidade.getSelectedItem();
        if (cidadeStr == null) return null;
        
        List<Cidade> cidades = cidadeDAO.listarTodos();
        for (Cidade cidade : cidades) {
            if (cidade.toString().equals(cidadeStr)) {
                return cidade;
            }
        }
        return null;
    }
    
    private void carregarTabela() {
        DefaultTableModel modelo = (DefaultTableModel) Tabela.getModel();
        modelo.setRowCount(0);
        
        List<Cliente> clientes = clienteDAO.listarTodos();
        for (Cliente cliente : clientes) {
            modelo.addRow(new Object[]{
                cliente.getId(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getTelefone(),
                cliente.getEmail(),
                cliente.getCidade() != null ? cliente.getCidade().toString() : ""
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtCod = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtCPF = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtTelefone = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnNovo = new javax.swing.JButton();
        bntSalvar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        txtPesquisar = new javax.swing.JTextField();
        btnOk = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabela = new javax.swing.JTable();
        cbCidade = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Cadastro de cliente");

        jLabel2.setText("Código");

        txtCod.setText("gerado automaticamente");
        txtCod.addActionListener(this::txtCodActionPerformed);

        jLabel3.setText("CPF");

        txtCPF.addActionListener(this::txtCPFActionPerformed);

        jLabel4.setText("E-mail");

        jLabel5.setText("Nome");

        jLabel6.setText("Telefone");

        jLabel7.setText("Cidade");

        btnNovo.setText("+ Novo");
        btnNovo.addActionListener(this::btnNovoActionPerformed);

        bntSalvar.setText("Salvar");
        bntSalvar.addActionListener(this::bntSalvarActionPerformed);

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(this::btnExcluirActionPerformed);

        txtPesquisar.setToolTipText("Pesquisa por nome");
        txtPesquisar.addActionListener(this::txtPesquisarActionPerformed);

        btnOk.setText("OK");
        btnOk.addActionListener(this::btnOkActionPerformed);

        Tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome", "CPF", "Telefone", "E-mail", "Cidade"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(Tabela);

        cbCidade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nenhuma cidade cadastrada" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                                .addGap(181, 181, 181))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(119, 119, 119))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(199, 199, 199))
                            .addComponent(txtCPF)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(177, 177, 177))
                            .addComponent(txtEmail)
                            .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(254, 254, 254)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(183, 183, 183))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                                .addGap(174, 174, 174))
                            .addComponent(txtTelefone)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(183, 183, 183))
                            .addComponent(txtNome)
                            .addComponent(cbCidade, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(txtPesquisar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnOk))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnNovo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(46, 46, 46)
                                .addComponent(bntSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(65, 65, 65)
                                .addComponent(btnExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(59, 59, 59)))
                        .addGap(29, 29, 29)))
                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bntSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOk))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1)
                .addGap(13, 13, 13))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodActionPerformed

    private void txtCPFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCPFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPFActionPerformed

    private void bntSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntSalvarActionPerformed
        if (clienteSelecionadoId == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "Selecione um cliente na tabela para editar.");
            return;
        }

        try {
            Cliente cliente = clienteDAO.buscarPorId(clienteSelecionadoId);
            cliente.setNome(txtNome.getText());
            cliente.setCpf(txtCPF.getText());
            cliente.setEmail(txtEmail.getText());
            cliente.setTelefone(txtTelefone.getText());
            cliente.setCidade(getCidadeSelecionada());
            
            clienteDAO.atualizar(cliente);
            
            limparCampos();
            carregarTabela();
            javax.swing.JOptionPane.showMessageDialog(this, "Cliente atualizado com sucesso!");
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Erro ao atualizar cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_bntSalvarActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        if (txtNome.getText().trim().isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Preencha ao menos o nome antes de adicionar.");
            return;
        }

        try {
            Cliente cliente = new Cliente(txtNome.getText(), txtCPF.getText(), txtEmail.getText(), txtTelefone.getText(), getCidadeSelecionada());
            clienteDAO.salvar(cliente);
            
            limparCampos();
            carregarTabela();
            javax.swing.JOptionPane.showMessageDialog(this, "Cliente salvo com sucesso!");
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Erro ao salvar cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        String texto = txtPesquisar.getText().trim();

        if (texto.isEmpty()) {
            sorter.setRowFilter(null); 
        } else {
            sorter.setRowFilter(javax.swing.RowFilter.regexFilter("(?i)" + java.util.regex.Pattern.quote(texto), 1));
        }
    }//GEN-LAST:event_btnOkActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        if (clienteSelecionadoId == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "Selecione um cliente para excluir.");
            return;
        }
        
        int confirm = javax.swing.JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir este cliente?", "Confirmação", javax.swing.JOptionPane.YES_NO_OPTION);
        
        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
            try {
                clienteDAO.excluir(clienteSelecionadoId);
                limparCampos();
                carregarTabela();
                javax.swing.JOptionPane.showMessageDialog(this, "Cliente excluído com sucesso!");
            } catch (Exception e) {
                javax.swing.JOptionPane.showMessageDialog(this, "Erro ao excluir cliente: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void txtPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesquisarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesquisarActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new TelaCliente().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tabela;
    private javax.swing.JButton bntSalvar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnOk;
    private javax.swing.JComboBox<String> cbCidade;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtCPF;
    private javax.swing.JTextField txtCod;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtPesquisar;
    private javax.swing.JTextField txtTelefone;
    // End of variables declaration//GEN-END:variables
}
