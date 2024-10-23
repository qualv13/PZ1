package lab2;

import java.util.Random;
import java.util.Scanner;
import java.lang.Math;

public class Matrix {
    double[]data;
    int rows;
    int cols;
    //...
    private static final double VERY_SMALL = 1e-10;
    Matrix(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        data = new double[rows*cols];
    }
    Matrix(double[][] d) {
        this.rows = d.length;
        this.cols = d[0].length;
        data = new double[rows * cols];

        for (int i = 0; i < rows; i++) {
            System.arraycopy(d[i], 0, data, i * cols, d[i].length);
        }
    }
    double[][] asArray(){
        double [][] newData = new double[rows][cols];
        for (int i = 0; i<rows; i++){
            for (int j = 0; j<cols; j++){
                newData[i][j] = data[i*cols + j];
            }
        }
        return newData;
    }
    double get(int r, int c){return data[r*cols + c];}
    void set(int r, int c, double value){data[r*cols + c] = value;}

    public String toString(){
        StringBuilder buf = new StringBuilder();
        buf.append("[");
        for(int i=0;i<rows;i++){
            buf.append("[");
            for(int j = 0; j<cols; j++){
                buf.append(data[i*cols + j]);
                if(j!=cols-1){buf.append(" ");}
            }
            buf.append("]");
            if(i!=rows-1){buf.append("\n");}

            //...
        }
        buf.append("]\n");
        //...
        return buf.toString();
    }

    void reshape(int newRows,int newCols){
        if(rows*cols != newRows*newCols)
            throw new RuntimeException(String.format("%d x %d matrix can't be reshaped to %d x %d",rows,cols,newRows,newCols));

        double[] reshaped = new double[newCols*newRows];
        int index = 0;
        for (int i = 0; i<rows; i++){
            for (int j = 0; j<cols; j++){
                reshaped[index++] = this.data[i*cols + j];
            }
        }
        this.data = new double[newCols * newRows];
        index=0;
        for (int i = 0; i<newRows; i++){
            for (int j = 0; j<newCols; j++){
                data[i*newCols + j] = reshaped[index++];
            }
        }
        this.rows = newRows;
        this.cols = newCols;
    }

    Matrix add(Matrix m){
        Matrix n = new Matrix(rows, cols);
        if(rows!=m.rows || cols!=m.cols)
            throw new RuntimeException(String.format("%d x %d matrix can't be added to %d x %d",rows,cols,m.rows,m.cols));
        for(int i = 0; i<rows; i++){
            for(int j = 0; j<cols; j++){
                n.data[i*cols + j] = data[i*cols + j] + m.data[i*cols + j];
            }
        }
        return n;
    }
    Matrix add(double w){
        Matrix n = new Matrix(rows, cols);
        for(int i = 0; i<rows; i++){
            for(int j = 0; j<cols; j++){
                n.data[i*cols + j] = data[i*cols + j] + w;
            }
        }
        return n;
    }
    Matrix sub(Matrix m){
        Matrix n = new Matrix(rows, cols);
        if(rows!=m.rows || cols!=m.cols)
            throw new RuntimeException(String.format("%d x %d matrix can't be substracted to %d x %d",rows,cols,m.rows,m.cols));
        for(int i = 0; i<rows; i++){
            for(int j = 0; j<cols; j++){
                n.data[i*cols + j] = data[i*cols + j] - m.data[i*cols + j];
            }
        }
        return n;
    }
    Matrix sub(double w){
        Matrix n = new Matrix(rows, cols);
        for(int i = 0; i<rows; i++){
            for(int j = 0; j<cols; j++){
                n.data[i*cols + j] = data[i*cols + j] - w;
            }
        }
        return n;
    }
    Matrix mul(Matrix m){
        Matrix n = new Matrix(rows, cols);
        if(rows!=m.rows || cols!=m.cols)
            throw new RuntimeException(String.format("%d x %d matrix can't be multiplied to %d x %d",rows,cols,m.rows,m.cols));
        for(int i = 0; i<rows; i++){
            for(int j = 0; j<cols; j++){
                n.data[i*cols + j] = data[i*cols + j] * m.data[i*cols + j];
            }
        }
        return n;
    }
    Matrix mul(double w){
        Matrix n = new Matrix(rows, cols);
        for(int i = 0; i<rows; i++){
            for(int j = 0; j<cols; j++){
                n.data[i*cols + j] = data[i*cols + j] * w;
            }
        }
        return n;
    }
    Matrix div(Matrix m){
        Matrix n = new Matrix(rows, cols);
        if(rows!=m.rows || cols!=m.cols)
            throw new RuntimeException(String.format("%d x %d matrix can't be divided to %d x %d",rows,cols,m.rows,m.cols));
        for(int i = 0; i<rows; i++){
            for(int j = 0; j<cols; j++){
                n.data[i*cols + j] = data[i*cols + j] / m.data[i*cols + j];
            }
        }
        return n;
    }
    Matrix div(double w){
        if(w==0)
            throw new RuntimeException("matrix can't be divided by 0");

        Matrix n = new Matrix(rows, cols);
        for(int i = 0; i<rows; i++){
            for(int j = 0; j<cols; j++){
                n.data[i*cols + j] = data[i*cols + j] / w;
            }
        }
        return n;
    }

