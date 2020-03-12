package com.example.apprestaurante.rest.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class PlatoCategoriaResponse implements Serializable {

    @SerializedName("message")
    @Expose
    private Object message;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public class Datum implements Serializable {

        @SerializedName("imagen")
        @Expose
        private String imagen;
        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("categoria_id")
        @Expose
        private CategoriaId categoriaId;
        @SerializedName("nombre")
        @Expose
        private String nombre;
        @SerializedName("precio")
        @Expose
        private double precio;
        @SerializedName("descripcion")
        @Expose
        private String descripcion;

        public String getImagen() {
            return imagen;
        }

        public void setImagen(String imagen) {
            this.imagen = imagen;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public CategoriaId getCategoriaId() {
            return categoriaId;
        }

        public void setCategoriaId(CategoriaId categoriaId) {
            this.categoriaId = categoriaId;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public double getPrecio() {
            return precio;
        }

        public void setPrecio(double precio) {
            this.precio = precio;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public class CategoriaId implements Serializable {

            @SerializedName("_id")
            @Expose
            private String id;
            @SerializedName("nombre")
            @Expose
            private String nombre;
            @SerializedName("descripcion")
            @Expose
            private String descripcion;

            public String getId() {
                return id;
            }

            public void setId(String id) {
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

    }

}
