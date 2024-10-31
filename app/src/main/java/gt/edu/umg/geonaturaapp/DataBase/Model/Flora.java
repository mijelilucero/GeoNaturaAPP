package gt.edu.umg.geonaturaapp.DataBase.Model;

public class Flora {

    private int id;
    private double latitud;
    private double longitud;
    private byte[] imagen;
    private String nombreComun;
    private String nombreCientifico;
    private String tipoPlanta;
    private double altura;
    private String fruto;
    private String estadoConservacion;
    private String fechaHora;
    private int id_user;

    public Flora() {}

    public Flora(int id, double latitud, double longitud, byte[] imagen, String nombreComun,
                 String nombreCientifico, String tipoPlanta, double altura, String fruto,
                 String estadoConservacion, String fechaHora, int id_user) {
        this.id = id;
        this.latitud = latitud;
        this.longitud = longitud;
        this.imagen = imagen;
        this.nombreComun = nombreComun;
        this.nombreCientifico = nombreCientifico;
        this.tipoPlanta = tipoPlanta;
        this.altura = altura;
        this.fruto = fruto;
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

    public String getNombreComun() {
        return nombreComun;
    }

    public void setNombreComun(String nombreComun) {
        this.nombreComun = nombreComun;
    }

    public String getNombreCientifico() {
        return nombreCientifico;
    }

    public void setNombreCientifico(String nombreCientifico) {
        this.nombreCientifico = nombreCientifico;
    }

    public String getTipoPlanta() {
        return tipoPlanta;
    }

    public void setTipoPlanta(String tipoPlanta) {
        this.tipoPlanta = tipoPlanta;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public String getFruto() {
        return fruto;
    }

    public void setFruto(String fruto) {
        this.fruto = fruto;
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
