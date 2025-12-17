
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
public class FormProducto extends javax.swing.JInternalFrame {
    Connection conex;
    Statement st;
    //JList<String> jList1;
    Producto cl=new Producto();
    private DefaultListModel listModel;
    private ArrayList<String> datos=new ArrayList<>();
    DefaultTableModel modelo = new DefaultTableModel();
    int i=0;
    /**
     * Creates new form Ejemplo
     */
    public FormProducto() {
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
        List<Producto> lista = new ArrayList<>();
        try {
            st = conex.createStatement();
            ResultSet rs = st.executeQuery("SELECT id_producto, nombre_producto, banda_producto, album_producto, edicion_producto, precio_producto, stock_producto, estado_producto FROM producto");
            while (rs.next()) {
                Producto producto = new Producto(rs.getInt("id_producto"), rs.getString("nombre_producto"), rs.getString("banda_producto"), rs.getString("album_producto"), rs.getString("edicion_producto"), rs.getDouble("precio_producto"), rs.getInt("stock_producto"), rs.getString("estado_producto"));
                lista.add(producto);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en SELECT: " + ex, "Error", JOptionPane.ERROR_MESSAGE);
        }

        modelo = (DefaultTableModel) jtbProducto.getModel();
        modelo.setRowCount(0);
        Object[] ob = new Object[8];
        for (Producto producto : lista) {
            ob[0] = producto.getId();
            ob[1] = producto.getNom();
            ob[2] = producto.getBanda();
            ob[3] = producto.getAlbum();
            ob[4] = producto.getEdicion();  
            ob[5] = producto.getPrecio();
            ob[6] = producto.getStock();
            ob[7] = producto.getEstado();
            modelo.addRow(ob);
        }
        jtbProducto.setModel(modelo);
    }
    
    public void actualizarProducto(int id,String nom,String banda,String album,String edicion,double precio,double stock,String estado) {
        try {
            st = conex.createStatement();
            String sql = "UPDATE producto SET nombre_producto = '" + nom + "',banda_producto = '" + banda + "',album_producto = '" + album + "',edicion_producto = '" + edicion + "', precio_producto = " + precio + ", stock_producto = " + stock + ", estado_producto = '" + estado + "' WHERE id_producto = " + id;
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Datos actualizados correctamente", "Actualizar", JOptionPane.INFORMATION_MESSAGE);
            listado(); 
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en UPDATE: " + ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
     private void agregarMouseListenerATabla() {
        jtbProducto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int filaSeleccionada = jtbProducto.getSelectedRow();
                if (filaSeleccionada != -1) {
                    cmbNom.setSelectedItem(jtbProducto.getValueAt(filaSeleccionada, 1).toString());
                    txtBanda.setText(jtbProducto.getValueAt(filaSeleccionada, 2).toString());
                    txtAlbum.setText(jtbProducto.getValueAt(filaSeleccionada, 3).toString());
                    txtEdicion.setText(jtbProducto.getValueAt(filaSeleccionada, 4).toString());
                    txtPrecio.setText(jtbProducto.getValueAt(filaSeleccionada, 5).toString());
                    txtStock.setText(jtbProducto.getValueAt(filaSeleccionada, 6).toString());
                    cmbEstado.setSelectedItem(jtbProducto.getValueAt(filaSeleccionada, 7).toString());
                }
            }
        });
    }
    
