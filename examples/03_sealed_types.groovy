import groovy.transform.ToString

sealed interface Tree<T> { }

@Singleton
final class Empty implements Tree {
    String toString() { "Empty" }
}

@ToString
final class Node<T> implements Tree<T> {
    final T value
    final Tree<T> left, right

    Node(T value, Tree<T> left, Tree<T> right) {
        this.value = value
        this.left = left
        this.right = right
    }
}

def a = new Node(0, Empty.instance, Empty.instance)
def b = new Node(1, a, Empty.instance)
def c = new Node(2, Empty.instance, Empty.instance)
def d = new Node(3, c, b)

assert d.toString() == 'Node(3, Node(2, Empty, Empty), Node(1, Node(0, Empty, Empty), Empty))'
