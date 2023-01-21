package kyonggi_girls.kgu_babmat.service;

import kyonggi_girls.kgu_babmat.dto.CafeteriaMenu;
import kyonggi_girls.kgu_babmat.dto.Menu;
import kyonggi_girls.kgu_babmat.dto.Store;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface StoreService {
    List<Store> getStores() throws ExecutionException, InterruptedException;
    List<Store> getStore(String storeName) throws ExecutionException, InterruptedException;
    List<Store> getInnerStore(String selectStoreName, String storeName) throws ExecutionException, InterruptedException;

    List<Menu> getMenu(String selectStoreName, String storeName) throws ExecutionException, InterruptedException;
    List<CafeteriaMenu> getCafeteriaMenu(String storeName) throws Exception;
}