    public void eliminarProducto(int idProducto) {
    try {
        String sql = "DELETE FROM producto WHERE id_producto = ?";
        PreparedStatement pst = conex.prepareStatement(sql);
        pst.setInt(1, idProducto);
        pst.executeUpdate();

        JOptionPane.showMessageDialog(null, "Producto eliminado correctamente", "Eliminación exitosa", JOptionPane.INFORMATION_MESSAGE);

        listado();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error No se puede Eliminar este Producto por Ventas Comprometidas: ", "Error", JOptionPane.ERROR_MESSAGE);
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
        lblNombre = new javax.swing.JLabel();
        lblPrecio = new javax.swing.JLabel();
        cmbEstado = new javax.swing.JComboBox<>();
        lblStock = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        lblEstado = new javax.swing.JLabel();
        txtStock = new javax.swing.JTextField();
        lblTitulo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbProducto = new javax.swing.JTable();
        btnAgregar = new javax.swing.JButton();
        btnActulizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        cmbNom = new javax.swing.JComboBox<>();
        lblBanda = new javax.swing.JLabel();
        lblAlbum = new javax.swing.JLabel();
        txtBanda = new javax.swing.JTextField();
        txtAlbum = new javax.swing.JTextField();
        lblEdicion = new javax.swing.JLabel();
        txtEdicion = new javax.swing.JTextField();

        setTitle("MANTENEDOR PRODUCTO");

        btnSalirVenta.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSalirVenta.setText("VOLVER");
        btnSalirVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirVentaActionPerformed(evt);
            }
        });

        lblNombre.setText("NOMBRE");

        lblPrecio.setText("PRECIO");

        cmbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Inactivo", "Activo" }));
        cmbEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbEstadoActionPerformed(evt);
            }
        });

        lblStock.setText("STOCK");

        lblEstado.setText("ESTADO");

        txtStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStockActionPerformed(evt);
            }
        });

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitulo.setText("DATOS DE PRODUCTO");

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jtbProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "NOMBRE", "BANDA", "ALBUM", "EDICION", "PRECIO", "STOCK", "ESTADO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtbProducto);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
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

        cmbNom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Cd", "Cassette", "Vinilo 12 Pulgadas", "Vinilo 10 Pulgadas", "Vinilo 7 Pulgadas" }));

        lblBanda.setText("BANDA");

        lblAlbum.setText("ALBUM");

        lblEdicion.setText("EDICION");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(451, 451, 451)
                .addComponent(btnSalirVenta)
                .addGap(0, 24, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblEdicion)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblAlbum)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtAlbum, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblBanda)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtBanda, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNombre)
                                    .addComponent(lblPrecio)
                                    .addComponent(lblStock)
                                    .addComponent(lblEstado))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(36, 36, 36)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(txtStock, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                                                .addComponent(txtPrecio, javax.swing.GroupLayout.Alignment.LEADING)))
                                        .addGap(101, 101, 101))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cmbNom, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(txtEdicion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnActulizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(lblNombre)
                        .addGap(15, 15, 15))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbNom, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar)
                    .addComponent(txtBanda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBanda))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnActulizar)
                    .addComponent(txtAlbum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAlbum))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEdicion)
                    .addComponent(txtEdicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNuevo)
                    .addComponent(lblPrecio))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStock))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEstado))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSalirVenta)
                .addGap(19, 19, 19))
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
        String nom = "",album="",edicion="",banda="";
        String estado = "";
        double precio = 0;
        int id = 0;
        int stock = 0;

        nom = (String) cmbNom.getSelectedItem();
        banda=txtBanda.getText();
        album=txtAlbum.getText();
        edicion=txtEdicion.getText();
        try {
            precio = Double.parseDouble(txtPrecio.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Por favor ingrese un número válido para el precio", "Error de entrada", JOptionPane.ERROR_MESSAGE);
            return; 
        }

        try {
            stock = Integer.parseInt(txtStock.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Por favor ingrese un número válido para el stock", "Error de entrada", JOptionPane.ERROR_MESSAGE);
            return; 
        }

        estado = (String) cmbEstado.getSelectedItem();

        guardarProducto(id, nom, banda, album, edicion, precio, stock, estado);
                                 
    }//GEN-LAST:event_btnAgregarActionPerformed
    public void guardarProducto(int id,String nom,String banda,String album,String edicion,double precio,int stock,String estado){
        
        String inser="INSERT INTO producto (id_producto,nombre_producto,banda_producto,album_producto,edicion_producto,precio_producto,stock_producto,estado_producto)"
                +"VALUES ("+id+",'"+nom+"','"+banda+"','"+album+"','"+edicion+"',"+precio+","+stock+",'"+estado+"')";
        try{
            st=conex.createStatement();
            st.executeUpdate(inser);
            listado();
           
            JOptionPane.showMessageDialog(null,"datos insertados ","conection",1);
        }catch(SQLException ei){
            JOptionPane.showMessageDialog(null,"error insert "+ei,"insert",3);
        }
    }
  
    
    
    private void txtStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStockActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStockActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // Botón "NUEVO" (btnNuevo)
        cmbNom.setSelectedIndex(0);
        txtBanda.setText("");
        txtAlbum.setText("");
        txtEdicion.setText("");
        txtPrecio.setText("");
        txtStock.setText("");
        cmbEstado.setSelectedIndex(0); 
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnActulizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActulizarActionPerformed
        int filaSeleccionada = jtbProducto.getSelectedRow();
            if (filaSeleccionada >= 0) {
                int idProducto = (int) jtbProducto.getValueAt(filaSeleccionada, 0);
                String nom = cmbNom.getSelectedItem().toString();
                String banda = txtBanda.getText();
                String album = txtAlbum.getText();
                String edicion = txtEdicion.getText();
                String precio = txtPrecio.getText();
                String stock = txtStock.getText();
                String estado = cmbEstado.getSelectedItem().toString();

        try {
                String sql = "UPDATE producto SET nombre_producto = ?,banda_producto = ?,album_producto = ?,edicion_producto = ?, precio_producto = ?, stock_producto = ?, estado_producto = ? WHERE id_producto = ?";
                PreparedStatement pst = conex.prepareStatement(sql);
                pst.setString(1, nom);
                pst.setString(2, banda);
                pst.setString(3, album);
                pst.setString(4, edicion);
                pst.setString(5, precio);
                pst.setString(6, stock);
                pst.setString(7, estado);
                pst.setInt(8, idProducto);
                pst.executeUpdate();

                JOptionPane.showMessageDialog(null, "Producto actualizado correctamente", "Actualización exitosa", JOptionPane.INFORMATION_MESSAGE);

                listado();

                // Limpiar los campos de texto
                cmbNom.setSelectedIndex(0);
                txtBanda.setText("");
                txtAlbum.setText("");
                txtEdicion.setText("");            
                txtPrecio.setText("");
                txtStock.setText("");
                cmbEstado.setSelectedIndex(0);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el producto: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(null, "Debe seleccionar un producto de la tabla para actualizar", "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnActulizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int filaSeleccionada = jtbProducto.getSelectedRow();
    if (filaSeleccionada >= 0) {
        int idProducto = (int) jtbProducto.getValueAt(filaSeleccionada, 0);
        eliminarProducto(idProducto);//fila 120
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
    private javax.swing.JComboBox<String> cmbNom;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtbProducto;
    private javax.swing.JLabel lblAlbum;
    private javax.swing.JLabel lblBanda;
    private javax.swing.JLabel lblEdicion;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JLabel lblStock;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txtAlbum;
    private javax.swing.JTextField txtBanda;
    private javax.swing.JTextField txtEdicion;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtStock;
    // End of variables declaration//GEN-END:variables
}
