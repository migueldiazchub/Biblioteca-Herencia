public class Revista extends ItemBiblioteca{
    private int numeroEdicion;

    public Revista(String id, String titulo, int numeroEdicion) {
        super(id, titulo);
        this.numeroEdicion = numeroEdicion;
    }

    public int getNumeroEdicion() {
        return numeroEdicion;
    }

    public void setNumeroEdicion(int numeroEdicion) {
        this.numeroEdicion = numeroEdicion;
    }

    @Override
    public String toString() {
        return "Revista{" +
                super.toString() +
                ", numeroEdicion=" + numeroEdicion +
                '}';
    }

    @Override
    public double calcularMulta(int diasRetraso) {
        return diasRetraso;
    }

    @Override
    public int getDiasMaximosPrestamo() {
        return 7;
    }
}
