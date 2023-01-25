package kyonggi_girls.kgu_babmat.dao.store;

import kyonggi_girls.kgu_babmat.dto.CafeteriaMenu;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Service
public class CafeteriaMenuDao {
    private String dorm_url = "https://dorm.kyonggi.ac.kr:446/Khostel/mall_main.php?viewform=B0001_foodboard_list&board_no=1";
    private String gamco_url = "http://www.kyonggi.ac.kr/webRestMenu.kgu?mzcode=K00M04038500&restGb=suwon";

    String today = (new SimpleDateFormat("MM.dd (EEE)", Locale.KOREAN)).format(new java.util.Date());
    boolean isToday = false;

    public List<CafeteriaMenu> getCafeteriaMenu(String store) throws Exception {
        List<CafeteriaMenu> menuList = new ArrayList<>();
        switch (store) {
            case "기숙사 식당":
                menuList = getDormMenuList();
                break;
            case "감성코어":
                menuList = getGamcoMenuList();
                break;
        }
        return menuList;
    }

    public List<CafeteriaMenu> getDormMenuList() throws Exception {
        List<CafeteriaMenu> dormMenuList = new ArrayList<>();
        Document document = Jsoup.connect(dorm_url).get();
        Elements contents = document.select(".boxstyle02").get(1).select("tbody");
        for (int i = 1; i < 6; i++) {
            Element content = contents.select("tr").get(i);
            String date = removeTag(String.valueOf(content.select("th")));
            date = (date.replace("-", ".")).substring(6);

            if (date.contains(today))
                isToday = true;
            else
                isToday = false;

            CafeteriaMenu menu = CafeteriaMenu.builder()
                    .date(date)
                    .breakfast(checkMenu(removeTag((String.valueOf(content.select("td").get(0))))))
                    .lunch(checkMenu(removeTag((String.valueOf(content.select("td").get(1))))))
                    .dinner(checkMenu(removeTag((String.valueOf(content.select("td").get(2))))))
                    .today(isToday)
                    .build();
            dormMenuList.add(menu);
        }
        return dormMenuList;
    }

    public List<CafeteriaMenu> getGamcoMenuList() throws Exception {
        List<CafeteriaMenu> gamcoMenuList = new ArrayList<>();
        Document document = Jsoup.connect(gamco_url).get();
        Elements contents = document.select("table.table_t1 tbody");
        if (!contents.select("tr").isEmpty()) {
            for (int i = 0; i < 5; i++) {
                Element content = contents.select("tr").get(i);
                String date = removeTag(String.valueOf(content.select("th")));

                if (date.contains(today))
                    isToday = true;
                else
                    isToday = false;

                CafeteriaMenu menu = CafeteriaMenu.builder()
                        .date(removeTag(date))
                        .breakfast("미운영")
                        .lunch(checkMenu(removeVerticalBar(removeTag((String.valueOf(content.select("td").get(1)))))))
                        .dinner("미운영")
                        .today(false)
                        .build();
                gamcoMenuList.add(menu);
            }
        }
        return gamcoMenuList;
    }

    public String getToday() {
        return today;
    }

    /**
     * <br>을 제외한 모든 HTML 태그를 제거
     */
    public String removeTag(String str) {
        str = str.replace("<br>", "\n");
        str = str.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
        str = str.replace("\n", "<br>");
        return str;
    }

    /**
     * Vertical Bar(|)을 <br> 변경
     */
    public String removeVerticalBar(String str) {
        return str.replace("|", "<br>");
    }

    /**
     * 미운영 처리
     */
    public String checkMenu(String str) {
        if (str.matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*"))
            return str;
        else
            return "미운영";
    }
}
