package HelloWold.Array;


//一维数组
public class ArrayDemo
{
	public static void main(String [] agrs)
	{
		int [] arr1 =new int [100];
		for(int i=0;i<100;i++)
		{
			System.out.println(arr1[i]);
		}
		
		double arr2[]={1,2,4,5,6,7,8,0,4,5,6,7,8,9};
		for(int i=0;i<arr2.length;i++)
		{
			System.out.println(arr2[i]);
		}
		
		float [] arr3 = new float [] {1,2,4,5,6,7,833,324534,340,4,5,6,7,8,9};
		for(int i=0;i<arr3.length;i++)
		{
			System.out.println(arr3[i]);
		}
		
		//数组越界异常
		//System.out.println(arr3[arr3.length]);
		
		System.out.println("arr3="+arr3);
	}
}










