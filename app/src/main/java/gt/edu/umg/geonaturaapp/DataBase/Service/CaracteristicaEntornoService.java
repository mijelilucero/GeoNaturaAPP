package gt.edu.umg.geonaturaapp.DataBase.Service;

import android.content.Context;

import java.util.List;

import gt.edu.umg.geonaturaapp.DataBase.Dao.CaracteristicasEntornoDao;
import gt.edu.umg.geonaturaapp.DataBase.Model.CaracteristicaEntorno;

public class CaracteristicaEntornoService {

    private CaracteristicasEntornoDao caracteristicasEntornoDao;

    public CaracteristicaEntornoService(Context context) {
        caracteristicasEntornoDao = new CaracteristicasEntornoDao(context);
    }

    public void addCaracteristicaEntorno(CaracteristicaEntorno caracteristicaEntorno) {
        caracteristicasEntornoDao.insertCaracteristicaEntorno(caracteristicaEntorno);
    }

    public void updateCaracteristicaEntorno(CaracteristicaEntorno caracteristicaEntorno) {
        caracteristicasEntornoDao.updateCaracteristicaEntorno(caracteristicaEntorno);
    }

    public void deleteCaracteristicaEntorno(int id) {
        caracteristicasEntornoDao.deleteCaracteristicaEntornoById(id);
    }

    public CaracteristicaEntorno getCaracteristicaEntorno(int id) {
        return caracteristicasEntornoDao.getCaracteristicaEntornoById(id);
    }

    public List<CaracteristicaEntorno> getAllCaracteristicasEntorno(int userId) {
        return caracteristicasEntornoDao.getAllCaracteristicasEntornoByIdUser(userId);
    }
}
