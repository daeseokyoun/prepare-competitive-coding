# Linked List (연결 리스트)
## Reference
- [GeeksForGeeks-LinkedList](https://www.geeksforgeeks.org/data-structures/linked-list/)
  - 이미지 및 텍스트 번역

## 기본 구조
연결 리스트는 요소(element)들이 메모리에 연속적으로 저장되지 않은 선형 자료구조다. 연결 리스트에 있는 요소들은 아래의 이미지와 같이 포인터를 사용하여 연결한다.

![linked list image-GeeksForGeeks](https://www.geeksforgeeks.org/wp-content/uploads/gq/2013/03/Linkedlist.png)

간단하게 말하면, 연결 리스트는 노드라는 것으로 구성이되어 있고 그 노드는 데이터 항목과 다음 노드의 참조(link)를 갖고 있는 것이다.

## 단일 연결 리스트
### 연결 리스트 소개
- 배열과 같이 연결 리스트는 선형 자료구조이다.
- 배열과 다른 점은, 연속적인 공간에 저장되지 않는다는 것이다.; 즉, 각 노드들은 포인터로 연결된다.
![Introduction Linkedlist-GeeksForGeeks](https://www.geeksforgeeks.org/wp-content/uploads/gq/2013/03/Linkedlist.png)

#### 왜 연결리스트를 써야 하는가?
- 배열은 같은 타입의 데이터를 선형적으로 저장을 할 수 있지만, 다음과 같은 제약이 있다.
  - 배열은 크기가 고정적이다: 그래서 미리 요소 수의 상한선을 알고 있어야 한다. 또한, 일반적으로, 상한의 수와 같은 크기로 할당된 메모리로 무의미하게 할당된 공간이 있다.
  - 새로운 요소를 삽입하는 것은 비용이 든다. 이유는 새로운 요소를 위한 공간이 마련이 되어야 하고 원래 있던 요소들은 밀려서 들어가야만 한다.

    예를 들면, 만약 정렬된 ID 를 관리하기 위해 id[] 배열이 있다고 가정하자.

    id[] =  [1000, 1010, 1050, 2000, 2040].

    그리고 만약 새로운 ID 1005 를 넣고 싶다면, 정렬된 것을 유지하기 위해 1010 을 포함한 이후의 요소들을 공간을 마련하기 위해 우측을 쉬프트 해야 한다. 삭제 또한 비용이 든다. 예를 들어 1010 의 ID 가 삭제되길 원한다면, 비어있는 공간 뒤에 있는 요소들을 앞으로 밀어줘야 하는 비용이 발생한다.

- 배열 대비 장점
  - 동적 크기
  - 삽입/삭제 연산의 용이

- 단점
  - 임의 요소 접근이 허용이 안됨. 요소를 찾기 위해 첫 노트부터 순차적으로 탐색해야 함. 이진 탐색을 연결 리스트로 할 수 없음.
  - 리스트에 있는 요소 마다 노드 간의 연결을 위한 포인터 공간이 추가적으로 필요함
  - 캐쉬 친화적이지 못함. 배열은 기본적으로 연속적인 공간을 할당하지만 연결 리스트는 그렇지 않음. 참조 지역성(locality of reference)가 적용되지 않음.

- 대표 노드
  - 연결 리스트는 연결 리스트의 맨 처음에 존재하는 노드가 있는데, 그것을 head 라고 부른다. 또한 비어있는 리스트의 경우는 head 가 NULL 이다.
  - 각 노드는 최소한 두 가지 부분으로 이루어져 있다

    1) 데이터

    2) 다음 노드의 포인터(혹은 참조)

  - C 에서는 구조체를 사용해서 노드를 구성하고, JAVA 는 분리된 class 를 통해 노드를 구성한다.

- Examples with JAVA
  - 정수형 데이터를 가지는 노드 클래스의 구현

  ```java
  class LinkedList
  {
      Node head;  // head of list

      /* Linked list Node*/
      class Node
      {
          int data;
          Node next;

          // Constructor to create a new node
          // Next is by default initialized
          // as null
          Node(int d) {data = d;}
      }
  }
  ```
