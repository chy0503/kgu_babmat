package kyonggi_girls.kgu_babmat.controller;

import kyonggi_girls.kgu_babmat.dto.Menu;
import kyonggi_girls.kgu_babmat.dto.Store;
import kyonggi_girls.kgu_babmat.service.StoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
public class IntroController {
    private final StoreService storeService;

    public IntroController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("/")
    public String intro(Model model) throws ExecutionException, InterruptedException {
        // store list
        List<Store> storeList = storeService.getStores();
        model.addAttribute("storeList", storeList);
        return "intro";
    }
}
