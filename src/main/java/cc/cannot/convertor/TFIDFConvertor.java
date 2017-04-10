package cc.cannot.convertor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangzhen on 4/11/2017.
 */
public class TFIDFConvertor {
    // 分词结果集
    private List<String[]> segList;
    //构成向量模板的基本单词集合
    private List<String> vectorBase;

    public TFIDFConvertor(){
        super();
    }
    public TFIDFConvertor(List<String[]> list){
        super();
        segList = list;
        initConvertor();
    }
    private void initConvertor(){
        vectorBase = new ArrayList<String>();
        for (String[] array : segList){
            for (String word : array){
                //若该词语不在集合内，则加入
                if(!vectorBase.contains(word)){
                    vectorBase.add(word);
                }
            }
        }
    }
}
