# 연결리스트 노드 삽입
## 설명
- 이전 [소개](introduction.md) 에서 연결리스트의 간단한 소개를 알아보았다. 이전 소개에서 3개의 노드를 생성하고 생성된 노드들을 순회하는 프로그램도 간략히 알아보았다. 이번 챕터에서 다룰 구현은 아래의 연결리스트 클래스를 기본으로 한다.

```java
// Linked List Class
class LinkedList
{
    Node head;  // head of list

    /* Node Class */
    class Node
    {
        int data;
        Node next;

        // Constructor to create a new node
        Node(int d) {data = d; next = null; }
    }
}
```

이번 챕터에서는 연결리스트에 새로운 노드를 삽입하는 방법에 대해 논의를 할 예정이다. 논의할 삽입 방법은 3가지가 있다.
- 연결리스트 맨 앞에 새로운 노드 추가
- 주어진 노드 다음에 새로운 노드 추가
- 연결리스트 맨 뒤에 새로운 노드 추가

### 연결리스트 맨 앞에 새로운 노드 추가 (4 단계 프로세스)
새로운 노드는 항상 주어진 연결리스트 head 의 전에 추가되어야 한다. 그래서 새로 추가된 노드는 그 연결 리스트의 head 가 된다. 예를 들어, `10 -> 15 -> 20 -> 25` 연결 리스트가 있다고 하면, 새로운 노드(5)를 연결리스트 맨 앞에 넣게 된다면 `5 -> 10 -> 15 -> 20 -> 25` 가 된다. `push()`라는 함수로 연결리스트의 맨앞에 노드를 추가하도록 해보자. `push()` 는 인자로 head 포인터를 반드시 받아야 하는데, 이유는 새로운 노드를 head 가 가리킬 수 있도록 해야 하기 때문이다.

