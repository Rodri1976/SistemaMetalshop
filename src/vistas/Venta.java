/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistas;

/**
 *
 * @author rfigu
 */
public class Venta {
    
 private int id,id_Cliente,id_Vendedor;
 private String num,fecha,estado;
 private double monto;

    public Venta() {
    }

    public Venta(int id, int id_Cliente, int id_Vendedor, String num, String fecha,double monto,String estado) {
        this.id = id;
        this.id_Cliente = id_Cliente;
        this.id_Vendedor = id_Vendedor;
        this.num = num;
        this.fecha = fecha;
        this.estado = estado;
        this.monto = monto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_Cliente() {
        return id_Cliente;
    }

    public void setId_Cliente(int id_Cliente) {
        this.id_Cliente = id_Cliente;
    }

    public int getId_Vendedor() {
        return id_Vendedor;
    }

    public void setId_Vendedor(int id_Vendedor) {
        this.id_Vendedor = id_Vendedor;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
    
  
}
