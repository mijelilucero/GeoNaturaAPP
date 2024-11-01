package gt.edu.umg.geonaturaapp.DataBase.Service;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import gt.edu.umg.geonaturaapp.DataBase.Dao.FaunaDao;
import gt.edu.umg.geonaturaapp.DataBase.Model.Fauna;

public class FaunaService {

    private FaunaDao faunaDao;

    public FaunaService(Context context) {
        faunaDao = new FaunaDao(context);
    }

    public void addFauna(Fauna fauna) {
        faunaDao.insertFauna(fauna);
    }

    public void updateFauna(Fauna fauna) {
        faunaDao.updateFauna(fauna);
    }

    public void deleteFauna(int id) {
        faunaDao.deleteFaunaById(id);
    }

    public Fauna getFauna(int id) {
        return faunaDao.getFaunaById(id);
    }

    public ArrayList<Fauna> getAllFaunas(int userId) {
        return faunaDao.getAllFaunaByIdUser(userId);
    }
}
