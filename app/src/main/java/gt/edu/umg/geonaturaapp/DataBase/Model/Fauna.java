package gt.edu.umg.geonaturaapp.DataBase.Model;

public class Fauna {

    private int id;
    private double latitud;
    private double longitud;
    private byte[] imagen;
    private String nombre;
    private String clasificacion;
    private double tamanio;
    private double peso;
    private String habitat;
    private String estadoConservacion;
    private String fechaHora;
    private int id_user;

    public Fauna() {}

    public Fauna(int id, double latitud, double longitud, byte[] imagen, String nombre,
                 String clasificacion, double tamanio, double peso, String habitat,
                 String estadoConservacion, String fechaHora, int id_user) {
        this.id = id;
        this.latitud = latitud;
        this.longitud = longitud;
        this.imagen = imagen;
        this.nombre = nombre;
        this.clasificacion = clasificacion;
        this.tamanio = tamanio;
        this.peso = peso;
        this.habitat = habitat;
        this.estadoConservacion = estadoConservacion;
        this.fechaHora = fechaHora;
        this.id_user = id_user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public double getTamanio() {
        return tamanio;
    }

    public void setTamanio(double tamanio) {
        this.tamanio = tamanio;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public String getEstadoConservacion() {
        return estadoConservacion;
    }

    public void setEstadoConservacion(String estadoConservacion) {
        this.estadoConservacion = estadoConservacion;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public int getIdUser() {
        return id_user;
    }

    public void setIdUser(int id_user) {
        this.id_user = id_user;
    }
}
