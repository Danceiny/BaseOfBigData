package cc.cannot.crawl;

import cc.cannot.util.DB;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;

/**
 * Created by huangzhen on 4/8/2017.
 */
public class theBigDataCn extends BreadthCrawler {
    public theBigDataCn(String crawlPath, boolean autoParse){
        super(crawlPath, autoParse);

        this.addSeed("http://www.thebigdata.cn/YeJieDongTai/32592.html");

        //set up regular rules
        this.addRegex("http://www.thebigdata.cn/.*/[0-9][0-9][0-9][0-9][0-9].html.*");

        //except jpg|png|gif
        this.addRegex("-.*\\.(jpg|png|gif).*");

        //except url with #
        this.addRegex("-.*#.*");

    }

    public void visit(Page page, CrawlDatums crawlDatums) {
        String url = page.getUrl();

        if(page.matchUrl("http://www.thebigdata.cn/.*/[0-9][0-9]0-9][0-9][0-9][0-9].html.*")){
            String title = page.select("div.cn>h1").text(); // 文章标题
            String text = page.select("div.content>p").text();  //正文


            String sql = "insert into thebigdatacn(url,title,text)"
                    + "values('" + url + "','" + title + "','" + text +  "')";
            DB db = new DB();
            db.open(sql);
            db.close();
        }
    }

    public static void main(String[] args)throws Exception{
        //autoParse 自动解析机制
        qianchengwuyou crawler = new qianchengwuyou("crawl", true);

        //thread
        crawler.setThreads(500);
        //up-limit in every iteration
        crawler.setTopN(5000);

        //设置每个线程visit的间隔，单位ms。主要用于反 反爬虫
        crawler.setExecuteInterval(10000);

        //断点爬取：默认false则任务启动前清空历史数据
        crawler.setResumable(true);

        crawler.start(200);
    }
}
