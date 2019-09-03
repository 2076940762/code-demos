package Array;


public class Array2Demo2
{
	public static void main(String [] args)
	{
		int [][] arr =new int [2][3];
		for(int i=0;i<2;i++)
		{
			for(int j=0;j<3;j++)
			{
				System.out.println(arr[i][j]);
			}
		}
		
		System.out.println("=====================");
			
		
		int arr1[][]=new int[3][];
		arr1[0]=new int[5];
		arr1[1]=new int[3];
		arr1[2]=new int [8];
		
		for(int i=0;i<arr1.length;i++)
		{
			for(int j=0;j<arr1[i].length;j++)
			{
				System.out.println(arr1[i][j]);
			}
			System.out.println("------------");
		}
		
		
		
		System.out.println("====================================");
		
		int arr2[][]= new int [10][];
		
		for(int i=0;i<10;i++)
		{
			arr2[i]=new int [2+i];
		}
		
		for(int i=0;i<arr2.length;i++)
		{
			for(int j=0;j<arr2[i].length;j++)
			{
				arr2[i][j]=(i+1)*(j+1);
				System.out.println(arr2[i][j]);
			}
		}
	}
}





