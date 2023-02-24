package java003.example;
import java.util.Arrays;

public class Minimumbalane {
    Double[] charge(Double[] arr){
        for(int i=0;i<arr.length;i++){
            if(arr[i]>1000 && arr[i]<5000 ){
                arr[i] -= arr[i] * 0.03;
            }
            else if(arr[i]>=5000 && arr[i]<10000){
                arr[i] -= arr[i] * 0.05;
            }
        }
        return arr;
    }
    public static void main(String[] args) {
        Double[] balances = {12000.0,9000.0,4000.0,12000.0,15000.0,2000.0,1800.0,8000.0,20000.0,220000.0,4500.0,3500.0,9800.0,21000.0,900.0,7000.0,17000.0,9700.0,6000.0,13000.0};
        Minimumbalane obj = new Minimumbalane(); //Creating a object
        System.out.println(Arrays.toString(obj.charge(balances))); //Printing the array
    }
}
