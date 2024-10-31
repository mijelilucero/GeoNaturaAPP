package gt.edu.umg.geonaturaapp.DataBase.Model;

public class CaracteristicaEntorno {

    private int id;
    private double latitud;
    private double longitud;
    private byte[] imagen;
    private String nombre;
    private String ecosistema;
    private double temperatura;
    private double altitud;
    private String presenciaAgua;
    private String densidadVegetal;
    private String fechaHora;
    private int id_user;

    public CaracteristicaEntorno() {}

    public CaracteristicaEntorno(int id, double latitud, double longitud, byte[] imagen,
                                 String nombre, String ecosistema, double temperatura, double altitud,
                                 String presenciaAgua, String densidadVegetal, String fechaHora, int id_user) {
        this.id = id;
        this.latitud = latitud;
        this.longitud = longitud;
        this.imagen = imagen;
        this.nombre = nombre;
        this.ecosistema = ecosistema;
        this.temperatura = temperatura;
        this.altitud = altitud;
        this.presenciaAgua = presenciaAgua;
        this.densidadVegetal = densidadVegetal;
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

    public String getEcosistema() {
        return ecosistema;
    }

    public void setEcosistema(String ecosistema) {
        this.ecosistema = ecosistema;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public double getAltitud() {
        return altitud;
    }

    public void setAltitud(double altitud) {
        this.altitud = altitud;
    }

    public String getPresenciaAgua() {
        return presenciaAgua;
    }

    public void setPresenciaAgua(String presenciaAgua) {
        this.presenciaAgua = presenciaAgua;
    }

    public String getDensidadVegetal() {
        return densidadVegetal;
    }

    public void setDensidadVegetal(String densidadVegetal) {
        this.densidadVegetal = densidadVegetal;
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
