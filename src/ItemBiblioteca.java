public abstract class ItemBiblioteca {
    private String id;
    private String titulo;
    private Boolean estadoPrestado = false;

    public ItemBiblioteca(String id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Boolean getEstadoPrestado() {
        return estadoPrestado;
    }

    public void setEstadoPrestado(Boolean estadoPrestado) {
        this.estadoPrestado = estadoPrestado;
    }

    @Override
    public String toString() {
        return  "id='" + id + '\'' +
                ", titulo='" + titulo + '\'' +
                ", estadoPrestado=" + estadoPrestado;
    }

    public void prestar(){
        this.estadoPrestado = true;
    }

    public void devolver(){
        this.estadoPrestado = false;
    }

    public abstract double calcularMulta(int diasRetraso);

    public abstract int getDiasMaximosPrestamo();
}
