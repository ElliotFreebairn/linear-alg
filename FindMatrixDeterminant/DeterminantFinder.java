

public class DeterminantFinder {
  
  public static void main(String[] args) {
    int[][] matrix = { {1, 2, 4}, {3, 1, -1}, {2, 5, 6} };
    System.out.println(DeterminantFinder.getDeterminant(matrix));
  }

  public static int getDeterminant(int[][] matrix) {

    if(isTwoByTwo(matrix)) {
      return getTwoByTwoDeterminant(matrix);
    } else {
      int row = 0;
      int determinant = 0;

      for(int col = 0; col < matrix.length; col++) {
        int cofactor = getCofactor(row, col);
        int[][] minor = getMinor(matrix, row, col);

        if(isTwoByTwo(minor)) {
          determinant += cofactor * (matrix[row][col] * getTwoByTwoDeterminant(minor));
        } else {
          determinant += getDeterminant(minor);
        }
      }

      return determinant;
    }
  }

  private static int[][] getMinor(int[][] matrix, int row, int col) {
    int[][] minor = new int[matrix.length -1][matrix[0].length - 1];

    int rM = 0;
    int cM = 0;
    for(int i = 0; i < matrix.length; i++) {
      if(i != row) {
        for(int j = 0; j < matrix[i].length; j++) {
          if(j != col) {
            minor[rM][cM] = matrix[i][j];
            cM += 1;
          }
        }
        rM += 1;
        cM = 0;
      }
    }

    return minor;
  }

  private static int getCofactor(int row, int col) {
    return (int)Math.pow(-1.0, (double)row+col);
  }

  private static int getTwoByTwoDeterminant(int[][] matrix) {
    return ((matrix[0][0] * matrix[1][1]) - (matrix[0][1] * matrix[1][0]));
  }

  private static boolean isTwoByTwo(int[][] matrix) {
    if(matrix.length == 2 && matrix[0].length == 2) {
      return true;
    }
    return false;
  }

  private static void printMatrix(int[][] matrix) {
    for(int i = 0; i < matrix.length; i++) {
      for(int j = 0; j < matrix[i].length; j++) {
        System.out.print("|" + matrix[i][j] + "| " );
      }
      System.out.println("\n" + "-".repeat(matrix[i].length * 5));
    }
  }

}
