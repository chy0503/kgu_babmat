package kyonggi_girls.kgu_babmat.controller;

import kyonggi_girls.kgu_babmat.dto.Store;
import kyonggi_girls.kgu_babmat.service.StoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
public class StoreController {
    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("store")
    public String store(@RequestParam("storeName") String storeName, Model model) throws ExecutionException, InterruptedException {
//        관리자가 식당 리스트를 조회할 때 사용할 예정
//        List<Store> storeList = storeService.getStores();
//        model.addAttribute("storeList", storeList);

        List<Store> store = storeService.getStore(storeName);
        model.addAttribute("store", store);
        model.addAttribute("storeName", storeName);
        return "store";
    }

    @GetMapping("selectStore")
    public String selectStore(@RequestParam("storeName") String storeName, Model model) throws ExecutionException, InterruptedException {
        List<Store> store = storeService.getStore(storeName);
        model.addAttribute("store", store);
        model.addAttribute("storeName", storeName);
        return "selectStore";
    }
}
