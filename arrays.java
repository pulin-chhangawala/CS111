import java.util.Random;
public class arrays {
    public static void main(String[] args){
        int n= Integer.parseInt(args[0]);
        Random rand = new Random();
        int[] a = new int[n];
        for (int i=0 ; i < a.length; i++)
            a[i] = rand.nextInt(i+1);

        for (int i=0; i< a.length; i++)
            System.out.print(a[i]+ " ");
        System.out.println();

        int max= a[0], min=a[0];
        for (int i=1; i<a.length; i++){
            if (a[i]>max) max = a[i];
            if (a[i]<min) min = a[i];
        }
        System.out.println("max:" + max + "min: " + min );

        for (int i=0; i<a.length-1; i++)
            for (int j=i+1; j< a.length; j++)
                if(a[i] == a[j])
                System.out.println("duplicate found" + a[i]);

        int target = 34;
        int targetCount = 0;
        for (int i=0; i<a.length; i++)
            if (a[i] == target)
                {
                    targetCount++;
                }
        System.out.println("target" + target + "appeared" + targetCount + "times" );

        targetCount = 0;
        target = 34;
        for (int i=0; i< a.length; i++);
                if (a[i] == target)
                {
                    targetCount++;
                }
        System.out.println("target" + target + "appeared" + targetCount + "times" );

        for(int i = 0; i<a.length/2; i++)
        {
            int tmp = a[i];
            a[i] = a[a.length - i -1];
            a[a.length -i -1] =tmp;
        }

            for (int i=0; i<a.length; i++)
            System.out.print(a[i] + " ");
        System.out.println();

        for (int i=0; i<a.length; i=i+2)
            a[i]=0;

        int[] newa = new int (2*a.length);
        



    }
}
