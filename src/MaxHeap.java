import java.util.Arrays;

public class MaxHeap implements Heap {
    int size;

    Integer[] data;

    public MaxHeap(int capacity) {
        this.size = 0;
        data = new Integer[capacity];
    }

    // build a heap based on data
    // to be implemented in O(nlogn)
    public void MaxHeapNLogN(Integer[] data) {
        data = Arrays.copyOf(data, data.length);
        for (Integer i : data)
            add(i);
    }

    // build a heap based on data
    // to be implemented in O(n)
    public void MaxHeapN(Integer[] data) {
        this.data = Arrays.copyOf(data, data.length);
        int lastParentIndex = parent(data.length - 1);
        this.size = data.length;

        for (int i = lastParentIndex; i >= 0; i--)
            heapifyDown(i);
    }

    // add an item to the heap
    public boolean add(Integer item) {
        if (size == data.length)
            return false;

        data[size] = item;
        heapifyUp(size);
        size++;
        return true;
    }

    // Makes sure that the element at currentIndex is in correct pos
    private void heapifyUp(int currentIndex) {
        int parentIndex = parent(currentIndex);
        if (data[currentIndex] > data[parentIndex]) {
            swap(currentIndex, parentIndex);
            heapifyUp(parentIndex);
        }
    }

    // return the max item in the heap
    public Integer get() {
        return data[0];
    }

    // remove the root item
    public Integer pop() {
        Integer toReturn = data[0];

        data[0] = data[size - 1];
        heapifyDown(0);

        data[size - 1] = null;
        size--;
        return toReturn;
    }

    private void heapifyDown(int currentIndex) {
        int childIndex = child(currentIndex);

        // If leaf do nothing
        if (childIndex >= size)
            return;

        // If no right child and left is bigger
        if (childIndex + 1 >= size) {
            if (data[currentIndex] < data[childIndex]) {
                swap(currentIndex, childIndex);
                heapifyDown(childIndex);
            }
        } else {
            if(data[childIndex] > data[childIndex + 1]){
                if(data[currentIndex] < data[childIndex]) {
                    swap(currentIndex, childIndex);
                    heapifyDown(childIndex);
                }
            } else {
                if(data[currentIndex] < data[childIndex + 1]) {
                    swap(currentIndex, childIndex + 1);
                    heapifyDown(childIndex + 1);
                }
            }
        }
    }

    // Swaps the two indices
    private void swap(int a, int b) {
        int temp = data[a];
        data[a] = data[b];
        data[b] = temp;
    }

    // Returns the index of the parent
    private int parent(int child) {
        return (child - 1) / 2;
    }

    // Returns the index of the first child
    private int child(int parent) {
        return 2 * parent + 1;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MaxHeap))
            return false;
        MaxHeap heap = (MaxHeap) obj;

        if (size != heap.size)
            return false;

        if (!(isValid() && heap.isValid()))
            return false;

        return Arrays.asList(data).subList(0, size)
                .containsAll(Arrays.asList(heap.data).subList(0, size));
    }

    @Override
    public String toString() {
        return Arrays.asList(data).subList(0, size).toString();
    }

    // Checks if the current heap is valid
    public boolean isValid() {
        return isValid(0);
    }

    // Recursively checks if the current heap is valid
    private boolean isValid(int current) {
        int childIndex = child(current);
        // If it is a leaf node return true
        if (childIndex > size ||
                childIndex + 1 > size)
            return true;

        // If first child is bigger return false
        if (data[current] < data[childIndex])
            return false;

        // If the second child is bigger return false
        if (childIndex + 1 < size &&
                data[current] < data[childIndex + 1])
            return false;

        // Make sure that both childrens' trees are also valid
        return isValid(childIndex) && isValid(childIndex + 1);
    }
}
