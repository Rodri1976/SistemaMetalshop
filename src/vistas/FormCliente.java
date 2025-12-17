
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package vistas;
//import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.sql.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 *
 * @author rfigu
 */
public class FormCliente extends javax.swing.JInternalFrame {
    Connection conex;
    Statement st;
    //JList<String> jList1;
    Cliente cl=new Cliente();
    private DefaultListModel listModel;
    private ArrayList<String> datos=new ArrayList<>();
    DefaultTableModel modelo = new DefaultTableModel();
    int i=0;
    /**
     * Creates new form Ejemplo
     */
    public FormCliente() {
        conectar();
        //Principall();
        initComponents();
        //llenaCombo();
        //listar();
        listado();
        agregarMouseListenerATabla();
    }
    public void conectar(){
      String url="jdbc:mysql://localhost:3306/metalshop";
      String usuario="root";
      String pass="";
      try{
          conex=DriverManager.getConnection(url,usuario,pass);
          //JOptionPane.showMessageDialog(null,"conectado","conection",1);
      }catch(Exception ex){
          System.out.println("ERROR EN CONEXION");
      }   
    }
    
    
    public void listado() {
        List<Cliente> lista = new ArrayList<>();
        try {
            st = conex.createStatement();
            ResultSet rs = st.executeQuery("SELECT id_cliente, rut_cliente, nombre_cliente, direccion_cliente, estado_cliente FROM cliente");
            while (rs.next()) {
                Cliente cliente = new Cliente(rs.getInt("id_cliente"), rs.getString("rut_cliente"), rs.getString("nombre_cliente"), rs.getString("direccion_cliente"), rs.getString("estado_cliente"));
                lista.add(cliente);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en SELECT: " + ex, "Error", JOptionPane.ERROR_MESSAGE);
        }

        modelo = (DefaultTableModel) jtbCliente.getModel();
        modelo.setRowCount(0);
        Object[] ob = new Object[5];
        for (Cliente cliente : lista) {
            ob[0] = cliente.getId();
            ob[1] = cliente.getRut();
            ob[2] = cliente.getNom();
            ob[3] = cliente.getDir();
            ob[4] = cliente.getEstado();
            modelo.addRow(ob);
        }
        jtbCliente.setModel(modelo);
        
    }
    
    public void actualizarCliente(int id, String rut, String nombre, String direccion, String estado) {
        try {
            st = conex.createStatement();
            String sql = "UPDATE cliente SET rut_cliente = '" + rut + "', nombre_cliente = '" + nombre + "', direccion_cliente = '" + direccion + "', estado_cliente = '" + estado + "' WHERE id_cliente = " + id;
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Datos actualizados correctamente", "Actualizar", JOptionPane.INFORMATION_MESSAGE);
            listado();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en UPDATE: " + ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
     private void agregarMouseListenerATabla() {
        jtbCliente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int filaSeleccionada = jtbCliente.getSelectedRow();
                if (filaSeleccionada != -1) {
                    txtRut.setText(jtbCliente.getValueAt(filaSeleccionada, 1).toString());
                    txtNombre.setText(jtbCliente.getValueAt(filaSeleccionada, 2).toString());
                    txtDireccion.setText(jtbCliente.getValueAt(filaSeleccionada, 3).toString());
                    cmbEstado.setSelectedItem(jtbCliente.getValueAt(filaSeleccionada, 4).toString());
                }
            }
        });
    }
    
    public void eliminarCliente(int idCliente) {
    try {
        String sql = "DELETE FROM cliente WHERE id_cliente = ?";
        PreparedStatement pst = conex.prepareStatement(sql);
        pst.setInt(1, idCliente);
        pst.executeUpdate();

        JOptionPane.showMessageDialog(null, "Cliente eliminado correctamente", "Eliminación exitosa", JOptionPane.INFORMATION_MESSAGE);

        listado();

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error No se puede Eliminar este Cliente por Ventas Comprometidas: ", "Error", JOptionPane.ERROR_MESSAGE);
    }
    } 
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgCliente = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        btnSalirVenta = new javax.swing.JButton();
        txtRut = new javax.swing.JTextField();
        lblRut = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        cmbEstado = new javax.swing.JComboBox<>();
        lblDir = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lblEstado = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbCliente = new javax.swing.JTable();
        cmdAgregar = new javax.swing.JButton();
        cmdActulizar = new javax.swing.JButton();
        cmdEliminar = new javax.swing.JButton();
        cmdNuevo = new javax.swing.JButton();

        setTitle("MANTENEDOR CLIENTE");

