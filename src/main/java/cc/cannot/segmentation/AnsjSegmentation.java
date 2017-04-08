package cc.cannot.segmentation;


import cc.cannot.util.Config;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

import cc.cannot.util.TxtTool;
import org.ansj.domain.Result;
import org.ansj.library.DicLibrary;
import org.ansj.recognition.impl.StopRecognition;
import org.ansj.splitWord.analysis.DicAnalysis;

/**
 * Created by huangzhen on 4/6/2017.
 * 1. 初始化变量，包括读取停用词和用户自定义词
 * 2. 获取文本集合（setWordList方法实现）
 * 3. 分词——》（过滤停用词）——》去词性（toStringWithoutNature()方法）——》根据","将string切分为string数组
 */
public class AnsjSegmentation {
    // doc set
    private List<String> wordList;
    // segmented set
    private List<String[]> segList;
    // 停用词表的存放路径
    private static String stopWordsPath = Config.stopWordsPath;

    private static String userWordsPath = Config.userWordsPath;
    //用来存放未过滤停用词的结果
    private List<String[]> listWithoutFilter = new ArrayList<String[]>();
    private static StopRecognition filter;


    static {
        List<String > list = new TxtTool().getDataFromTxt(stopWordsPath);
        filter = new StopRecognition();
        filter.insertStopWords(list);
        System.out.println("停用词加载成功");
    }

    //添加用户自定义词典
    static {
        List<String> list = new TxtTool().getDataFromTxt(userWordsPath);
        for(String str: list){
            DicLibrary.insert(DicLibrary.DEFAULT,str);
        }
    }


    // init variables
    public AnsjSegmentation(){
        wordList = new ArrayList<String>();
        segList = new ArrayList<String[]>();
    }

    public void segment(){
        //处理一句一句的分词
        for(String word: wordList){
            if(word == null){
                segList.add(new String[] {});
                continue;
            }
            Result result = new Result(null);

            result = DicAnalysis.parse(word);
            if(result.size() == 0){
                segList.add(new String[] {});
                continue;
            }
            //将result去掉词性
            listWithoutFilter.add(result.toStringWithOutNature().split(","));

            //
            result = result.recognition(filter);
            if(result.size() == 0){
                segList.add(new String[] {});
                continue;
            }
            segList.add(result.toStringWithOutNature().split(","));
        }
    }

    public List<String[]> getListWithoutFilter(){
        return listWithoutFilter;
    }

    //将给定string经过过滤停用词分词为一个str数组（item为词语）
    public String[] segmentToArray(String str){
        if(str == null){
            return new String[]{};
        }
        Result result = new Result(null);
        result = DicAnalysis.parse(str).recognition(filter);
        if(result.size() == 0){
            return new String[]{};
        }
        return result.toStringWithOutNature().split(",");
    }

    //将给定string经过过滤停用词分词为一个str,以,分隔每个词语
    public String segmentToString(String str){
        if(str == null){
            return "";
        }
        Result result = new Result(null);
        result = DicAnalysis.parse(str).recognition(filter);
        if(result.size() == 0){
            return "";
        }
        return result.toStringWithOutNature();
    }

    public List<String[]> getSegList(){
        return segList;
    }

    public void setWordList(List<String> wordList){
        this.wordList = wordList;
    }
}
