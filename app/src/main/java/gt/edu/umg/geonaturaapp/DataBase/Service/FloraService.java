package gt.edu.umg.geonaturaapp.DataBase.Service;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import gt.edu.umg.geonaturaapp.DataBase.Dao.FloraDao;
import gt.edu.umg.geonaturaapp.DataBase.Model.Flora;

public class FloraService {

    private FloraDao floraDao;

    public FloraService(Context context) {
        floraDao = new FloraDao(context);
    }

    public void addFlora(Flora flora) {
        floraDao.insertFlora(flora);
    }

    public void updateFlora(Flora flora) {
        floraDao.updateFlora(flora);
    }

    public void deleteFlora(int id) {
        floraDao.deleteFloraById(id);
    }

    public Flora getFlora(int id) {
        return floraDao.getFloraById(id);
    }

    public ArrayList<Flora> getAllFloras(int userId) {
        return floraDao.getAllFloraByIdUser(userId);
    }
}
