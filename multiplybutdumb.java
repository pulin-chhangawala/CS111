public class multiplybutdumb {
    public static void main (String[] args){
        int n1 = Integer.parseInt(args[0]);
        int n2 = Integer.parseInt(args[1]);
        int total=0;
        while (n1>0) {
            total += n2;
            n1--;
        }
        System.out.println(total); 
    }
}
