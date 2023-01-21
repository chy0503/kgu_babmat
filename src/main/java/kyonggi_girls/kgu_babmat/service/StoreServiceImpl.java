package kyonggi_girls.kgu_babmat.service;

import kyonggi_girls.kgu_babmat.dao.store.CafeteriaMenuDao;
import kyonggi_girls.kgu_babmat.dao.store.MenuDao;
import kyonggi_girls.kgu_babmat.dao.store.StoreDao;
import kyonggi_girls.kgu_babmat.dto.CafeteriaMenu;
import kyonggi_girls.kgu_babmat.dto.Menu;
import kyonggi_girls.kgu_babmat.dto.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreDao storeDao;
    private final MenuDao menuDao;
    private final CafeteriaMenuDao cafeteriaMenuDao;

    @Override
    public List<Store> getStores() throws ExecutionException, InterruptedException {
        return storeDao.getStores();
    }

    @Override
    public List<Store> getStore(String storeName) throws ExecutionException, InterruptedException {
        return storeDao.getStore(storeName);
    }

    @Override
    public List<Store> getInnerStore(String selectStoreName, String storeName) throws ExecutionException, InterruptedException {
        return storeDao.getInnerStore(selectStoreName, storeName);
    }

    @Override
    public List<Menu> getMenu(String selectStoreName, String storeName) throws ExecutionException, InterruptedException {
        return menuDao.getMenu(selectStoreName, storeName);
    }

    @Override
    public List<CafeteriaMenu> getCafeteriaMenu(String storeName) throws Exception {
        return cafeteriaMenuDao.getCafeteriaMenu(storeName);
    }

}
