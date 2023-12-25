
public class IntArrayBag {
    private int manyItems;
    private int[] data;

    public IntArrayBag() {
        final int INITIAL_CAPACITY = 10;
        data = new int[INITIAL_CAPACITY];
        manyItems = 0;
    }

    public IntArrayBag(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("initialCapacity is negative");
        }
        data = new int[initialCapacity];
        manyItems = 0;
    }

    public void add(int element) {
        if (data.length == manyItems) {
            ensureCapacity(manyItems * 2 + 1);
        }
        data[manyItems] = element;
        manyItems++;
    }

    public void addAll(IntArrayBag addend) {
        if (manyItems + addend.manyItems > data.length) {
            ensureCapacity(manyItems + addend.manyItems);
        }
        if (addend != null) {
            // System.arraycopy(addend.data, 0, manyItems, addend.manyItems);
            manyItems = manyItems + addend.manyItems;
        }
    }

    public void addMany(int... elements) {
        if (data.length <= elements.length + manyItems) {
            ensureCapacity((manyItems + elements.length) * 2 + 1);
        }
        // System.arrayCopy(elements, 0, data, manyItems, elements.length);
        manyItems = manyItems + elements.length;
    }

    public IntArrayBag clone() {
        IntArrayBag answer;
        answer = new IntArrayBag();
        answer.data = data.clone();
        answer.manyItems = manyItems;
        return answer;
    }

    public int countOccurences(int target) {
        int answer = 0;
        int index;

        for (index = 0; index < manyItems; index++) {
            if (target == data[index]) {
                answer++;
            }
        }
        return answer;
    }

    public void ensureCapacity(int minimumCapacity) {
        int[] biggerArray;
        if (data.length < minimumCapacity) {
            biggerArray = new int[minimumCapacity];
            // System.arrayCopy(data, 0, biggerArray, 0, manyItems)
            data = biggerArray;
        }
    }

    public int getCapacity() {
        return data.length;
    }

    public boolean remove(int target) {
        int index = 0;
        while ((index < manyItems) && (target != data[index])) {
            index++;
        }
        if (index == manyItems) {
            return false;
        } else {
            manyItems--;
            data[index] = data[manyItems];
            return true;
        }
    }

    public int size() {
        return manyItems;
    }

    public void trimToSize() {
        int[] trimmedArray;
        if (data.length != manyItems) {
            trimmedArray = new int[manyItems];
            // System.arrayCopy(data, 0, trimmedArray, 0, manyItems);
            data = trimmedArray;
        }
    }
    // public static IntArrayBag untion(IntArrayBag b1, IntArrayBag b2) {
    // IntArrayBag answer = new IntArrayBag(b1.getCapacity + b2.getCapacity);

    // System.arrayCopy(b1.data, 0, answer.data, 0, b1.manyItems);
    // System.arrayCopy(b2.data, 0, answer.data, b1.manyItems, b2.manyItems);

    // answer.manyItems = b1.manyItems + b2.manyItems;
    // return answer;
    // }
}
