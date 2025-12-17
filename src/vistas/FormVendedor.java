
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
public class FormVendedor extends javax.swing.JInternalFrame {
    Connection conex;
    Statement st;
    //JList<String> jList1;
    Vendedor cl=new Vendedor();
    private DefaultListModel listModel;
    private ArrayList<String> datos=new ArrayList<>();
    DefaultTableModel modelo = new DefaultTableModel();
    int i=0;
    /**
     * Creates new form Ejemplo
     */
    public FormVendedor() {
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
        List<Vendedor> lista = new ArrayList<>();
        try {
            st = conex.createStatement();
            ResultSet rs = st.executeQuery("SELECT id_vendedor, rut_vendedor, nombre_vendedor, telefono_vendedor, estado_vendedor, user_vendedor FROM vendedor");
            while (rs.next()) {
                Vendedor vendedor = new Vendedor(rs.getInt("id_vendedor"), rs.getString("rut_vendedor"), rs.getString("nombre_vendedor"), rs.getString("telefono_vendedor"), rs.getString("estado_vendedor"), rs.getString("user_vendedor"));
                lista.add(vendedor);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en SELECT: " + ex, "Error", JOptionPane.ERROR_MESSAGE);
        }

        modelo = (DefaultTableModel) jtbVendedor.getModel();
        modelo.setRowCount(0);
        Object[] ob = new Object[6];
        for (Vendedor vendedor : lista) {
            ob[0] = vendedor.getId();
            ob[1] = vendedor.getRut();
            ob[2] = vendedor.getNom();
            ob[3] = vendedor.getTelefono();
            ob[4] = vendedor.getEstado();
            ob[5] = vendedor.getUser();
            modelo.addRow(ob);
        }
        jtbVendedor.setModel(modelo);
    }
    
    public void actualizarVendedor(int id, String rut, String nombre, String telefono, String estado, String user) {
        try {
            st = conex.createStatement();
            String sql = "UPDATE vendedor SET rut_vendedor = '" + rut + "', nombre_vendedor = '" + nombre + "', telefono_vendedor= '" + telefono + "', estado_vendedor = '" + estado + "', user_vendedor = '" + user + "' WHERE id_vendedor = " + id;
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Datos actualizados correctamente", "Actualizar", JOptionPane.INFORMATION_MESSAGE);
            listado(); 
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en UPDATE: " + ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
     private void agregarMouseListenerATabla() {
        jtbVendedor.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int filaSeleccionada = jtbVendedor.getSelectedRow();
                if (filaSeleccionada != -1) {
                    txtRut.setText(jtbVendedor.getValueAt(filaSeleccionada, 1).toString());
                    txtNombre.setText(jtbVendedor.getValueAt(filaSeleccionada, 2).toString());
                    txtTelefono.setText(jtbVendedor.getValueAt(filaSeleccionada, 3).toString());
                    cmbEstado.setSelectedItem(jtbVendedor.getValueAt(filaSeleccionada, 4).toString());
                    txtUser.setText(jtbVendedor.getValueAt(filaSeleccionada, 5).toString());
                }
            }
        });
    }
    
