package lab1;

public class Tools {
    private static double k = Math.PI / 180;

    public static double[][] xRotationMatrix(int angle){
        double[][] matrix = defaultRotationMatrix();
        angle *= -1;

        //[1,   0,    0,   0]
        //[0,  cosA, sinA, 0]
        //[0, -sinA, cosA, 0]
        //[0,   0,    0,   1]

        matrix[1][1] = Math.cos(angle * k);
        matrix[2][1] = -Math.sin(angle * k);
        matrix[1][2] = Math.sin(angle * k);
        matrix[2][2] = Math.cos(angle * k);

        return matrix;
    }

    public static double[][] yRotationMatrix(int angle){
        double[][] matrix = defaultRotationMatrix();
        angle *= -1;

        //[cosA, 0, -sinA, 0]
        //[  0 , 1,   0,   0]
        //[sinA, 0,  cosA, 0]
        //[  0,  0,  0,    1]

        matrix[0][0] = Math.cos(angle * k);
        matrix[2][0] = Math.sin(angle * k);
        matrix[0][2] = -Math.sin(angle * k);
        matrix[2][2] = Math.cos(angle * k);

        return matrix;
    }

    public static double[][] zRotationMatrix(int angle){
        double[][] matrix = defaultRotationMatrix();

        //[ cosA, sinA, 0, 0]
        //[-sinA, cosA, 0, 0]
        //[  0,     0,  1, 0]
        //[  0,     0,  0, 1]

        matrix[0][0] = Math.cos(angle * k);
        matrix[0][1] = Math.sin(angle * k);
        matrix[1][0] = -Math.sin(angle * k);
        matrix[1][1] = Math.cos(angle * k);

        return matrix;
    }

    public static double[][] multiply(double[][] first, double[][] second){
        double[][] result = new double[first.length][second[0].length];

        for (int i = 0; i < first.length; i++)
            for (int j = 0; j < second[0].length; j++)
                for (int k = 0; k < first[0].length; k++)
                    result[i][j] += first[i][k] * second[k][j];

        return result;
    }

    public static double[][] createMatrix(double[][] x){
        double[][] result = new double[x.length][x[0].length + 1];

        for (int i = 0; i < x.length; i++){
            for (int j = 0; j < x[0].length; j++){
                result[i][j] = x[i][j];
            }
            result[i][x[0].length] = 1.0d;
        }

        return (result);
    }

    private static double[][] defaultRotationMatrix(){
        double[][] matrix = new double[4][4];
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++){
                if (i==j) matrix[i][j] = 1;
                else matrix[i][j] = 0;
            }
        return matrix;
    }
}
