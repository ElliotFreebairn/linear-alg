public class GaussianElimination{
  
  public static void main(String[] args) {
    double[][] matrix = {
      {1, 2, -1, -4},
      {2, 3, -1, -11},
      {-2, 0, -3, 22},
    };
    System.out.println("Original matrix:");
    GaussianElimination.display(matrix);
    
    System.out.println("Reduced matrix");
    GaussianElimination.display(GaussianElimination.reducedRowEchelonForm(matrix));
  }

  public static double[][] reducedRowEchelonForm(double[][] matrix) {
    int lead = 0;
    int rowC = matrix.length;
    int colC = matrix[0].length;

    for(int row = 0; row < rowC; row++) {
      if(colC <= lead) {
        return matrix;
      }

      int ro = row;
      while(matrix[ro][lead] == 0) {
        ro += 1;
        if(rowC == ro) {
          ro = row;
          lead += 1;
          if(colC == lead) {
            return matrix;
          }
        }
      }
      
      // Swapping rows i and row
      double[] tempRow = matrix[row];
      matrix[row] = matrix[ro];
      matrix[ro] = tempRow;

      if(matrix[row][lead] != 0) {
        double divisor = matrix[row][lead];
        for(int col = 0; col < colC; col++) {
          matrix[row][col] /= divisor;
        }
      }

      for(int j = 0; j < rowC; j++) {
        if(j != row) {
          double mult = matrix[j][lead];
          for(int col = 0; col < colC; col++) {
            matrix[j][col] -= matrix[row][col] * mult;
          }
        }
      }

      lead += 1;
    }

    return matrix;
  }

  public static void display(double[][] matrix) {
    for(int row = 0; row < matrix.length; row++) {
      for(int col = 0; col < matrix[0].length; col++) {
        if(matrix[row][col] == -0.0) {
          matrix[row][col] = 0.0;
        }
        System.out.print(String.format("%6.1f", matrix[row][col]));
      }
      System.out.println();
    }
    System.out.println();
  }
  
}
