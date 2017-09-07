package com.tusueldo.comodin.connections.ruc;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class InformationRUC {

    @SerializedName("success")
    @Expose
    private boolean success;

    @SerializedName("entity")
    @Expose
    private Entity entity;

    public InformationRUC() {

    }

    public InformationRUC(boolean success, Entity entity) {
        this.success = success;
        this.entity = entity;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public class Entity {

        @SerializedName("nombre_o_razon_social")
        @Expose
        private String nombreORazonSocial;

        @SerializedName("ubigeo")
        @Expose
        private String ubigeo;

        @SerializedName("departamento")
        @Expose
        private String departamento;

        @SerializedName("provincia")
        @Expose
        private String provincia;

        @SerializedName("distrito")
        @Expose
        private String distrito;

        @SerializedName("direccion")
        @Expose
        private String direccion;

        public Entity() {

        }

        public Entity(String nombreORazonSocial, String ubigeo, String distrito, String direccion) {
            this.nombreORazonSocial = nombreORazonSocial;
            this.ubigeo = ubigeo;
            this.distrito = distrito;
            this.direccion = direccion;
        }

        public String getNombreORazonSocial() {
            return nombreORazonSocial;
        }

        public void setNombreORazonSocial(String nombreORazonSocial) {
            this.nombreORazonSocial = nombreORazonSocial;
        }

        public String getUbigeo() {
            return ubigeo;
        }

        public void setUbigeo(String ubigeo) {
            this.ubigeo = ubigeo;
        }

        public String getDistrito() {
            return distrito;
        }

        public void setDistrito(String distrito) {
            this.distrito = distrito;
        }

        public String getDireccion() {
            return direccion;
        }

        public void setDireccion(String direccion) {
            this.direccion = direccion;
        }

        public String getDepartamento() {
            return departamento;
        }

        public void setDepartamento(String departamento) {
            this.departamento = departamento;
        }

        public String getProvincia() {
            return provincia;
        }

        public void setProvincia(String provincia) {
            this.provincia = provincia;
        }
    }

}
