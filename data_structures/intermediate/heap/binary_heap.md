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

위의 알고리즘을 보면, 수행시간은 ![heap time complexity](https://www.geeksforgeeks.org/wp-content/ql-cache/quicklatex.com-ad1e0425bcffe637a86253c8fe18f69b_l3.svg) 이다. 각 HEAPIFY() 가 ![heapify time complexity](https://www.geeksforgeeks.org/wp-content/ql-cache/quicklatex.com-44bb8c9d3ee0919ae381f87c480882c1_l3.svg) 이고, BUILD-HEAP이 ![BUILD-HEAP](https://www.geeksforgeeks.org/wp-content/ql-cache/quicklatex.com-9567d404baff2c322642ed8e476ad1af_l3.svg) 만큼 수행이 되기 때문이다. 여기서 점근 상한은 대체로 정확하지만 근접하지는 않을 것이다. 실제로 HEAP 을 구성하는 함수를 수행하는 시간 복잡도를 정확하게 계산/증명하는 방법에 대해 많은 설명들이 있다. 그 설명을 기반으로 `BUILD-HEAP()` 의 정확한 시간복잡도를 계산해보도록 하자.

우선적으로 n 에 대한 가정(assumption)을 해보자. n 은 ![n 의 공식](https://www.geeksforgeeks.org/wp-content/ql-cache/quicklatex.com-10098723d0619faa221e59a735844f4c_l3.svg) 로 나타낸다. 여기서 h 는 tree 의 높이를 나타낸다. 완전 이진 트리(complete binary tree)는 왼쪽에서 부터 node 를 채워나간다는 것은 알고 있을 것이다. 만약, 완전 이진 트리의 높이가 3이고 모든 리프노들들이 채워져있다면 아래와 같은 모습을 하고 있을 것이다.
```
               O                  level 3
            /     \
           O       O              level 2
          / \     /  \
         O   O   O    O           level 1
        / \ / \ /  \  / \
       O  O O O O  O O   O        level 0
```
이제 각 높이(h) 에 대해 각 노드의 최대 개수를 구하는 공식을 확인해보자. ![n 의 공식](https://www.geeksforgeeks.org/wp-content/ql-cache/quicklatex.com-10098723d0619faa221e59a735844f4c_l3.svg) 공식에서 n / 2^(h+1) 에서 n 은 모든 노드의 개수 15 가되고 h 는 각 높이에 따른 값이 될 것이다. 그리고 대괄호에서 위만 꺽여있는 기호는 ceil 을 적용하는 것이다. ceil 은 올림, 소수점 이하의 값이 있다면 올림하여 처리한다. 예를 들어, 위의 트리에서 n = 15 이고 h = 0 의 최대 노드 개수를 구하면, `15 / 2^(0 + 1) = 15 / 2` 가 되고 `7.5` 가 된다. `ceil(7.5) = 8` 이므로 level 0 의 노드 최대 개수는 8개가 된다. 말그대로 최대 값이다. 완전 이진 트리는 마지막 레벨의 노드들이 왼쪽에서 채워지고 다 채워지지 않을 수도 있기때문에 마지막 노드에서는 최대값이라고 보면 된다. 일단 공식을 증명하는데서는 모든 leaf 노드가 채워져있다고 가정하자.
앞서 `heapify` 함수를 보면, 주어진 노드에서 시작하여 트리 아래로 내려오면서 최소값을 위로 올려주는 형식으로 진행되었다. level 0 에서는 heapify 를 수행하지 않고, level 1 은 아래로 한번만 내려오면서 수행된다. level 2 는 level 1 까지 2번의 수행을 하고, 이렇게 되면 heapify() 는 O(h) 만큼 반복이 된다는 것이다. `for i := floor(heapsize/2) downto 1` 는 현재 node 가 15 개라면, `for i:= floor(7.5) downto 1`이 된다. floor(7.5) => 7 과 같다. 1 부터 시작하는 배열의 인덱스로 생각을 하게 되면,
```
               1                  level 3
            /     \
           2        3              level 2
          / \      /  \
         4   5     6    7           level 1
        / \ /  \  /  \  / \
       8  9 10 11 12 13 14 15        level 0
```
7은 level 1 의 맨 끝 노드이다. 여기서 heapify 수행하고, 6번 수행하고.. 3번, 1번 식으로 heapify 호출을 한다. 공식을 한번 보자.

![heapify 복잡도 공식](https://www.geeksforgeeks.org/wp-content/ql-cache/quicklatex.com-c68cff1daa1d0880c2e65ab4062ef3d1_l3.svg)

T(n) : n 개 노드에 대한 시간 복잡도
heapify 는 높이 0 ~ lg(n) 까지 호출이 되며 각 높이 h 의  ![n 의 공식](https://www.geeksforgeeks.org/wp-content/ql-cache/quicklatex.com-10098723d0619faa221e59a735844f4c_l3.svg) 개수 만큼 호출이 된다. 각 호출되는 heapify 는 높이에 따라 다른 시간 복잡도를 가진다. 위의 그림에 level 1에서 수행하면 O(1)이고(아래로 내려갈 필요 없다.) level 2에서는 level 1 까지 다시 내려가봐야 하므로, O(2)가 된다. 그래서 각 높이의 노드 개수 * O(h) 가 해당 level 의 시간 복잡도이며, 모든 level 을 다 하게 되면 위의 공식이 나오게 되는 것이다. 여기서 O(h) 는 상수 시간 c * h 로 공식 변환할 수 있다. 그리고 2^(h + 1) = 2 * 2^h 과 같다. 여기서 상수 부분을 시그마 밖으로 빼자. 1/2, n 그리고 c 이다. 위의 공식에서는 2 와 c 는 없지만 있다고 생각하자. 그리고 시간 복잡도 계산을 위해 h = 0 ~ lg(n) 부분을 0 ~ 무한대로 변환한다. 그리고 0 ~ 무한대로 h/2^h 더하게 되면 2 와 가까운 값이 나오게 된다. 이에 대한 증명은,

|x| < 0 때 성립되는,
![x<0 때, 공식](https://www.geeksforgeeks.org/wp-content/ql-cache/quicklatex.com-185c07cec4efa614ebfb52b4434fa6b5_l3.svg) 을 양쪽 미분을 하고, 다시 x 를 양쪽에 곱해주면, ![미분](https://www.geeksforgeeks.org/wp-content/ql-cache/quicklatex.com-f533b28af7646ab9d7894fe78e24e68a_l3.svg) 가 되고 x = 1/2 를 넣으면 앞서 설명했던 공식이 된다. 오른쪽 공식에도 1/2 를 넣어 계산을 하게 되면 2의 값이 나오게 된다. 그렇다면, 실제 O(cn/2 * 2)가 되고 시간복잡도의 결론은 O(n)이 된다.

### 시간복잡도 참고
- [Youtube- heapify 시간복잡도 계산](https://www.youtube.com/watch?v=HI97KDV23Ig)
- [heapify 시간복잡도 분석](http://www.cs.umd.edu/~meesh/351/mount/lectures/lect14-heapsort-analysis-part.pdf)
- [geometric progression](https://en.wikipedia.org/wiki/Geometric_progression)