![Adds new node in front of linked list](https://www.geeksforgeeks.org/wp-content/uploads/gq/2013/03/Linkedlist_insert_at_start.png)

새로운 데이터 E 가 들어가면서, E 는 기존 head 가 가리켰던 포인터(A 노드)를 E 의 next 항목으로 넣어주고, 새로운 노드 E 가 head 가 될 것이므로 head 를 새로운 노드 E의 주소값을 가지게 한다.

```java

/* This function is in LinkedList class. Inserts a
   new Node at front of the list. This method is  
   defined inside LinkedList class shown above */
public void push(int new_data)
{
    /* 1 & 2: 새로운 노드 할당 및 데이터 설정/
    Node new_node = new Node(new_data);

    /* 3. 새로운 노드의 next 항목은 head 가 가리키던 요소의 주소 */
    new_node.next = head;

    /* 4. head 의 주소는 새로운 노드가 되어야 한다. */
    head = new_node;
}
```

이 push() 는 시간 복잡도 O(1)을 가진다.

### 주어진 노드 다음에 새로운 노드 추가 (5 단계 프로세스)
 `insertAfter()` 라는 method 를 구현할 것이며, 이 method는 인자로 노드와 새로운 노드의 데이터를 받는다. 새로 만들어진 노드는 주어진 노드 다음에 연결되고 기존 연결된 노드는 새로운 노드의 다음으로 연결이 될 것이다.

 ```java

/* This function is in LinkedList class.
   Inserts a new node after the given prev_node. This method is  
   defined inside LinkedList class shown above */
public void insertAfter(Node prev_node, int new_data)
{
    /* 1. 새로운 노드의 이전 노드는 null 이면 안된다. */
    if (prev_node == null)
    {
        System.out.println("The given previous node cannot be null");
        return;
    }
    /* 2. 새로운 노드 생성 &
       3. 데이터 설정 */
    Node new_node = new Node(new_data);
    /* 4. 새로운 노드의 next 는 이전 노드의 next 로 되어야 한다. */
    new_node.next = prev_node.next;
    /* 5. 이전 노드의 next 는 새로운 노드가 될 것이다. */
    prev_node.next = new_node;
}
 ```

`insertAfter()` 는 시간복잡도 O(1)을 가진다.

 ### 연결리스트 맨 뒤에 새로운 노드 추가
새로운 노드는 항상 주어진 연결리스트의 맨 마지막에 추가가 되어야 한다. 예를 들어 주어진 연결리스트가 `5 -> 10 -> 15 -> 20 -> 25` 라면, 새로운 노드(30)이 마지막에 추가되어 `5 -> 10 -> 15 -> 20 -> 25 -> 30`가 될 것이다. 연결리스트는 일반적으로 head 로 관리하기 때문에, 추가를 위해서는 연결리스트 마지막으로 가기위해 한번 순회를 해야 한다.

![Adds new node to the end of linked list](https://www.geeksforgeeks.org/wp-content/uploads/gq/2013/03/Linkedlist_insert_last.png)

```java

/* Appends a new node at the end.  This method is  
   defined inside LinkedList class shown above */
public void append(int new_data)
{
    /* 1. 새로운 노드 할당 &
       2. 데이터 설정
       3. next 는 null 로 설정되어야 함 */
    Node new_node = new Node(new_data);

    /* 4. 연결리스트가 비어있다면, 새로운 노드가 head 가 되어야 함. */
    if (head == null)
    {
        head = new Node(new_data);
        return;
    }

    /* 4. 새로운 노드는 무조건 마지막 노드가 된다
          그래서 next 항목은 항상 null 이 된다. */
    new_node.next = null;

    /* 5. 연결리스트 순회하여 추가전 마지막 노드 찾기 */
    Node last = head;  
    while (last.next != null)
        last = last.next;

    /* 6. 마지막 노드의 next 항목이 새로운 노드가 되도록 함. */
    last.next = new_node;
    return;
}
```

마지막에 새로운 노드를 추가하는 `append()`는 시간복잡도가 O(n) 이 된다. 이유는 한번 노드를 순회하여 연결리스트 모든 노드를 방문해야하는 비용이 들기 때문이다. 물론 tail 포인터를 생성하고 마지막 노드를 추적할 수 있도록 만든다면, O(1) 시간 복잡도로 새로운 노드를 추가할 수 있다.

### 연습코드

앞서 3가지 방법으로 새로운 노드를 수행할 수 있는 구현을 보자. 구현을 하고 나서 실행하면 각 새로운 노드들이 어떻게 생성되어 출력되는지 알 수 있다.

```java

// A complete working Java program to demonstrate all insertion methods
// on linked list
class LinkedList
{
    Node head;  // head of list

    /* Linked list Node*/
    class Node
    {
        int data;
        Node next;
        Node(int d) {data = d; next = null; }
    }

    /* Inserts a new Node at front of the list. */
    public void push(int new_data)
    {
        /* 1 & 2: Allocate the Node &
                  Put in the data*/
        Node new_node = new Node(new_data);

        /* 3. Make next of new Node as head */
        new_node.next = head;

        /* 4. Move the head to point to new Node */
        head = new_node;
    }

    /* Inserts a new node after the given prev_node. */
    public void insertAfter(Node prev_node, int new_data)
    {
        /* 1. Check if the given Node is null */
        if (prev_node == null)
        {
            System.out.println("The given previous node cannot be null");
            return;
        }

        /* 2 & 3: Allocate the Node &
                  Put in the data*/
        Node new_node = new Node(new_data);

        /* 4. Make next of new Node as next of prev_node */
        new_node.next = prev_node.next;

        /* 5. make next of prev_node as new_node */
        prev_node.next = new_node;
    }

    /* Appends a new node at the end.  This method is  
       defined inside LinkedList class shown above */
    public void append(int new_data)
    {
        /* 1. Allocate the Node &
           2. Put in the data
           3. Set next as null */
        Node new_node = new Node(new_data);

        /* 4. If the Linked List is empty, then make the
              new node as head */
        if (head == null)
        {
            head = new Node(new_data);
            return;
        }

        /* 4. This new node is going to be the last node, so
              make next of it as null */
        new_node.next = null;

        /* 5. Else traverse till the last node */
        Node last = head;  
        while (last.next != null)
            last = last.next;

        /* 6. Change the next of last node */
        last.next = new_node;
        return;
    }

    /* This function prints contents of linked list starting from
        the given node */
    public void printList()
    {
        Node tnode = head;
        while (tnode != null)
        {
            System.out.print(tnode.data+" ");
            tnode = tnode.next;
        }
    }

    /* Driver program to test above functions. Ideally this function
       should be in a separate user class.  It is kept here to keep
       code compact */
    public static void main(String[] args)
    {
        /* Start with the empty list */
        LinkedList llist = new LinkedList();

        // Insert 6.  So linked list becomes 6->NUllist
        llist.append(6);

        // Insert 7 at the beginning. So linked list becomes
        // 7->6->NUllist
        llist.push(7);

        // Insert 1 at the beginning. So linked list becomes
        // 1->7->6->NUllist
        llist.push(1);

        // Insert 4 at the end. So linked list becomes
        // 1->7->6->4->NUllist
        llist.append(4);

        // Insert 8, after 7. So linked list becomes
        // 1->7->8->6->4->NUllist
        llist.insertAfter(llist.head.next, 8);

        System.out.println("\nCreated Linked list is: ");
        llist.printList();
    }
}
```
