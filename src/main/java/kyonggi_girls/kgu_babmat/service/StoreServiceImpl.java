package kyonggi_girls.kgu_babmat.service;

import kyonggi_girls.kgu_babmat.dao.StoreDao;
import kyonggi_girls.kgu_babmat.dto.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreDao storeDao;

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

}
