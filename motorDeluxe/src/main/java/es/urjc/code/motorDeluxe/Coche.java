package es.urjc.code.motorDeluxe;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name = "COCHE")
public class Coche {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	    
    private String matricula;
    private String marca;
    private String modelo;
    private String color;
    private String extras;
    private int numPuertas;
    private double kilometros;
    private int anioMatriculacion;
    private double precio;
    
   
    protected Coche(){}

    public Coche(String matricula, String marca, String modelo, String color, String extras, int numPuertas, double kilometros, int anioMatriculacion, double precio) {
        this.id = id;
    	this.matricula = matricula;
        this.marca = marca;
        this.color = color;
        this.extras = extras;
        this.numPuertas = numPuertas;
        this.kilometros = kilometros;
        this.anioMatriculacion = anioMatriculacion;
        this.precio = precio;
    }

    

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }

    public int getNumPuertas() {
        return numPuertas;
    }

    public void setNumPuertas(int numPuertas) {
        this.numPuertas = numPuertas;
    }

    public double getKilometros() {
        return kilometros;
    }

    public void setKilometros(double kilometros) {
        this.kilometros = kilometros;
    }

    public int getAnioMatriculacion() {
        return anioMatriculacion;
    }

    public void setAnioMatriculacion(int anioMatriculacion) {
        this.anioMatriculacion = anioMatriculacion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

   
}