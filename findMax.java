package mytest;

public class findMax {
    public static void main(String[] args) {
        int[] num1={1,-2,3,5,-1};
        int[] num2={1,-2,3,-8,5,1};
        int[] num3={1,-2,3,-2,5,1};     //输入三个数组
        int res1,res2,res3;
        res1=getMax(num1);
        res2=getMax(num2);
        res3=getMax(num3);              //接收最大子数组之和
        System.out.println(res1+"\n"+res2+"\n"+res3+"\n");

    }
    public static int getMax(int[] num){    //求解问题
        int maxSum=num[0];                  //初始化最大子数组之和
        int currentSum=num[0];              //初始化当前子数组之和
        for(int i=0;i<num.length;i++){
            currentSum=Math.max(num[i],currentSum+num[i]);      //更新当前和
            maxSum=Math.max(maxSum,currentSum);                 //更新最大和
        }
        return maxSum;                  //返回
    }
}
