package lab2;

import java.util.Random;
import java.util.random.RandomGenerator;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {

    @org.junit.jupiter.api.Test
    void testMatrix(){
        Matrix matrix = new Matrix(3, 4);
        assertEquals(3, matrix.rows, "Rows should be 3");
        assertEquals(4, matrix.cols, "Cols should be 4");
        assertEquals(12, matrix.data.length, "Data array length should be rows * cols");
    }

    @org.junit.jupiter.api.Test
    void testMatrixDouble() {
        double[][] data = {{1, 2, 3}, {4, 5}};
        Matrix matrix = new Matrix(data);
        assertEquals(2, matrix.rows, "Rows should be 2");
        assertEquals(3, matrix.cols, "Cols should be 3");
        assertEquals(0, matrix.data[5], "Element should be 0");
    }

    @org.junit.jupiter.api.Test
    void asArray() {
        double[][] data = {{1, 2, 3}, {4, 5, 6}};
        Matrix matrix = new Matrix(data);
        double[][] array = matrix.asArray();
        assertArrayEquals(data, array, "asArray should return the correct 2D array");
    }

    @org.junit.jupiter.api.Test
    void get() {
        Matrix matrix = new Matrix(new double[][] {{1,4,2}, {2,4,1}});
        assertEquals(1, matrix.get(0, 0), "Index 0 0 should be 1");
        assertEquals(4, matrix.get(0, 1), "Index 0 1 should be 4");
        assertEquals(2, matrix.get(0, 2), "Index 0 2 should be 2");
        assertEquals(2, matrix.get(1, 0), "Index 1 0 should be 2");
        assertEquals(4, matrix.get(1, 1), "Index 1 1 should be 4");
        assertEquals(1, matrix.get(1, 2), "Index 1 2 should be 1");
    }

    @org.junit.jupiter.api.Test
    void set() {
        Matrix matrix = new Matrix(3,3);
        matrix.set(0, 0, 5);
        matrix.set(1, 1, 10);
        matrix.set(2, 2, 15);
        matrix.set(1, 2, 25);
        assertEquals(5, matrix.get(0, 0), "Index 0 0 should be 5");
        assertEquals(10, matrix.get(1, 1), "Index 1 1 should be 10");
        assertEquals(15, matrix.get(2, 2), "Index 2 2 should be 15");
        assertEquals(25, matrix.get(1, 2), "Index 1 2 should be 25");
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        String s= "[[1.0,2.3,4.56], [12.3,  45, 21.8]]";
        s= s.replaceAll("(\\[|\\]|\\s)+","");
        String[] t = s.split("(,)+");
        for(String x:t){
            System.out.println(String.format("\'%s\'",x ));
        }

        double[]d=new double[t.length];
        for(int i=0;i<t.length;i++) {
            d[i] = Double.parseDouble(t[i]);
        }

        double[][] arr =new double[1][];
        arr[0]=d;

        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[i].length;j++){
                System.out.println(arr[i][j]);
            }
        }
    }

    @org.junit.jupiter.api.Test
    void reshape() {
        Matrix matrix = new Matrix(new double[][]{{1,2,3},{4,5,6},{7,8,9},{10,11,12}});
        matrix.reshape(2,6);
        for (int i = 0; i < matrix.rows * matrix.cols; i++) {
            assertEquals(i+1, matrix.data[i], "reshape doesn't work");
        }
        assertThrows(RuntimeException.class,()->matrix.reshape(4,1));
    }

    @org.junit.jupiter.api.Test
    void add() {
        Matrix matrix = new Matrix(new double[][]{{1,2,3},{4,5,6},{7,8,9},{10,11,12}});
        Matrix matrix2 = new Matrix(new double[][]{{-1,-2,-3},{-4,-5,-6},{-7,-8,-9},{-10,-11,-12}});
        Matrix matrix3 = new Matrix(new double[][]{{-1,-2,-3},{-4,-5,-6},{-7,-8,-9},{-10,-11,-12},{-13, -14}});
        matrix.add(matrix2);
        Matrix diff = matrix.add(matrix2);
        double err = diff.frobenius();
        assertEquals(err,0,1e-5);
        assertThrows(RuntimeException.class,()->matrix.add(matrix3));
    }

    @org.junit.jupiter.api.Test
    void testAdd() {
    }

    @org.junit.jupiter.api.Test
    void sub() {
        Matrix matrix = new Matrix(new double[][]{{1,2,3},{4,5,6},{7,8,9},{10,11,12}});
        Matrix matrix2 = new Matrix(new double[][]{{-1,-2,-3},{-4,-5,-6},{-7,-8,-9},{-10,-11,-12}});
        Matrix matrix3 = new Matrix(new double[][]{{-1,-2,-3},{-4,-5,-6},{-7,-8,-9},{-10,-11,-12},{-13, -14}});
        //matrix.sub(matrix);
        Matrix diff = matrix.sub(matrix);
        double err = diff.frobenius();
        //double[] err = new double[]{2,4,6,8,10,12,14,16,18};
        assertEquals(err,0, 1e-5);
        assertThrows(RuntimeException.class,()->matrix.sub(matrix3));
    }

    @org.junit.jupiter.api.Test
    void testSub() {
    }

    @org.junit.jupiter.api.Test
    void mul() {
        Matrix matrix = new Matrix(new double[][]{{1,2,3},{4,5,6},{7,8,9},{10,11,12}});
        Matrix matrix2 = new Matrix(new double[][]{{-1,-2,-3},{-4,-5,-6},{-7,-8,-9},{-10,-11,-12}});
        Matrix matrix3 = new Matrix(new double[][]{{-30,-36,-42},{-66,-81,-96},{-102,-126,-150}});
        Matrix matrix4 = new Matrix(new double[][]{{-1,-2,-3},{-4,-5,-6},{-7,-8,-9},{-10,-11,-12},{-13, -14}});
        //matrix.mul(matrix2);
        Matrix diff = matrix.mul(-1);
        Matrix out = diff.add(matrix);
        double act = out.frobenius();
        assertEquals(0,act, 1e-5);
        assertThrows(RuntimeException.class,()->matrix.mul(matrix4));
    }

    @org.junit.jupiter.api.Test
    void testMul() {
    }

    @org.junit.jupiter.api.Test
    void div() {
        Matrix matrix = new Matrix(new double[][]{{1,2,3},{4,5,6},{7,8,9},{10,11,12}});
        Matrix zeroMatrix = new Matrix(new double[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}});
        Matrix result = matrix.div(matrix);
        double act = result.frobenius();
        double err = Math.sqrt(matrix.rows * matrix.cols);
        assertEquals(err, act, 1e-5, "Frobenius norm should equals rows*cols after dividing by self");
        assertThrows(RuntimeException.class, () -> matrix.div(zeroMatrix));
    }

    @org.junit.jupiter.api.Test
    void testDiv() {
    }

    @org.junit.jupiter.api.Test
    void dot() {
        Matrix matrix = new Matrix(new double[][]{{1,5},{2,3},{1,7}});
        Matrix matrix2 = new Matrix(new double[][]{{1,2,3,7}, {5,2,8,1}});
        Matrix diff = matrix.dot(matrix2);
        Matrix matrix3 = new Matrix(new double[][]{{26,12,43,12}, {17,10,30,17}, {36,16,59,14}});
        assertArrayEquals(matrix3.asArray(), diff.asArray(),"dot powinien być równy matrix3");
    }

    @org.junit.jupiter.api.Test
    void eye() {
        int n = 5;
        Matrix matrix = Matrix.eye(n);
        double act = matrix.frobenius();
        double err = Math.sqrt(n);
        assertEquals(err,act, 1e-5);
    }

    @org.junit.jupiter.api.Test
    void inv() {
        Matrix matrix = new Matrix(new double[][]{{2, 4},{6, 8}});
        Matrix inv = matrix.inv();
        Matrix vec = new Matrix(new double[][]{{5},{7}});

        Matrix res1 = matrix.dot(vec);
        Matrix res2 = inv.dot(res1);
        double act = res1.frobenius();
        double err = res2.frobenius();
        assertEquals(err,act, 1e-5);
        assertArrayEquals(vec.asArray(), res2.asArray(), "powinny być równe");
    }

    @org.junit.jupiter.api.Test
    void inv2() {
        Matrix matrix = new Matrix(new double[][]{{2, 4},{6, 2}});
        //Matrix exp = new Matrix(new double[][]{{-0.1, 0.2},{0.3, -0.1}});
        Matrix inv = matrix.inv();
        Matrix exp = Matrix.eye(2);

        Matrix act = inv.dot(matrix);

        assertArrayEquals(exp.asArray(), act.asArray(),  "powinny być równe");
    }
}