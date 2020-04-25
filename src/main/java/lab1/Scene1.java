package lab1;

public class Scene1 {
    public static final double[][] cube_default = {
            //front
            {-0.25d, -0.25d, 0.25d}, //1
            {0.25d, -0.25d, 0.25d}, //2
            {0.25d, 0.25d, 0.25d}, //3
            {-0.25d, 0.25d, 0.25d}, //4

            //back
            {0.25d, 0.25d, -0.25d}, //5
            {-0.25d, 0.25d, -0.25d}, //6
            {-0.25d, -0.25d, -0.25d}, //7
            {0.25d, -0.25d, -0.25d}, //8

            //top
            {-0.25d, 0.25d, 0.25d}, //4
            {0.25d, 0.25d, 0.25d}, //3
            {0.25d, 0.25d, -0.25d}, //5
            {-0.25d, 0.25d, -0.25d}, //6

            //bottom
            {-0.25d, -0.25d, -0.25d}, //7
            {0.25d, -0.25d, -0.25d}, //8
            {0.25d, -0.25d, 0.25d}, //2
            {-0.25d, -0.25d, 0.25d}, //1

            //left
            {-0.25d, -0.25d, 0.25d}, //1
            {-0.25d, 0.25d, 0.25d}, //4
            {-0.25d, 0.25d, -0.25d}, //6
            {-0.25d, -0.25d, -0.25d}, //7

            //right
            {0.25d, -0.25d, 0.25d}, //2
            {0.25d, 0.25d, 0.25d}, //3
            {0.25d, 0.25d, -0.25d}, //5
            {0.25d, -0.25d, -0.25d} //8
    };

    public static final double[][] axis_default = {
            {0, 0, 0},
            {1, 0, 0},
            {0, 1, 0},
            {0, 0, -1}
    };

    public static double[][] cube = cube_default.clone();

    public static void commitRotation(int angle, String axis){
        double[][] result;
        switch (axis){
            case "X" : {
                result = Tools.multiply(Tools.createMatrix(cube), Tools.xRotationMatrix(angle));
                refreshCoordinates(result);
                break;
            }
            case "Y" : {
                result = Tools.multiply(Tools.createMatrix(cube), Tools.yRotationMatrix(angle));
                refreshCoordinates(result);
                break;
            }
            case "Z" : {
                result = Tools.multiply(Tools.createMatrix(cube), Tools.zRotationMatrix(angle));
                refreshCoordinates(result);
                break;
            }
        }
    }

    private static void refreshCoordinates(double[][] newCoordinates){

        for (int i = 0; i < cube_default.length; i++)
            for (int j = 0; j < cube_default[0].length; j++) {
                cube[i][j] = newCoordinates[i][j];
            }
    }
}
