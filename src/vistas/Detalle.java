/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistas;

/**
 *
 * @author rfigu
 */
public class Detalle {
    
 private int id,id_Venta,id_Producto;
 private int cantidad;
 private double preVenta;

    public Detalle() {
    }

    public Detalle(int id, int id_Venta, int id_Producto, int cantidad, double preVenta) {
        this.id = id;
        this.id_Venta = id_Venta;
        this.id_Producto = id_Producto;
        this.cantidad = cantidad;
        this.preVenta = preVenta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_Venta() {
        return id_Venta;
    }

    public void setId_Venta(int id_Venta) {
        this.id_Venta = id_Venta;
    }

    public int getId_Producto() {
        return id_Producto;
    }

    public void setId_Producto(int id_Producto) {
        this.id_Producto = id_Producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPreVenta() {
        return preVenta;
    }

    public void setPreVenta(double preVenta) {
        this.preVenta = preVenta;
    }
 
    
    
}
