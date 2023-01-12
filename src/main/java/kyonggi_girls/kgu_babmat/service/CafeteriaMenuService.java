package kyonggi_girls.kgu_babmat.service;

import kyonggi_girls.kgu_babmat.domain.CafeteriaMenu;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CafeteriaMenuService {
    private String dorm_url = "https://dorm.kyonggi.ac.kr:446/Khostel/mall_main.php?viewform=B0001_foodboard_list&board_no=1";
    private String gamco_url = "http://www.kyonggi.ac.kr/webRestMenu.kgu?mzcode=K00M04038500&restGb=suwon";

    public List<CafeteriaMenu> getDormMenuList() throws Exception {
        List<CafeteriaMenu> dormMenuList = new ArrayList<>();
        Document document = Jsoup.connect(dorm_url).get();
        Elements contents = document.select(".boxstyle02").get(1).select("tbody");
        for (int i = 1; i < 6; i++) {
            Element content = contents.select("tr").get(i);
            CafeteriaMenu menu = CafeteriaMenu.builder()
                    .date(removeTag(String.valueOf(content.select("th"))))
                    .breakfast(removeTag((String.valueOf(content.select("td").get(0)))))
                    .lunch(removeTag((String.valueOf(content.select("td").get(1)))))
                    .dinner(removeTag((String.valueOf(content.select("td").get(2)))))
                    .build();
            dormMenuList.add(menu);
        }
        return dormMenuList;
    }

    public List<CafeteriaMenu> getGamcoMenuList() throws Exception {
        List<CafeteriaMenu> gamcoMenuList = new ArrayList<>();
        Document document = Jsoup.connect(gamco_url).get();
        Elements contents = document.select("table.table_t1 tbody");
        for (int i = 0; i < 5; i++) {
            Element content = contents.select("tr").get(i);
            CafeteriaMenu menu = CafeteriaMenu.builder()
                    .date(removeTag(String.valueOf(content.select("th"))))
                    .breakfast("미운영")
                    .lunch(removeVerticalBar(removeTag((String.valueOf(content.select("td").get(1))))))
                    .dinner("미운영")
                    .build();
            gamcoMenuList.add(menu);
        }
        return gamcoMenuList;
    }


    /**
     * <br>을 제외한 모든 HTML 태그를 제거
     */
    public String removeTag(String str) throws Exception {
        str = str.replace("<br>", "\n");
        str = str.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
        str = str.replace("\n", "<br>");
        return str;
    }

    /**
     * Vertical Bar(|)을 <br> 변경
     */
    public String removeVerticalBar(String str) throws Exception {
        return str.replace("|", "<br>");
    }
}
