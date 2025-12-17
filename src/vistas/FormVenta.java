/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package vistas;
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
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
//import java.util.HashMap;


/**
 *
 * @author rfigu
 */
public class FormVenta extends javax.swing.JInternalFrame {
    Connection conex;
    PreparedStatement ps;
    ResultSet rs;
    Statement st;
    //Conexion cn = new Conexion();
    Producto p=new Producto();
    //JList<String> jList1;
    Cliente cl=new Cliente();
    Venta v=new Venta();
    Detalle dv=new Detalle();
    Calendar calendar=new GregorianCalendar();
    private DefaultListModel listModel;
    private ArrayList<String> datos=new ArrayList<>();
    DefaultTableModel modelo = new DefaultTableModel();
    int i=0, r=0;
    int idp,cant,cantidad,stock,newStock;
    double tPagar,pre;
    
    
    public FormVenta() {
        initComponents(); 
        conectar();
        
        //fecha();        
        inicializarNumeroSerie();
        llenaCombo();
        buscarProducto();
        //obtenerIdVendedor();
        Calendar calendar=new GregorianCalendar();
        txtFecha.setText(""+calendar.get(Calendar.DAY_OF_MONTH)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.YEAR));
        //Principall();
        //initComponents();
        //llenaCombo();
        //listar();
        //listado();
        //agregarMouseListenerATabla();
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
    
        
    public void inicializarNumeroSerie(){
    String serie = NroSerieVentas();
    
    if (serie == null || serie.isEmpty()) {
        txtSerie.setText("0000001");
        } else {
            int increment = Integer.parseInt(serie) + 1;
            String nuevaSerie = String.format("%07d", increment);
            txtSerie.setText(nuevaSerie);
        }
    }        
    