    Matrix dot(Matrix m){
        Matrix dotMatrix = new Matrix(rows, m.cols);
        for(int i = 0; i<dotMatrix.rows; i++){
            double sum = 0;
            for (int j = 0; j<dotMatrix.cols; j++){
                sum = 0;
                for(int k = 0; k<cols; k++){
                    sum = sum + data[i*cols + k]*data[k*m.cols + j];
                }
                dotMatrix.set(i, j, sum);
            }
        }
        return dotMatrix;
    }

    double frobenius(){
        double sum = 0;
        for(int i = 0; i<rows; i++){
            for (int j = 0; j<cols; j++){
                double k = get(i, j);
                sum = sum + k*k;
            }
        }
        return Math.sqrt(sum);
    }

    public Matrix random(int rows, int cols){
        Matrix m = new Matrix(rows,cols);
        Random r = new Random();
        //m.set(0,0,r.nextDouble());
        for(int i = 0; i<rows; i++){
            for(int j = 0; j<cols; j++){
                m.set(i,j,r.nextDouble());
            }
        }
        return m;
    }

    public static Matrix eye(int n){
        Matrix m = new Matrix(n,n);
        for(int i =0; i<n; i++){
            m.set(i,i,1);
        }
        return m;
    }

    public void gauss() {
        for (int i = 0; i < rows; i++) {
            for (int k = i + 1; k < rows; k++) {
                if (Math.abs(get(k,i)) > Math.abs(get(i,i))) {
                    for (int j = 0; j < cols; j++) {
                        double temp = get(i,j);
                        set(i,j,get(k,j));
                        set(k,j,temp);
                    }
                }
            }
            for (int k = i + 1; k < rows; k++) {
                double factor = get(k,i) / get(i,i);
                for (int j = i; j < cols; j++) {
                    data[k * cols + j] -= factor * data[i * cols + j];
                }
            }
        }
    }

    double dotProduct(){
        this.gauss();
        double sum = 0;
        for (int i = 0; i<rows; i++){
            sum = sum * get(i,i);
        }
        return sum;
    }

    private void swapRows(int i1, int i2) {
        for (int j = 0; j < rows ; j++){
            double temp = get(i1,j);
            this.set(i1,j,get(i2,j));
            set(i2,j,temp);
        }
    }

    Matrix inv(){
        if (rows != cols) {
            throw new RuntimeException("Matrix must be square to invert.");
        }

        Matrix out = new Matrix(rows, cols);

        for (int i = 0; i < rows; i++) {
            out.set(i, i, 1.0);
        }

        for (int i = 0; i < rows; i++) {
            double max = Math.abs(get(i, i));
            int pivot = i;
            for (int k = i; k < rows; k++) {
                if (max < Math.abs(get(k, i))) {
                    max = Math.abs(get(k, i));
                    pivot = k;
                }
            }

            if (i != pivot) {
                swapRows(i, pivot);
                out.swapRows(i, pivot);
            }

            if (Math.abs(get(i, i)) < VERY_SMALL) {
                throw new RuntimeException("Matrix is singular or nearly singular");
            }

            double divby = get(i, i);
            for (int j = 0; j < cols; j++) {
                out.set(i, j, out.get(i, j) / divby);
                set(i, j, get(i, j) / divby);
            }

            for (int j = 0; j < rows; j++) {
                if (j != i && Math.abs(get(j, i)) > 0) {
                    double mulby = get(j, i);
                    for (int k = 0; k < cols; k++) {
                        set(j, k, get(j, k) - mulby * get(i, k));
                        out.set(j, k, out.get(j, k) - mulby * out.get(i, k));
                    }
                }
            }
        }
        return out;
    }

    //...
//    public static void main(String [] args){
//        Matrix m = new Matrix(new double[][]{{1,2,3,4},{5,6},{7,8},{9}});
//        Matrix m2 = new Matrix(new double[][]{{5,2,3,4,5},{6,2,3,4,7},{8,2,5,2,9},{0,0,2,8,15},{0,2,0,22,9}});
//        Matrix m3 = new Matrix(new double[][]{{1,2,3,4},{5,6},{7,8},{9}});
//        Matrix m4 = new Matrix(new double[][]{{-1,7},{1,-5}});
//        System.out.println(m2.inv());
//        System.out.println(m4.inv());
////        System.out.print(m.add(m3));
////        m.gauss();
////        m.reshape(4,4);
////        m2.reshape(5,3);
////        System.out.print(m2.toString());
////        System.out.print(m.toString());
//        //System.out.print(m.div(0));
//        //System.out.print(m.dot(m));
////        System.out.println(m.toString());
////        System.out.println(m.sub(m).toString());
////        System.out.println(m.sub(m).frobenius());
////        System.out.println(random(2,3));
////        System.out.println(eye(5));
//        System.out.println(m.dotProduct());
//
//    }

};


