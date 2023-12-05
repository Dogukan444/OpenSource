public class MyClass {
    public static void main(String args[]) {
        Heap h = new Heap();
        h.print();
        h.ekle(2);
        h.print();
    }
}

class Heap {
    int[] dizi;
    int count;

    public Heap() {
        dizi = new int[100];
        count = -1;
    }

    public int parent(int i) {
        if (i - 1 < 0) {
            return -1;
        }
        return (i - 1) >> 1;
    }

    public int sol_cocuk(int i) {
        int l = i * 2 + 1;
        if (i > count || l > count) {
            return -1;
        }
        return l;
    }

    public int sag_cocuk(int i) {
        int r = i * 2 + 2;
        if (i > count || r > count) {
            return -1;
        }
        return r;
    }

    public void takas(int i, int j) {
        int temp = dizi[i];
        dizi[i] = dizi[j];
        dizi[j] = temp;
    }

    public void percolateUp(int i) {
        int size = count;
        while (size > 0 && dizi[i] < dizi[parent(i)]) {
            takas(i, parent(i));
            i = parent(i);
        }
    }

    public void heapify(int i) {
        int degis;
        do {
            degis = 0;
            int sol = sol_cocuk(i);
            int sag = sag_cocuk(i);

            if (sol != -1 && dizi[i] > dizi[sol]) {
                degis = 1;
                takas(i, sol);
                i = sol;
            } else if (sag != -1 && dizi[i] > dizi[sag]) {
                degis = 1;
                takas(i, sag);
                i = sag;
            }
        } while (degis == 1);
    }

    public void ekle(int i) {
        dizi[++count] = i;
        percolateUp(count);
    }

    public void print() {
        for (int i = 0; i <= count; i++) {
            System.out.print(dizi[i] + " ");
        }
        System.out.println();
    }
}