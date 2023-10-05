
import java.util.*;

public class Ass10Bankers {
    static boolean safe(int allocation[][], int need[][], int max[][], int available[][], int np, int nr) {
        boolean[] finish = new boolean[np];
        int[] safeSeq = new int[np];
        int[] work = new int[nr];
        for (int i = 0; i < nr; i++)
            work[i] = available[0][i];
        int count = 0;
        while (count < np) {
            boolean found = false;
            for (int i = 0; i < np; i++) {
                if (finish[i] == false) {
                    int j;
                    for (j = 0; j < nr; j++)
                        if (need[i][j] > work[j])
                            break;
                    if (j == nr) {
                        for (int k = 0; k < nr; k++)
                            work[k] += allocation[i][k];
                        safeSeq[count++] = i;
                        finish[i] = true;
                        found = true;
                    }
                }
            }
            if (found == false) {
                System.out.print("System is not in safe state");
                return false;
            }
        }
        System.out.print("System is in safe state.\nSafe" + " sequence is: ");
        for (int i = 0; i < np; i++)
            System.out.print(safeSeq[i] + " ");
        return true;
    }

    public static void main(String args[]) {
        int allocation[][], need[][], max[][], available[][], np, nr;
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter number of processes:");
            np = sc.nextInt();
            System.out.println("Enter number of resources:");
            nr = sc.nextInt();
            need = new int[np][nr];
            max = new int[np][nr];
            allocation = new int[np][nr];
            available = new int[1][nr];

            System.out.println("Enter the max matrix:");
            for (int i = 0; i < np; i++)
                for (int j = 0; j < nr; j++)
                    max[i][j] = sc.nextInt();

            System.out.println("Enter the allocation matrix:");
            for (int i = 0; i < np; i++)
                for (int j = 0; j < nr; j++)
                    allocation[i][j] = sc.nextInt();
            System.out.println("Enter the available matrix:");
            for (int j = 0; j < 1; j++)
                for (int i = 0; i < nr; i++)
                    available[j][i] = sc.nextInt();
            System.out.println("Need Matrix:");
            for (int i = 0; i < np; i++) {
                for (int j = 0; j < nr; j++) {
                    need[i][j] = max[i][j] - allocation[i][j];
                    System.out.print(need[i][j] + " ");
                }
                System.out.println();
            }
        }
        safe(allocation, need, max, available, np, nr);
    }
}