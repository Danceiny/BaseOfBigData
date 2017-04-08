package cc.cannot.crawl;

import cc.cannot.util.DB;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;

/**
 * Created by huangzhen on 4/5/2017.
 */
public class qianchengwuyou extends BreadthCrawler {
    public qianchengwuyou(String crawlPath, boolean autoParse){
        super(crawlPath, autoParse);

        //this.addSeed("http://search.51job.com/list/000000,000000,0000,00,9,99,%25E7%25A8%258B%25E5%25BA%258F%25E5%2591%2598,2,1.html?lang=c&stype=&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&providesalary=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=");

        this.addSeed("http://search.51job.com/jobsearch/search_result.php?lang=c&degreefrom=99&stype=1&workyear=99&cotype=99&jobterm=99&companysize=99&radius=-1&address=&lonlat=&postchannel=&list_type=&ord_field=&curr_page=&dibiaoid=0&landmark=&welfare=");
        //set up regular rules
        this.addRegex("http://jobs.51job.com/.*/[0-9][0-9]"+"[0-9][0-9][0-9][0-9][0-9][0-9].html.*");

        //except jpg|png|gif
        this.addRegex("-.*\\.(jpg|png|gif).*");

        //except url with #
        this.addRegex("-.*#.*");

    }

    public void visit(Page page, CrawlDatums crawlDatums) {
        String url = page.getUrl();

        if(page.matchUrl("http://jobs.51job.com/.*/[0-9][0-9]"+"[0-9][0-9][0-9][0-9][0-9][0-9].html.*")){
            String title = page.select("div.cn>h1").text(); // 职位名称
            String companyName = page.select("p.cname").text(); //公司名称
            String jobBenefits = page.select("p.t2").text();    //职位福利
            String salary = page.select("div.cn>strong").text();   //薪资
            String jobInfo = page.select("div.job_msg").text(); //
            String place = page.select("span.lname").text();
            String information = page.select("p.ltype").text();
            String[] names = information.split("\\|");  //以|为标志分割符
            String field = names[0];    //公司领域
            String type = names[1]; //公司性质
            String scale = names[2];    //公司规模

            String sql = "insert into qianchengwuyou(url,title,companyName,"
                    + "jobBenefits,salary,jobInfo,place,field,type,scale)"
                    + " values('" + url + "','" + title + "','" + companyName + "','" + jobBenefits + "','"
                    + ",'" + salary + "','" + jobInfo + "','" + place + "','" + field + "','"
                    + type + "','" + scale + "')";

//            String qcwy_sql = "insert into qianchengwuyou(url,title,companyName,jobBenefits,salary,jobInfo,place,field,type,scale)"
//                    + " values('" + "url" + "','" + "title" + "','" + "companyName" + "','" + "jobBenefits" + "','"
//                    + "salary" + "','" + "jobInfo" + "','" + "place" + "','" + "field" + "','"
//                    + "type" + "','" + "scale" + "')";
            DB db = new DB();
            db.open(sql);
            db.close();
        }
    }

    public static void main(String[] args)throws Exception{
        //autoParse 自动解析机制
        qianchengwuyou crawler = new qianchengwuyou("crawl", true);

        //thread
        crawler.setThreads(50);
        //up-limit in every iteration
        crawler.setTopN(5000);

        //设置每个线程visit的间隔，单位ms。主要用于反 反爬虫
        //crawler.setExecuteInterval(10000);

        //断点爬取：默认false则任务启动前清空历史数据
        crawler.setResumable(true);

        crawler.start(200);
        //System.exit(0);
    }
}
