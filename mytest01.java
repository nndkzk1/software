package mytest;

public class mytest01 {
    public static void main(String[] args) {
        int count=0;                        //计数器，判断是否为5的倍数
        boolean flag;                       //标志变量，true表示为素数
        for(int i=2;i<=20000;i++){          //循环数字
            flag=true;
            for(int j=3;j<i;j++){           //循环判断是否为素数
                if(i % j == 0){
                    flag=false;
                    break;
                }
            }
            if(flag==true){
                count++;
                System.out.print(i+"\t");
                if(count%5==0){
                    System.out.println("\n");
                }
            }                               //按5个为一行输出素数
        }
    }

    public static boolean judge(int num){
        if(num<=1||num%2==0) return false;    //排除偶数和1
        if(num==2) return true;               //2为特殊偶数
        for(int i=3;i<Math.sqrt(num);i++){    //减少内层循环
            if(num%i==0) return false;        //判断
        }
        return true;
    }
}
