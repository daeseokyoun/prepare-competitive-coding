# 이진 힙(Binary Heap)
## 원본 링크
- [GeeksForGeeks-binary heap](https://www.geeksforgeeks.org/binary-heap/)

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
