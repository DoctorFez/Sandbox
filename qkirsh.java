//Quick Kirsh
/////////////////////////////////////////////////////////////////////
import java.io.*;
/////////////////////////////////////////////////////////////////////
class qkirsh
{
//-------------------------------------------------------------------
   public static void main (String [] args) throws Exception
   {
      int [][] image = {{0,1,1,0,1,0,1,1,1,0},
                        {0,0,1,0,0,1,1,0,1,0},
                        {0,1,1,0,1,0,1,1,0,0},
                        {0,0,1,0,0,1,0,1,1,0},
                        {0,1,1,1,0,1,0,1,0,0},
                        {1,0,0,0,0,1,1,1,0,0},
                        {0,1,0,1,0,0,0,1,1,1}};
      print(kirsh(image));
   }
//-------------------------------------------------------------------
   public static int[][] kirsh (int [][] matrix)
   {
      int H = matrix[0].length;
      int W = matrix.length;
      
      int [][] oGrid = pad(matrix,3);
      int [][] mGrid = new int [W][H];

      int MAX = -1000;
      int sum = 0;

      //Loop through the image
      for ( int y = 1; y < H+1; y++)
      {
         for ( int x = 1; x < W+1; x++)
         {
            MAX = -1000;
            // Calculate each mask
            // Color the pixel according to the maxium value
            sum =  -3*oGrid[x-1][y-1] -3*oGrid[x  ][y-1]+5*oGrid[x+1][y-1]
                   -3*oGrid[x-1][y]                     +5*oGrid[x+1][y]
                   -3*oGrid[x-1][y+1] -3*oGrid[x+1][y+1]+5*oGrid[x+1][y+1];
            if ( MAX <= sum) {MAX = sum; mGrid[x-1][y-1] = 0;}

            sum =  -3*oGrid[x-1][y-1] +5*oGrid[x  ][y-1]+5*oGrid[x+1][y-1]
                   -3*oGrid[x-1][y]                     +5*oGrid[x+1][y]
                   -3*oGrid[x-1][y+1] -3*oGrid[x+1][y+1]-3*oGrid[x+1][y+1];
            if ( MAX <= sum) {MAX = sum; mGrid[x-1][y-1] = 1;}

            sum =   5*oGrid[x-1][y-1] +5*oGrid[x  ][y-1]+5*oGrid[x+1][y-1]
                   -3*oGrid[x-1][y]                     -3*oGrid[x+1][y]
                   -3*oGrid[x-1][y+1] -3*oGrid[x+1][y+1]-3*oGrid[x+1][y+1];
            if ( MAX <= sum) {MAX = sum; mGrid[x-1][y-1] = 2;}

            sum =   5*oGrid[x-1][y-1] +5*oGrid[x  ][y-1]-3*oGrid[x+1][y-1]
                   +5*oGrid[x-1][y]                     -3*oGrid[x+1][y]
                   -3*oGrid[x-1][y+1] -3*oGrid[x+1][y+1]-3*oGrid[x+1][y+1];
            if ( MAX <= sum) {MAX = sum; mGrid[x-1][y-1] = 3;}

            sum =   5*oGrid[x-1][y-1] -3*oGrid[x  ][y-1]-3*oGrid[x+1][y-1]
                   +5*oGrid[x-1][y]                     -3*oGrid[x+1][y]
                   +5*oGrid[x-1][y+1] -3*oGrid[x+1][y+1]-3*oGrid[x+1][y+1];
            if ( MAX <= sum) {MAX = sum; mGrid[x-1][y-1] = 4;}

            sum =  -3*oGrid[x-1][y-1] -3*oGrid[x  ][y-1]-3*oGrid[x+1][y-1]
                   +5*oGrid[x-1][y]                     -3*oGrid[x+1][y]
                   +5*oGrid[x-1][y+1] +5*oGrid[x+1][y+1]-3*oGrid[x+1][y+1];
            if ( MAX <= sum) {MAX = sum; mGrid[x-1][y-1] = 5;}

            sum =  -3*oGrid[x-1][y-1] -3*oGrid[x  ][y-1]-3*oGrid[x+1][y-1]
                   -3*oGrid[x-1][y]                     -3*oGrid[x+1][y]
                   +5*oGrid[x-1][y+1] +5*oGrid[x+1][y+1]+5*oGrid[x+1][y+1];
            if ( MAX <= sum) {MAX = sum; mGrid[x-1][y-1] = 6;}

            sum =  -3*oGrid[x-1][y-1] -3*oGrid[x  ][y-1]-3*oGrid[x+1][y-1]
                   -3*oGrid[x-1][y]                     +5*oGrid[x+1][y]
                   -3*oGrid[x-1][y+1] +5*oGrid[x+1][y+1]+5*oGrid[x+1][y+1];
            if ( MAX <= sum) {MAX = sum; mGrid[x-1][y-1] = 7;}
         }
      }

      return mGrid;
   }
//-------------------------------------------------------------------
   public static int[][] pad (int [][] matrix, int mask)
   {
      int pad = (mask-1)/2;
      int W = matrix.length + (mask-1);
      int H = matrix[0].length + (mask-1);
      int [][] modM = new int [W][H];

      for (int x = pad; x < W-pad; x++)
      {
         for (int y = pad; y < H-pad; y++)
         {
            modM[x][y] = matrix[x-pad][y-pad];
         }
      }

      //Copy right/left nieghbors
      int x1 = pad-1;
      int x2 = W-pad;

      for ( int i = 0; i < pad; i++)
      {
         for ( int y = pad; y < H; y++)
         {
            modM[x1][y] = modM[x1+1][y];
            modM[x2][y] = modM[x2-1][y];
         }
         x1--;
         x2++;
      }

      //Copy Top/Bottom Neighbors
      int y1 = pad-1;
      int y2 = H-pad;

      for ( int i = 0; i < pad; i++)
      {
         for ( int x = 0; x < W; x++)
         {
            modM[x][y1] = modM[x][y1+1];
            modM[x][y2] = modM[x][y2-1];
         }
         y1--;
         y2++;
      }

      return modM;
   }
//-------------------------------------------------------------------
   public static int[][] depad (int [][] matrix, int mask)
   {
      int pad = (mask-1)/2;
      int W = matrix.length-(mask-1);
      int H = matrix[0].length-(mask-1);
      int [][] modM = new int [W][H];

      for (int x = 0; x < W; x++)
      {
         for (int y = 0; y < H; y++)
         {
            modM[x][y] = matrix[x+pad][y+pad];
         }
      }
      return modM;
   }
//-------------------------------------------------------------------
   public static void print (int [][] m)
   {
      for ( int i = 0; i < m.length; i++)
      {
         for ( int j = 0; j < m[0].length; j++)
         {
            System.out.print(m[i][j] + " ");
         }
         System.out.println();
      }
   }
//-------------------------------------------------------------------
}//end class qkirsh
/////////////////////////////////////////////////////////////////////