public abstract class Heap<T extends Comparable<T>> {

    public Comparable<T>[] data;
    public int size;

    @SuppressWarnings("unchecked")
    public Heap() {
        this.data = (Comparable<T>[]) new Comparable[2];
        size = 0;
    }

    @SuppressWarnings("unchecked")
    public Heap(T[] array) {
        data = array.clone();
        size = array.length;
        int i = parentIndex(array.length - 1);
        while (i >= 0) {
            propogateDown(i);
            i--;
        }
    }

    private void propogateDown(int i) {
        Comparable<T> p = data[i];
        while (!isLeaf(i) && shouldSwap(i)) {
            if (leftChild(i) == null) {
                data[i] = data[getRightChildIndex(i)];
                data[getRightChildIndex(i)] = p;
                i = getRightChildIndex(i);
            } else if (rightChild(i) == null) {
                data[i] = data[getLeftChildIndex(i)];
                data[getLeftChildIndex(i)] = p;
                i = getLeftChildIndex(i);
            } else if (compare(rightChild(i), leftChild(i))) {
                data[i] = data[getRightChildIndex(i)];
                data[getRightChildIndex(i)] = p;
                i = getRightChildIndex(i);
            } else {
                data[i] = data[getLeftChildIndex(i)];
                data[getLeftChildIndex(i)] = p;
                i = getLeftChildIndex(i);
            }
        }
    }

    private boolean shouldSwap(int i) {
        boolean left = compare(leftChild(i), data[i]);
        boolean right = compare(rightChild(i), data[i]);
        return left || right;
    }

    private boolean isLeaf(int i) {
        int left = getLeftChildIndex(i);
        int right = getRightChildIndex(i);
        return (left >= size) && (right >= size);
    }

    private Comparable<T> leftChild(int i) {
        int leftIndex = getLeftChildIndex(i);
        if (leftIndex >= size) {
            return null;
        }
        return data[leftIndex];
    }

    private Comparable<T> rightChild(int i) {
        int rightIndex = getRightChildIndex(i);
        if (rightIndex >= size) {
            return null;
        }
        return data[rightIndex];
    }

    protected abstract boolean compare(Comparable<T> child, Comparable<T> parent);

    public void push(T item) {
        if (size == data.length) {
            Comparable<T>[] oldData = data;
            data = new Comparable[size * 2];
            System.arraycopy(oldData, 0, data, 0, size);
        }

        int i = size;
        data[i] = item;
        //item.compareTo((T) parent(i)) > 0
        while (i > 0 && compare(item, parent(i))) {
            Comparable<T> temp = data[i];
            data[i] = data[parentIndex(i)];
            data[parentIndex(i)] = temp;
            i = parentIndex(i);
        }
        size++;
    }

    private Comparable<T> parent(int i) {
        return data[parentIndex(i)];
    }

    private int parentIndex(int i) {
        return (i - 1) / 2;
    }

    public Comparable<T> pop() {
        Comparable<T> out = data[0];
        data[0] = data[size - 1];
        data[size - 1] = null;
        size--;
        propogateDown(0);
        return out;
    }

    public Comparable<T> peek() {
        return data[0];
    }

    /*
     *
     * Functions used for the toString
     * Do not change them but feel free to use them
     *
     */

    private int getLeftChildIndex(int parentIndex) {
        return 2 * parentIndex + 1;
    }

    private int getRightChildIndex(int parentIndex) {
        return 2 * parentIndex + 2;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        buildString(0, "", true, sb); // Start from the root
        return sb.toString();
    }

    private void buildString(int index, String prefix, boolean isTail, StringBuilder sb) {
        if (index < size) {
            String linePrefix = isTail ? "└── " : "┌── ";
            if (getRightChildIndex(index) < size) { // Check if there is a right child
                buildString(getRightChildIndex(index), prefix + (isTail ? "|   " : "    "), false, sb);
            }
            sb.append(prefix).append(linePrefix).append(data[index]).append("\n");
            if (getLeftChildIndex(index) < size) { // Check if there is a left child
                buildString(getLeftChildIndex(index), prefix + (isTail ? "    " : "│   "), true, sb);
            }
        }
    }

}
