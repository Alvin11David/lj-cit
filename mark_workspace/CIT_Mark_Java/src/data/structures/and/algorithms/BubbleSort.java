package data.structures.and.algorithms;

public class BubbleSort {
    public static int[] bubbleSort(int[] myArray){
        int i,j,size,temp;
        size = myArray.length;
        for(i =0; i<size;i++){
            for(j=0;j<size-i-1;j++){
                if(myArray[j]>myArray[j+1]){
                    temp = myArray[j];
                    myArray[j] = myArray[j+1];
                    myArray[j+1] = temp;
                }
            }
        }
        return myArray;
    }
    public static void main(String[] args){
        int[] nums = {90,76,21,90,30};
        for(int num : bubbleSort(nums)){
            System.out.print(num+ " ");
        }
}
    }
