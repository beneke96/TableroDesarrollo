package es.formacion.cip.fran.model;

import javax.persistence.*;


@Entity
@Table(name = "tarea")
public class Tarea {
    private Integer id;
    private String nombre;
    private String descripcion;

    public Tarea(){}
    public Tarea(String nombre, String descripcion){
        this.nombre = nombre;
        this.descripcion = descripcion;
    }



     @Id
     @GeneratedValue (strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
