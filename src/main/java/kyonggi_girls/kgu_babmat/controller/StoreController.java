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
        List<Store> storeList = storeService.getStores();
        model.addAttribute("storeList", storeList);
        model.addAttribute("storeName", storeName);
        return "store";
    }
}
