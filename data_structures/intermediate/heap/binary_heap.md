# 이진 힙(Binary Heap)
## 원본 링크
- [GeeksForGeeks-binary heap](https://www.geeksforgeeks.org/binary-heap/)

## 참고자료
- [MinHeap & Heap Sort](https://yaboong.github.io/data-structures/2018/02/10/2_array-advanced-heap-and-heapsort/)

## 이진 힙의 속성
- 이진 힙은 완전 이진 트리([complete binary tree](http://ehpub.co.kr/tag/완전이진트리/))이다.
  - 완전이진 트리는 노드를 삽입할 때 왼쪽부터 차례대로 추가하는 이진 트리
  - 이진 힙의 이 속성은 이진 힙을 배열에 저장하기 적합하도록 한다.
- 이진 힙은 최소 힙(Min Heap) 또는 최대 힙(Max Heap) 중 하나이다. 최소 힙에서는 루트 노드의 키가 이진 힙에 존재하는 모든 키의 값 중에 제일 작아야 한다.

### 최소 힙의 예제
```
          10                      10
       /      \               /       \  
     20        100          15         30  
   /                      /  \        /  \
 30                     40    50    100   40
```

### 이진 힙의 표현
- 이진 힙은 완전 이진 트리이다. 이진 힙은 일반적으로 배열로 표현할 수 있다.
  - 루트(root) 노드는 Arr[0] 에 들어있다.
  - 아래 테이블은 i 번째 노드 인덱스 접근을 나타낸다. 예) Arr[i]

| 배열 접근        | 반환값      |
| -------------   |:----------:|
| Arr[(i-1)/2]    | 부모 노드   |
| Arr[(2*i)+1]    | 왼쪽 자식 노드  |
| Arr[(2*i)+2]    | 오른쪽 자식 노드|

- 이렇게 생성된 배열의 인덱스의 순차적 접근은 이진 트리의 [Level Order 순회](http://bigbigdata.tistory.com/83)가 가능하다.

![level order traverse](https://www.geeksforgeeks.org/wp-content/uploads/binaryheap.png)

### 힙의 응요 사례
- 힙 정렬(Heap sort) : 이진 힙을 이용해서 O(nLogn) 시간 복잡도를 가지고 배열 정렬할 수 있다.
- 우선 순위 큐(Priority Queue): 우선순위 큐를 이진 힙으로 구현을 한다면, insert(), delete(), extractmax() 그리고 dreaseKey() 수행을 O(logN) 의 시간복잡도로 만들수 있다.
- 그래프 알고리즘: 우선 순위 큐는 다익스트라의 최단 경로나 프림의 최소 스패닝 트리와 같은 그래프 알고리즘에서 특별히 사용된다.
- 힙을 이용하면 다음과 같은 문제를 효율적으로 해결할 수 있다.
  - 배열의 K번째 요소 찾기
  - 거의 정렬된 배열 정렬하기
  - k 개의 정렬된 배열을 병합 정렬하기

### 최소 힙에 대한 함수
- getMin() : 최소 힙의 루트 노드의 값을 반환. 시간 복잡도는 O(1) 이다.
- extractMin() : 최소 힙에서 최소값을 제거한다. 시간 복잡도는 O(logn)이다. 이 작업은 가장 작은 값을 가지고 있는 루트 노드를 제거하고 나서 최소 힙의 구조를 다시 만들기 위한 작업이 필요하다(heapify())
- decreseKey() : 키의 값을 하나 감소시킨다. 이 작업의 시간복잡도는 O(logN)이다. 한 노드의 값을 하나 줄였을 때, 루트 노드보다 값이 크다면 아무일도 안해도 되지만, 반대로 작아진다면 힙 속성에 위배가 되는 것을 수정해 주기 위한 작업이 필요하다.
- insert() : 새로운 키 값을 삽입하는 것은 O(logN) 의 시간복잡도를 가진다. 만약 키값이 그 부모 노드의 키값보다 크다면, 아무것도 하지 않아도 된다. 반대의 경우라면, 순회하면서 힙 속성이 깨지는 부분을 수정해줘야 한다.
- delete() : 키 값하나를 지우는 것도 O(logN)의 시간 복잡도를 가진다. 이 부분은 decreseKey() 를 호출하는데, 정수 최소값(Integer.MIN_VALUE)으로 수행하여 무조건 루트노드로 만든 다음에 extractMin() 을 호출하여 루트노드를 꺼내준다. 이렇게 해서 delete() 를 구현한다.
- 아래는 기본 힙 수행의 구현이다.

```java
class MinHeap {
    int [] heapArr;
    int capacity;
    int heapEnd;

    MinHeap()
    {
        heapArr = new int [10];
        capacity = 10;
        heapEnd = 0;
    }

    MinHeap(int capacity)
    {
        this.capacity = capacity;
        this.heapArr = new int [capacity];
        heapEnd = 0;
    }

    int parent(int i)
    {
        return (i - 1) / 2;
    }

    int left(int i)
    {
        return (i * 2) + 1;
    }

    int right(int i)
    {
        return (i * 2) + 2;
    }

    int getMin()
    {
        return heapArr[0];
    }

    void swap(int i, int j)
    {
        int temp = heapArr[i];
        heapArr[i] = heapArr[j];
        heapArr[j] = temp;
    }

    void insertKey(int k)
    {
        if (heapEnd == capacity) {
            System.out.println("Overflow:Could not insert key to heap");
            return;
        }

        int i = heapEnd;
        heapArr[heapEnd++] = k;

        while (i > 0 && heapArr[parent(i)] > heapArr[i]) {
            swap(parent(i), i);
            i = parent(i);
        }
    }

    void decreaseKey(int index, int newval)
    {
        heapArr[index] = newval;

        while (index > 0 && heapArr[parent(index)] > heapArr[index]) {
            swap(parent(index), index);
            index = parent(index);
        }
    }

    void heapify(int i)
    {
        /*
         * Recursive way
        int l = left(i);
        int r = right(i);
        int small = i;

        if (l < heapEnd && heapArr[l] < heapArr[small])
            small = l;
        if (r < heapEnd && heapArr[r] < heapArr[small])
            small = r;

        if (small != i) {
            swap(small, i);
            heapify(small);
        }
        **************************/
        /* Interative way */
        while (left(i) < heapEnd) {
            int l = left(i);
            int r = right(i);

            int small = i;

            if (l < heapEnd && heapArr[l] < heapArr[small])
                small = l;
            if (r < heapEnd && heapArr[r] < heapArr[small])
                small = r;

            if (small != i) {
                swap(small, i);
                i = small;
            }
        }
    }

    void deleteKey(int i)
    {
        decreaseKey(i, Integer.MIN_VALUE);
        extractMin();
    }

    int extractMin()
    {
        if (heapEnd <= 0)
            return Integer.MIN_VALUE;
        if (heapEnd == 1) {
            heapEnd--;
            return heapArr[0];
        }

        int root = heapArr[0];
        heapArr[0] = heapArr[heapEnd - 1];
        heapEnd--;

        heapify(0);
        return root;
    }

    public static void main(String [] args)
    {
        MinHeap minHeap = new MinHeap(10);
        minHeap.insertKey(6);
        minHeap.insertKey(10);
        minHeap.insertKey(11);
        System.out.println(minHeap.getMin());
        minHeap.insertKey(5);
        System.out.println(minHeap.getMin());
        minHeap.decreaseKey(2, 4);
        System.out.println(minHeap.getMin());
        System.out.println("extrace min : " + minHeap.extractMin());
        System.out.println(minHeap.getMin());
        minHeap.deleteKey(0);
        System.out.println(minHeap.getMin());
    }
}
```

## 하나의 힙을 생성하는 시간 복잡도의 고려
### Reference
[GeeksForGeeks - Time Complexity of building a heap](https://www.geeksforgeeks.org/time-complexity-of-building-a-heap/)

### 설명
- 입력 배열 A 를 heap 으로 구성하기 위한 알고리즘은 다음과 같다.
```python
BUILD-HEAP(A)
    heapsize := size(A);
    for i := floor(heapsize/2) downto 1
        do HEAPIFY(A, i);
    end for
END
```

위의 알고리즘을 보면, 수행시간은 ![heap time complexity](https://www.geeksforgeeks.org/wp-content/ql-cache/quicklatex.com-ad1e0425bcffe637a86253c8fe18f69b_l3.svg) 이다. 각 HEAPIFY() 가 ![heapify time complexity](https://www.geeksforgeeks.org/wp-content/ql-cache/quicklatex.com-44bb8c9d3ee0919ae381f87c480882c1_l3.svg) 이고, BUILD-HEAP이 ![BUILD-HEAP](https://www.geeksforgeeks.org/wp-content/ql-cache/quicklatex.com-9567d404baff2c322642ed8e476ad1af_l3.svg) 만큼 수행이 되기 때문이다. 여기서 점근 상한은 대체로 정확하지만 근접하지는 않을 것이다.
우리는 여기서 Heapfipy() 의 실행시간은 트리의 높이 'h'(n 개의 node 가 있을 때, lg(n) 과 같다) 에 의존적이며 대부분의 서브 트리의 높이는 작다는 것을 관참함으로써 경계에 점근적으로 접근할 수 있다. 높이 'h' 는 트리를 따라 위로 올라갈 수록 증가한다. BUILD-HEAP() 의 라인 3번째에 루프가 있는데, 마지막 내부 노드의 인덱스에서 부터(height = 1) 루트(1) 노드 (height = lg(n))까지 진행한다. 그러므로, Heapify 는 각 노드 마다 다른 수행시간 O(h) 을 가진다. 힙을 구성하기 위한 시간 복잡도를 구하기 위해서는, 높이 h 인 노드의 개수를 반드시 알아야 한다. 
