package project_dz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project_dz.dao.TovarDao;
import project_dz.entity.Tovar;

import java.util.List;

@Service
public class TovarService {
    @Autowired
    TovarDao tovarDao;
public List<Tovar> getAllTovar(){
    return tovarDao.getAllTovar();
}
    public void addTovar(Tovar tovar){
       tovarDao.insert(tovar);
    }

    public void updateTovar(Tovar tovar) {
        tovarDao.update(tovar);
    }
    public void deleteTovar(long id){
        tovarDao.delete(id);
    }
    public Tovar getTovarById(long id){
        return tovarDao.getTovarById(id);
    }
    public void reservedTovar(Long id,Boolean reserved){
        Tovar tovar=getTovarById(id);
        tovar.setReserved(reserved);
        updateTovar(tovar);
    }
}
