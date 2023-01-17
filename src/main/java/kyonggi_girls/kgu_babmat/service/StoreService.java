package kyonggi_girls.kgu_babmat.service;

import kyonggi_girls.kgu_babmat.dto.Store;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface StoreService {
    List<Store> getStores() throws ExecutionException, InterruptedException;
}