        btnSalirVenta.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSalirVenta.setText("VOLVER");
        btnSalirVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirVentaActionPerformed(evt);
            }
        });

        lblRut.setText("RUT ");

        lblNombre.setText("NOMBRE");

        cmbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Inactivo", "Activo" }));
        cmbEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbEstadoActionPerformed(evt);
            }
        });

        lblDir.setText("DIRECCION");

        lblEstado.setText("ESTADO");

        txtDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDireccionActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel13.setText("DATOS DEL CLIENTE");

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jtbCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "RUT", "NOMBRE", "DIRECCION", "ESTADO"
            }
        ));
        jScrollPane1.setViewportView(jtbCliente);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        cmdAgregar.setText("AGREGAR");
        cmdAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAgregarActionPerformed(evt);
            }
        });

        cmdActulizar.setText("ACTUALIZAR");
        cmdActulizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdActulizarActionPerformed(evt);
            }
        });

        cmdEliminar.setText("ELIMINAR");
        cmdEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdEliminarActionPerformed(evt);
            }
        });

        cmdNuevo.setText("NUEVO");
        cmdNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdNuevoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblRut)
                    .addComponent(lblNombre)
                    .addComponent(lblDir)
                    .addComponent(lblEstado))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRut, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(cmdActulizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmdEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmdNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(cmdAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(147, 147, 147)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(30, 30, 30)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(btnSalirVenta)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRut)
                    .addComponent(txtRut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdAgregar))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdActulizar))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDir)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdEliminar))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEstado)
                    .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdNuevo))
                .addGap(28, 28, 28)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(30, 30, 30)
                .addComponent(btnSalirVenta)
                .addGap(37, 37, 37))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbEstadoActionPerformed

    private void btnSalirVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirVentaActionPerformed

        this.setVisible(false);
    }//GEN-LAST:event_btnSalirVentaActionPerformed

    private void cmdAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAgregarActionPerformed
        String rut="",nom="",dir="",estado="";
        int id=0;        
        rut=txtRut.getText();
        nom=txtNombre.getText();
        dir=txtDireccion.getText();
        estado=(String) cmbEstado.getSelectedItem();        
        guardar(id,rut,nom,dir,estado);
                                 
    }//GEN-LAST:event_cmdAgregarActionPerformed
    public void guardar(int id,String rut,String nom,String dir,String estado){
        
        String inser="INSERT INTO cliente (id_cliente,rut_cliente,nombre_cliente,direccion_cliente,estado_cliente)"
                +"VALUES ('"+id+"','"+rut+"','"+nom+"','"+dir+"','"+estado+"')";
        try{
            st=conex.createStatement();
            st.executeUpdate(inser);
            listado();            
            JOptionPane.showMessageDialog(null,"datos insertados ","conection",1);
        }catch(SQLException ei){
            JOptionPane.showMessageDialog(null,"error insert "+ei,"insert",3);
        }
    }
  
    
    
    private void txtDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionActionPerformed

    private void cmdNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdNuevoActionPerformed
       
    txtRut.setText("");
    txtNombre.setText("");
    txtDireccion.setText("");
    cmbEstado.setSelectedIndex(0); 
    }//GEN-LAST:event_cmdNuevoActionPerformed

    private void cmdActulizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdActulizarActionPerformed
        int filaSeleccionada = jtbCliente.getSelectedRow();
    if (filaSeleccionada >= 0) {
        int idCliente = (int) jtbCliente.getValueAt(filaSeleccionada, 0);
        String rut = txtRut.getText();
        String nombre = txtNombre.getText();
        String direccion = txtDireccion.getText();
        String estado = cmbEstado.getSelectedItem().toString();

        try {
            String sql = "UPDATE cliente SET rut_cliente = ?, nombre_cliente = ?, direccion_cliente = ?, estado_cliente = ? WHERE id_cliente = ?";
            PreparedStatement pst = conex.prepareStatement(sql);
            pst.setString(1, rut);
            pst.setString(2, nombre);
            pst.setString(3, direccion);
            pst.setString(4, estado);
            pst.setInt(5, idCliente);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Cliente actualizado correctamente", "Actualización exitosa", JOptionPane.INFORMATION_MESSAGE);

            listado();
            // Limpiar los campos de texto
            txtRut.setText("");
            txtNombre.setText("");
            txtDireccion.setText("");
            cmbEstado.setSelectedIndex(0);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el cliente: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(null, "Debe seleccionar un cliente de la tabla para actualizar", "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_cmdActulizarActionPerformed

    private void cmdEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdEliminarActionPerformed
        int filaSeleccionada = jtbCliente.getSelectedRow();
    if (filaSeleccionada >= 0) {
        int idCliente = (int) jtbCliente.getValueAt(filaSeleccionada, 0);
        eliminarCliente(idCliente);//fila 116
    } else {
        JOptionPane.showMessageDialog(null, "Debe seleccionar un cliente de la tabla para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_cmdEliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgCliente;
    private javax.swing.JButton btnSalirVenta;
    private javax.swing.JComboBox<String> cmbEstado;
    private javax.swing.JButton cmdActulizar;
    private javax.swing.JButton cmdAgregar;
    private javax.swing.JButton cmdEliminar;
    private javax.swing.JButton cmdNuevo;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtbCliente;
    private javax.swing.JLabel lblDir;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblRut;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtRut;
    // End of variables declaration//GEN-END:variables
}