    public String NroSerieVentas(){
        String serie = "";
        String sql = "SELECT MAX(CAST(numero_venta AS UNSIGNED)) FROM venta";
    
        try {
            conectar(); 
            ps = conex.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                serie = rs.getString(1);
                if (serie == null) {
                    serie = "0"; 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return serie;
    }       
         
    public Cliente listarRut(String rut){
        Cliente c=new Cliente();
        String sql="select * from cliente where rut_cliente=?";        
        try {
            conectar();          
            ps=conex.prepareStatement(sql);
            ps.setString(1,rut);
            rs=ps.executeQuery();
            while (rs.next()){
                c.setId(rs.getInt(1));
                c.setRut(rs.getString(2));
                c.setNom(rs.getString(3));
                c.setEstado(rs.getString(4));  
            }
        }catch(Exception e){            
        }
        return c;   
    }
    
    
    public Vendedor listarRutV(int id){
        Vendedor v=new Vendedor();
        String sql="select * from vendedor where id_vendedor=?";        
        try {
            conectar();           
            ps=conex.prepareStatement(sql);
            ps.setInt(1,id);
            rs=ps.executeQuery();
            while (rs.next()){
                v.setId(rs.getInt(1));
                v.setRut(rs.getString(2));
                v.setNom(rs.getString(3));
                v.setTelefono(rs.getString(4));
                v.setEstado(rs.getString(5));
                v.setUser(rs.getString(6));             
            }
        }catch(Exception e){            
        }
        return v;   
    }
    
    
    public int actualizarStock(int cant,int idp){
        String sql="update producto set stock_producto=? where id_producto=?";
        try {
             conectar();            
            ps=conex.prepareStatement(sql);
            ps.setInt(1,cant);
            ps.setInt(2,idp);
            ps.executeUpdate();
        }catch (Exception e) {
            
        }
        return r;     
    }
    
    public Producto listarID(int id){
        Producto p=new Producto();
        String sql="select * from producto where id_producto=?";
        try {
            conectar();
            ps=conex.prepareStatement(sql);
            ps.setInt(1, id);
            rs=ps.executeQuery();
            while (rs.next()){
                p.setId(rs.getInt(1));
                p.setNom(rs.getString(2));
                p.setBanda(rs.getString(3));
                p.setAlbum(rs.getString(4));
                p.setEdicion(rs.getString(5));
                p.setPrecio(rs.getDouble(6));
                p.setStock(rs.getInt(7));
                p.setEstado(rs.getString(8));
                
            }
        }catch(Exception e){
            
        }
        return p;
        
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblSubTitulo = new javax.swing.JLabel();
        lblN = new javax.swing.JLabel();
        txtSerie = new javax.swing.JTextField();
        txtFecha = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        lblRCliente = new javax.swing.JLabel();
        lblPrecio = new javax.swing.JLabel();
        lblCantidad = new javax.swing.JLabel();
        txtRutCliente = new javax.swing.JTextField();
        txtPrecio = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        lblCliente = new javax.swing.JLabel();
        lblStock = new javax.swing.JLabel();
        txtNomCliente = new javax.swing.JTextField();
        txtStock = new javax.swing.JTextField();
        lblVendedor = new javax.swing.JLabel();
        txtNomProducto = new javax.swing.JTextField();
        lblNombre = new javax.swing.JLabel();
        lblBanda = new javax.swing.JLabel();
        lblAlbum = new javax.swing.JLabel();
        txtBanda = new javax.swing.JTextField();
        txtAlbum = new javax.swing.JTextField();
        cmbNomVendedor = new javax.swing.JComboBox<>();
        txtIdCliente = new javax.swing.JTextField();
        txtIdVendedor = new javax.swing.JTextField();
        cmbListProduc = new javax.swing.JComboBox<>();
        btnGenerarV = new javax.swing.JButton();
        txtCodProducto = new javax.swing.JTextField();
        txtCantidad = new javax.swing.JTextField();
        lblProducto = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaVenta = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        txtTotal = new javax.swing.JTextField();
        lblTPagar = new javax.swing.JLabel();
        btnGenerarVenta = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setTitle("VENTAS");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTitulo.setText("VENTA METALSHOP");

        lblSubTitulo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSubTitulo.setText("Venta de Musica Metal");

        lblN.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblN.setText("N° : ");

        txtSerie.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtSerie.setEnabled(false);

        txtFecha.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtFecha.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(173, 173, 173)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(lblN)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTitulo)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(lblSubTitulo)))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblTitulo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSubTitulo)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSerie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblN))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblRCliente.setText("Rut Cliente");

        lblPrecio.setText("Precio");

        lblCantidad.setText("Cantidad");

        txtPrecio.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtPrecio.setEnabled(false);

        jButton1.setText("BUSCAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lblCliente.setText("Cliente");

        lblStock.setText("Stock");

        txtNomCliente.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtNomCliente.setEnabled(false);

        txtStock.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtStock.setEnabled(false);

        lblVendedor.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblVendedor.setText("Vendedor");

        txtNomProducto.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtNomProducto.setEnabled(false);

        lblNombre.setText("Nombre");

        lblBanda.setText("Banda");

        lblAlbum.setText("Album");

        txtBanda.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtBanda.setEnabled(false);

        txtAlbum.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtAlbum.setEnabled(false);

        cmbNomVendedor.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cmbNomVendedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione" }));
        cmbNomVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbNomVendedorActionPerformed(evt);
            }
        });

        txtIdCliente.setBackground(new java.awt.Color(204, 204, 204));
        txtIdCliente.setForeground(new java.awt.Color(204, 204, 204));
        txtIdCliente.setEnabled(false);

        txtIdVendedor.setBackground(new java.awt.Color(204, 204, 204));
        txtIdVendedor.setForeground(new java.awt.Color(204, 204, 204));
        txtIdVendedor.setEnabled(false);

        cmbListProduc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione un Producto" }));

        btnGenerarV.setBackground(new java.awt.Color(204, 204, 204));
        btnGenerarV.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnGenerarV.setText("GENERAR VENTA");
        btnGenerarV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarVActionPerformed(evt);
            }
        });

        txtCodProducto.setBackground(new java.awt.Color(204, 204, 204));
        txtCodProducto.setEnabled(false);

        lblProducto.setText("Seleccione Producto");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblNombre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtBanda, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblRCliente)
                            .addComponent(lblVendedor)
                            .addComponent(lblCantidad))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(btnGenerarV))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cmbNomVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtNomProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblBanda)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(lblAlbum)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lblPrecio)
                                                .addComponent(lblStock)))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(txtStock, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                                                    .addComponent(txtPrecio))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                                        .addComponent(txtIdVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(txtCodProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                            .addComponent(txtAlbum, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtRutCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblCliente)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtNomCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(lblProducto)
                        .addGap(18, 18, 18)
                        .addComponent(cmbListProduc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblRCliente)
                        .addComponent(txtRutCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNomCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblCliente)))
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbListProduc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblProducto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBanda)
                    .addComponent(txtBanda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNomProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAlbum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAlbum)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCantidad))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 10, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblStock)
                            .addComponent(txtIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbNomVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblVendedor))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIdVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPrecio)
                            .addComponent(txtCodProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGenerarV)
                        .addGap(4, 4, 4)))
                .addContainerGap())
        );

        tablaVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NRO", "COD", "PRODUCTO", "BANDA", "ALBUM", "CANTIDAD", "PRECIO UNI", "TOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaVenta);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtTotal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        lblTPagar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTPagar.setText("TOTAL A PAGAR");

        btnGenerarVenta.setBackground(new java.awt.Color(204, 204, 204));
        btnGenerarVenta.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnGenerarVenta.setText("GENERAR COMPRA");
        btnGenerarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarVentaActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(btnCancelar)
                .addGap(33, 33, 33)
                .addComponent(btnGenerarVenta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTPagar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGenerarVenta)
                    .addComponent(lblTPagar)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        buscarCliente();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnGenerarVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarVActionPerformed
      
        try {
            String cantidadText = txtCantidad.getText().trim(); 
            String stockText = txtStock.getText().trim();    
            cantidad = Integer.parseInt(cantidadText);
            stock = Integer.parseInt(stockText);          
            if ((cantidad <= stock) &&(stock>=0)&&(cantidad >=0)){
                 newStock= stock-cantidad; 
                 String newStockText = Integer.toString(newStock);
                 txtStock.setText(newStockText);
                 agregarProducto();
            } else {               
                 JOptionPane.showMessageDialog(null, "Error: El Stock no puede quedar en 0 ", "Error", JOptionPane.ERROR_MESSAGE);
            }
            } catch (NumberFormatException e) {
               
                JOptionPane.showMessageDialog(null, "Error: Ingrese números válidos", "Error", JOptionPane.ERROR_MESSAGE);
            }
    }//GEN-LAST:event_btnGenerarVActionPerformed

    private void btnGenerarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarVentaActionPerformed
        if(txtTotal.getText().equals("")){
            JOptionPane.showMessageDialog(this,"Debe Ingresar Datos");
        }else{
            guardarVenta();
            guardarDetalle();
            actualizarStock();
            JOptionPane.showMessageDialog(this,"Se Realizo con Exito");
            nuevo();
            inicializarNumeroSerie();
        }    
    }//GEN-LAST:event_btnGenerarVentaActionPerformed
    public void nuevo(){
        limpiarTabla();
        txtRutCliente.setText("");
        txtCodProducto.setText("");
        txtPrecio.setText(""); 
        txtNomProducto.setText("");
        //txtNomVendedor.setText("");
        txtNomCliente.setText("");
        txtBanda.setText("");
        txtAlbum.setText("");
        txtStock.setText("");
        txtCantidad.setText("");
        txtRutCliente.requestFocus();
        this.setVisible(false);        
              
        
    }
    
    
    public void limpiarTabla(){
        for(int i = 0; i< modelo.getRowCount();i++){
            modelo.removeRow(i);
            i=i-1;
        }
    }
    
    
    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        
        this.setVisible(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void cmbNomVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbNomVendedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbNomVendedorActionPerformed
    
    void actualizarStock(){
        for (int i = 0; i<modelo.getRowCount(); i++){
            Producto pr=new Producto();
            idp=Integer.parseInt(tablaVenta.getValueAt(i,1).toString());
            cant=Integer.parseInt(tablaVenta.getValueAt(i,5).toString());
            pr=listarID(idp);
            int sa=pr.getStock()-cant;
            actualizarStock(sa,idp);
        } 
    }
    
    
    
    public void guardarVenta(){
        try {        
        
        String idClienteText = txtIdCliente.getText();
        int idCli = Integer.parseInt(idClienteText);
        String idVendedorText = txtIdVendedor.getText();
        int idVen = Integer.parseInt(idVendedorText);
        String serie = txtSerie.getText();
        String fecha = txtFecha.getText();
        double monto = tPagar;
        String estado = "Activo"; 
      
        System.out.println("Serie: " + serie);
        System.out.println("Fecha: " + fecha);
        System.out.println("Monto: " + monto);
       
        if (serie == null || serie.isEmpty()) {
            throw new IllegalArgumentException("Serie no esta vacio");
        }
        if (fecha == null || fecha.isEmpty()) {
            throw new IllegalArgumentException("Fecha no esta vacia");
        }
      
        Venta v = new Venta();
        v.setId_Cliente(idCli);
        v.setId_Vendedor(idVen);
        v.setNum(serie);
        v.setFecha(fecha);
        v.setMonto(monto);
        v.setEstado(estado);
        GuardarVentas(v);
       
        } catch (Exception e) {           
            System.err.println("Error al guardar la venta: " + e.getMessage());
            e.printStackTrace();
        }
    }
       
        
    public void guardarDetalle(){
         String idVentaStr = id_venta();
    if (idVentaStr == null || idVentaStr.trim().isEmpty()) {
        System.err.println("Error: ID de venta es nulo o vacío.");
        return;
    } else {
        //System.out.println("ID de venta obtenido: " + idVentaStr);
    }

    try {
        int ventaId = Integer.parseInt(idVentaStr);
        int rowCount = tablaVenta.getRowCount();
        for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
            String productoIdStr = getCellValueAsString(rowIndex, 1);
            String cantidadStr = getCellValueAsString(rowIndex, 5);
            String precioStr = getCellValueAsString(rowIndex, 6);

            if (productoIdStr.isEmpty() || cantidadStr.isEmpty() || precioStr.isEmpty()) {
                System.err.println("Error: Fila " + rowIndex + " contiene valores vacíos.");
                continue;
            }

            try {
                int productoId = Integer.parseInt(productoIdStr);
                int cantidad = Integer.parseInt(cantidadStr);
                double precio = Double.parseDouble(precioStr);
                dv.setId_Venta(ventaId);
                dv.setId_Producto(productoId);
                dv.setCantidad(cantidad);
                dv.setPreVenta(precio);
                GuardarDetalleVentas(dv);
            } catch (NumberFormatException e) {
                System.err.println("Error al convertir un valor numérico en la fila " + rowIndex + ": " + e.getMessage());
                continue;
            }
        }
    } catch (NumberFormatException e) {
        System.err.println("Error al convertir el ID de venta: " + e.getMessage());
    } catch (Exception e) {
        System.err.println("Ocurrió un error inesperado: " + e.getMessage());
    }
}

    private String getCellValueAsString(int rowIndex, int columnIndex) {
        Object cellValue = tablaVenta.getValueAt(rowIndex, columnIndex);
        return cellValue != null ? cellValue.toString().trim() : "";
    }
        
    
    public void agregarProducto(){
        double total;
        int item=0;
        modelo=(DefaultTableModel)tablaVenta.getModel();
        item=item+1;       
        String cod=txtCodProducto.getText();
        String nomp=txtNomProducto.getText();
        String ban=txtBanda.getText();
        String alb=txtAlbum.getText();        
        double pre=Double.parseDouble(txtPrecio.getText());
        int cant=Integer.parseInt(txtCantidad.getText());        
        int stock=Integer.parseInt(txtStock.getText());
        total=cant*pre;
        ArrayList lista=new ArrayList();
        if (stock>=0){
            lista.add(item);
            lista.add(cod);
            lista.add(nomp);
            lista.add(ban);
            lista.add(alb);
            lista.add(cant);
            lista.add(pre);
            lista.add(total);
            Object[]ob=new Object[8];
            ob[0]=lista.get(0);
            ob[1]=lista.get(1);
            ob[2]=lista.get(2);
            ob[3]=lista.get(3);
            ob[4]=lista.get(4);
            ob[5]=lista.get(5);
            ob[6]=lista.get(6);
            ob[7]=lista.get(7);            
            modelo.addRow(ob);
            tablaVenta.setModel(modelo);
            calcularTotal();
        }else{
            JOptionPane.showMessageDialog(this,"Stock Producto No disponible");
        }
    }
    public void calcularTotal(){
      tPagar=0;  
      for (int i=0;i<tablaVenta.getRowCount();i++){
          cant=Integer.parseInt(tablaVenta.getValueAt(i,5).toString());
          pre=Double.parseDouble(tablaVenta.getValueAt(i,6).toString());
          tPagar=tPagar+(cant*pre);
      }  
          txtTotal.setText(""+tPagar);
    }
   
    public void buscarProducto() {
    try {        
        st = conex.createStatement();
        ResultSet list = st.executeQuery("SELECT id_producto,nombre_producto,banda_producto, album_producto,edicion_producto,precio_producto,stock_producto FROM producto");       
        while (list.next()) {
            String idProduc = list.getString("id_producto");
            String nomProduc = list.getString("nombre_producto");
            String bandProduc = list.getString("banda_producto");
            String albumProduc = list.getString("album_producto");
            String edicionProduc = list.getString("edicion_producto");
            String precioProduc = String.valueOf(list.getDouble("precio_producto"));
            String stockProductoStr = String.valueOf(list.getInt("stock_producto"));
            
            
            String prodCompleto = idProduc + " " + nomProduc + " " + bandProduc + " "+ albumProduc + " " + edicionProduc + " " +precioProduc + " " + stockProductoStr;
            cmbListProduc.addItem(prodCompleto);
        }        
        cmbListProduc.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {                    
                    String codSeleccionado = (String) cmbListProduc.getSelectedItem();
                   
                    if (codSeleccionado != null && !codSeleccionado.isEmpty()) {
                        String idProductoStr = codSeleccionado.split(" ")[0];    
                        int codigoSeleccionado = Integer.parseInt(idProductoStr);                     
                        
                        Producto p = listarID(codigoSeleccionado);
                        if(p.getId()!=0){ 
                            txtCodProducto.setText(""+p.getId());
                            txtNomProducto.setText(p.getNom());                            
                            txtBanda.setText(p.getBanda());
                            txtAlbum.setText(p.getAlbum());                           
                            txtStock.setText(""+p.getStock());
                            txtPrecio.setText(""+p.getPrecio());
                        } else {
                            JOptionPane.showMessageDialog(null,"Producto no registrado");
                            
                        }
                    }
                }
            }
        });
        } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error al cargar vendedores: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
      
    
    public void llenaCombo() {
    try {        
        st = conex.createStatement();
        ResultSet lista = st.executeQuery("SELECT id_vendedor,nombre_vendedor  FROM vendedor");       
        while (lista.next()) {
            String idVendedor = lista.getString("id_vendedor");
            String nomVendedor = lista.getString("nombre_vendedor");
            String vendCompleto = idVendedor + " " + nomVendedor;
            cmbNomVendedor.addItem(vendCompleto);
            
        }        
        cmbNomVendedor.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {                    
                    String nomSeleccionado = (String) cmbNomVendedor.getSelectedItem();
                    if (nomSeleccionado != null && !nomSeleccionado.isEmpty()) {
                        String idVendedorStr = nomSeleccionado.split(" ")[0];    
                        int codVSeleccionado = Integer.parseInt(idVendedorStr); 
                    
                        Vendedor v = listarRutV(codVSeleccionado);
                        if(v.getId()!=0){
                            txtIdVendedor.setText(""+v.getId());                           
                        } else {
                            JOptionPane.showMessageDialog(null,"Producto no registrado");
                        }
                    }
                }
            }
        });

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error al cargar vendedores: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
    
  
    
    public void buscarCliente(){
       int r;
       String cod=txtRutCliente.getText(); 
       if(txtRutCliente.getText().equals("")){
           JOptionPane.showMessageDialog(this,"Debe ingresar Rut del Cliente");
       }else{
        //System.out.println("Buscando cliente...");   
        Cliente cliente=listarRut(cod);
        if(cliente.getRut()!=null){
            txtNomCliente.setText(cliente.getNom());
            txtIdCliente.setText(String.valueOf(cliente.getId()));
            txtCodProducto.requestFocus();
            
        }else{
           r=JOptionPane.showConfirmDialog(this,"Cliente No Registrado, Desea Registrar ?"); 
           if(r == 0){
               FormCliente cf=new FormCliente();
               Principal.VentanaP.add(cf);
               cf.setVisible(true);
           }
        }
       }
        
    }
    public String id_venta(){
            String idv = "";
            String sql = "select max(id_venta) from venta"; 
            try {
                conectar();
                ps = conex.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    idv = rs.getString(1);
                }
                if (idv == null) {
                    idv = ""; 
                }
            } catch (Exception e) {
                System.err.println("Error al ejecutar la consulta: " + e.getMessage());
            } finally {
                try {
                    if (rs != null) rs.close();
                    if (ps != null) ps.close();
                    if (conex != null) conex.close();
                } catch (Exception e) {
                    System.err.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
            return idv;
        }
        
    public int GuardarVentas(Venta v){        
      
        Venta venta=new Venta();
        String sql="insert into venta(cliente_venta,vendedor_venta,numero_venta,fecha_venta,monto_venta,estado_venta)values(?,?,?,?,?,?)";
        try {
            conectar();
            ps=conex.prepareStatement(sql);            
            ps.setInt(1,v.getId_Cliente());
            ps.setInt(2,v.getId_Vendedor());
            ps.setString(3,v.getNum());
            ps.setString(4,v.getFecha());
            ps.setDouble(5,v.getMonto());
            ps.setString(6,v.getEstado());
            r=ps.executeUpdate();
        }catch (Exception e){
            
        }
        return r;
    }
    public int GuardarDetalleVentas(Detalle dv){           
       
          String sql="insert into detalle(venta_detalle,producto_detalle,cantidad_detalle,precio_venta_detalle)values(?,?,?,?)";
        try {
            conectar();
            ps=conex.prepareStatement(sql);            
            ps.setInt(1,dv.getId_Venta());
            ps.setInt(2,dv.getId_Producto());
            ps.setInt(3,dv.getCantidad());
            ps.setDouble(4,dv.getPreVenta());
            r=ps.executeUpdate();
        }catch (Exception e){
            
        }
        return r;
    }
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGenerarV;
    private javax.swing.JButton btnGenerarVenta;
    private javax.swing.JComboBox<String> cmbListProduc;
    private javax.swing.JComboBox<String> cmbNomVendedor;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAlbum;
    private javax.swing.JLabel lblBanda;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblN;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JLabel lblProducto;
    private javax.swing.JLabel lblRCliente;
    private javax.swing.JLabel lblStock;
    private javax.swing.JLabel lblSubTitulo;
    private javax.swing.JLabel lblTPagar;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblVendedor;
    private javax.swing.JTable tablaVenta;
    private javax.swing.JTextField txtAlbum;
    private javax.swing.JTextField txtBanda;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCodProducto;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtIdCliente;
    private javax.swing.JTextField txtIdVendedor;
    private javax.swing.JTextField txtNomCliente;
    private javax.swing.JTextField txtNomProducto;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtRutCliente;
    private javax.swing.JTextField txtSerie;
    private javax.swing.JTextField txtStock;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
