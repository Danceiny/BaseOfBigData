package cc.cannot.distance;

import cc.cannot.util.VectorUtils;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by huangzhen on 4/8/2017.
 */
public class CosDistance {
    //存放分词后所用来计算相似度的向量
    private List<double[]> vectors;
    /* 相似度矩阵 */
    private double[][] matrix;
    public CosDistance(List<double[]> list){
        this.vectors = list;
        init();
    }

    private void init() {
        int vSize = vectors.size();
        matrix = new double[vSize][vSize];
        for (int i=0; i<vSize; i++){
            for (int j=i; j<vSize; j++){
                if (j==i){
                    matrix[i][j] = 1;
                    continue;
                }
                matrix[i][j] = calSimilarity(vectors.get(i),vectors.get(j));
                matrix[j][i] = matrix[i][j];

                //print
                DecimalFormat df = new DecimalFormat("#0.000000");
                System.out.print(df.format(matrix[i][j]) + "  ");
                System.out.println(i+"  "+j);
            }
        }
    }

    private double calSimilarity(double[] vec1 , double[] vec2) {
        //TODO
        return VectorUtils.multiply(vec1,vec2)/(VectorUtils.module(vec1)*VectorUtils.module(vec2));
    }
}

