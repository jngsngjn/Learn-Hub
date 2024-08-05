package project.homelearn.service.teacher;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import project.homelearn.dto.teacher.dashboard.NewsDto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class NewsService {

    private static final String NAVER_IT_NEWS_URL = "https://media.naver.com/press/092?sid=105";

    public List<NewsDto> fetchLatestNews() throws IOException {
        List<NewsDto> newsList = new ArrayList<>();

        Document doc = Jsoup.connect(NAVER_IT_NEWS_URL).get();
        Elements newsHeadlines = doc.select(".press_edit_news_item");

        int count = 0;
        for (Element headline : newsHeadlines) {
            if (count >= 2) {
                break;
            }

            Element titleElement = headline.select(".press_edit_news_title").first();
            Element urlElement = headline.select(".press_edit_news_link").first();
            Element imageElement = headline.select(".press_edit_news_thumb img").first();

            if (titleElement != null && urlElement != null && imageElement != null) {
                String articleUrl = urlElement.attr("href");
                String content = fetchArticleContent(articleUrl);

                NewsDto news = new NewsDto();
                news.setTitle(titleElement.text());
                news.setContent(content);
                news.setUrl(articleUrl);
                news.setImageUrl(imageElement.attr("src")); // 이미지 URL 설정

                newsList.add(news);
                count++;
            }
        }

        return newsList;
    }

    private String fetchArticleContent(String url) throws IOException {
        Document articleDoc = Jsoup.connect(url).get();
        Element contentElement = articleDoc.select("#dic_area").first(); // 기사 내용 부분 선택자

        if (contentElement != null) {
            // 기사 내용의 일부만 추출 (예: 첫 200자)
            return contentElement.text().substring(0, Math.min(contentElement.text().length(), 200)) + "...";
        }
        return "";
    }
}