    public void eliminarVendedor(int idVendedor) {
    try {
        String sql = "DELETE FROM vendedor WHERE id_vendedor = ?";
        PreparedStatement pst = conex.prepareStatement(sql);
        pst.setInt(1, idVendedor);
        pst.executeUpdate();
        JOptionPane.showMessageDialog(null, "Vendedor eliminado correctamente", "Eliminación exitosa", JOptionPane.INFORMATION_MESSAGE);
        listado();

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error No se puede Eliminar este Vendedor por Ventas Comprometidas: ", "Error", JOptionPane.ERROR_MESSAGE);
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
        lblTelefono = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lblEstado = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        lblTitulo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbVendedor = new javax.swing.JTable();
        btnAgregar = new javax.swing.JButton();
        btnActulizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        lblUsuario = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();

        setTitle("MANTENEDOR VENDEDOR");

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

        lblTelefono.setText("TELEFONO");

        lblEstado.setText("ESTADO");

        txtTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoActionPerformed(evt);
            }
        });

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitulo.setText("DATOS DEL VENDEDOR");

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jtbVendedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "RUT", "NOMBRE", "TELEFONO", "ESTADO", "USUARIO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtbVendedor);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnAgregar.setText("AGREGAR");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnActulizar.setText("ACTUALIZAR");
        btnActulizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActulizarActionPerformed(evt);
            }
        });

        btnEliminar.setText("ELIMINAR");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnNuevo.setText("NUEVO");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        lblUsuario.setText("USUARIO");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(147, 147, 147)
                        .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSalirVenta))))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblRut)
                    .addComponent(lblNombre)
                    .addComponent(lblTelefono)
                    .addComponent(lblEstado)
                    .addComponent(lblUsuario))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRut, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnActulizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRut)
                    .addComponent(txtRut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregar))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActulizar))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTelefono)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEstado)
                    .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNuevo))
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsuario)
                    .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
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

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        String rut="",nom="",telefono="",estado="",user="";
        int id=0;
        
        rut=txtRut.getText();
        nom=txtNombre.getText();
        telefono=txtTelefono.getText();
        estado=(String) cmbEstado.getSelectedItem();
        user=txtUser.getText();
        
        guardar(id,rut,nom,telefono,estado,user);
                                 
    }//GEN-LAST:event_btnAgregarActionPerformed
    public void guardar(int id,String rut,String nom,String telefono,String estado,String user){
        
        String inser="INSERT INTO vendedor (id_vendedor,rut_vendedor,nombre_vendedor,telefono_vendedor,estado_vendedor,user_vendedor)"
                +"VALUES ('"+id+"','"+rut+"','"+nom+"','"+telefono+"','"+estado+"','"+user+"')";
        try{
            st=conex.createStatement();
            st.executeUpdate(inser);
            listado();           
            JOptionPane.showMessageDialog(null,"datos insertados ","conection",1);
        }catch(SQLException ei){
            JOptionPane.showMessageDialog(null,"error insert "+ei,"insert",3);
        }
    }
  
    
    
    private void txtTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
      
    txtRut.setText("");
    txtNombre.setText("");
    txtTelefono.setText("");
    cmbEstado.setSelectedIndex(0); 
    txtUser.setText("");
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnActulizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActulizarActionPerformed
        int filaSeleccionada = jtbVendedor.getSelectedRow();
    if (filaSeleccionada >= 0) {
        int idVendedor = (int) jtbVendedor.getValueAt(filaSeleccionada, 0);
        String rut = txtRut.getText();
        String nombre = txtNombre.getText();
        String telefono = txtTelefono.getText();
        String estado = cmbEstado.getSelectedItem().toString();
        String user = txtUser.getText();

        try {
            String sql = "UPDATE vendedor SET rut_vendedor = ?, nombre_vendedor = ?, telefono_vendedor = ?, estado_vendedor = ? , user_vendedor = ? WHERE id_vendedor = ?";
            PreparedStatement pst = conex.prepareStatement(sql);
            pst.setString(1, rut);
            pst.setString(2, nombre);
            pst.setString(3, telefono);
            pst.setString(4, estado);
            pst.setString(5, user);
            pst.setInt(6, idVendedor);
             
            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Vendedor actualizado correctamente", "Actualización exitosa", JOptionPane.INFORMATION_MESSAGE);

            listado();
           
            txtRut.setText("");
            txtNombre.setText("");
            txtTelefono.setText("");
            cmbEstado.setSelectedIndex(0);
            txtUser.setText("");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el vendedor: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(null, "Debe seleccionar un cliente de la tabla para actualizar", "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnActulizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int filaSeleccionada = jtbVendedor.getSelectedRow();
    if (filaSeleccionada >= 0) {
        int idVendedor = (int) jtbVendedor.getValueAt(filaSeleccionada, 0);
        eliminarVendedor(idVendedor);//fila 117
    } else {
        JOptionPane.showMessageDialog(null, "Debe seleccionar un cliente de la tabla para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnEliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgCliente;
    private javax.swing.JButton btnActulizar;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalirVenta;
    private javax.swing.JComboBox<String> cmbEstado;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtbVendedor;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblRut;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtRut;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
