package kyonggi_girls.kgu_babmat.controller;

import kyonggi_girls.kgu_babmat.dto.Store;
import kyonggi_girls.kgu_babmat.service.StoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
public class MainController {
    private final StoreService storeService;

    public MainController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("main")
    public String main(Model model) throws ExecutionException, InterruptedException {
        List<Store> storeList = storeService.getStores();
        model.addAttribute("storeList", storeList);
        return "main";
    }
}
