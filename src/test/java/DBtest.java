import cc.cannot.util.DB;

/**
 * Created by huangzhen on 4/8/2017.
 */
public class DBtest {
    public static void  main(String[] args){
        String sql = "insert into dbtest(url,title,companyName) value('www.baidu.com','百度','baidu')";
        String qcwy_sql = "insert into qianchengwuyou(url,title,companyName,jobBenefits,salary,jobInfo,place,field,type,scale)"
                + " values('" + "url" + "','" + "title" + "','" + "companyName" + "','" + "jobBenefits" + "','"
                + "salary" + "','" + "jobInfo" + "','" + "place" + "','" + "field" + "','"
                + "type" + "','" + "scale" + "')";
        System.out.println(qcwy_sql);
        DB db = new DB();
        //db.open(sql);
        db.open(qcwy_sql);
        db.close();
    }
}
