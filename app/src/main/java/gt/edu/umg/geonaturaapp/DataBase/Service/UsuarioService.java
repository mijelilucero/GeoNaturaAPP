package gt.edu.umg.geonaturaapp.DataBase.Service;

import android.content.Context;

import gt.edu.umg.geonaturaapp.DataBase.Dao.UsuarioDao;
import gt.edu.umg.geonaturaapp.DataBase.Model.Usuario;

public class UsuarioService {

    private UsuarioDao usuarioDao;

    public UsuarioService(Context context) {
        usuarioDao = new UsuarioDao(context);
    }

    public void agregarUsuario(Usuario usuario) {
        usuarioDao.insertUsuario(usuario);
    }

    public Usuario obtenerUsuarioPorNombre(String nombreUsuario) {
        return usuarioDao.getUsuarioByNombreUsuario(nombreUsuario);
    }
}
