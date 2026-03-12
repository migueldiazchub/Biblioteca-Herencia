public class Libro extends ItemBiblioteca {
    private String autor;

    public Libro(String id, String titulo, String autor) {
        super(id, titulo);
        this.autor = autor;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Libro{" +
                super.toString() +
                ", autor='" + autor + '\'' +
                '}';
    }

    @Override
    public double calcularMulta(int diasRetraso) {
        return diasRetraso * 0.5;
    }

    @Override
    public int getDiasMaximosPrestamo() {
        return 14;
    }
}
