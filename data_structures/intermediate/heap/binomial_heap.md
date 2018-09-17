# 이항 힙 (Binomial heap)
## 설명
이진 힙의 주요 응용은 우선 순위 큐의 구현이다. 이항 힙(Binomial Heap)은 이진 힙의 확장이며, 이진 힙이 제공하는 수행들과 함께 빠른 union 과 merge 를 지원한다.

_A Binomial Heap is a collection of Binomial Trees_
(이항 힙은 이항 트리의 집합이다.)

### 이항 트리(Binomial Tree)
- 차수(Order) 가 0 인 이항 트리는 단일 노드이다.
- 차수(Order) 가 k 인 이항 트리는 k - 1 차수 2개의 이항 트리를 취하고 가장 왼쪽 자식 혹은 다른 하나의 차수에 의해 구성될 수 있다.  
- 차수 k 의 이항 트리는 다음과 같은 속성을 가진다.
  - 정확히 2^k 개의 노드를 가진다.
  - 이항 트리의 깊이는 k 이다.
  - 깊이 i 에 있는 kCi 노드는 i 가 0, 1, 2, ... k 가 된다.
  - 루트는 차수 k 이고, 루트의 자식들은 왼쪽에서 오른쪽으로 차수 k-1, k-2..., 0을 가진다.

  ![이항 트리](https://contribute.geeksforgeeks.org/wp-content/uploads/Bionomial_tree_1.jpg)

### reference
- [wiki - 이항 힙](https://ko.wikipedia.org/wiki/이항_힙)
