package AVL;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int[] a;
        Random random = new Random();
        try (FileWriter writer = new FileWriter("AVLTree.csv")) {
            a = new int[10000];
            int size = a.length;
            int avgT1 = 0;
            int avgC1 = 0;
            int avgT2 = 0;
            int avgC2 = 0;
            int avgT3 = 0;
            int avgC3 = 0;
            for (int i = 0; i < a.length; i++) {
                a[i] = random.nextInt(10000);
            }
            AVLTree avlTree = new AVLTree();
            for (int i = 0; i < a.length; i++) {
                avlTree.count = 0;
                int value =  random.nextInt(10000);
                long start1 = System.nanoTime();
                avlTree.insert(value);
                long finish1 = System.nanoTime();
                int count = avlTree.getCount();
                avgT1 += finish1 - start1;
                avgC1 += count;
                writer.write(i + ";" + a[i] + ";" + count + ";" + (finish1 - start1) + "\n" );
            }
            System.out.println("Среднее время 1: " + avgT1 / 10000);
            System.out.println("Среднее кол - во итераций 1: " + avgC1 / 10000);
            writer.write("\n");
            for (int i = 1; i < 101; i++){
                avlTree.count = 0;
                int ind =  random.nextInt(a.length - 1);
                long start2 = System.nanoTime();
                avlTree.search(a[ind]);
                long finish2 = System.nanoTime();
                int count = avlTree.getCount();
                avgT2 += finish2 - start2;
                avgC2 += count;
                writer.write(i + ";" + a[ind] + ";" + count + ";" + (finish2 - start2) + "\n" );
            }
            System.out.println("Среднее время 2: " + avgT2 / 100);
            System.out.println("Среднее кол - во итераций 2: " + avgC2 / 100);
            writer.write("\n");
            for (int i = 1; i < 1001; i++){
                avlTree.count = 0;
                int ind =  random.nextInt(a.length - 1);
                long start3 = System.nanoTime();
                avlTree.remove(a[ind]);
                long finish3 = System.nanoTime();
                int count = avlTree.getCount();
                avgT3 += finish3 - start3;
                avgC3 += count;
                writer.write(i + ";" + a[ind] + ";" + count + ";" + (finish3 - start3) + "\n" );
            }
            System.out.println("Среднее время 3: " + avgT3 / 1000);
            System.out.println("Среднее кол - во итераций 3: " + avgC3 / 1000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
