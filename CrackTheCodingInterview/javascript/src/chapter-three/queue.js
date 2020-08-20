const notEmpty = (node) => node !== undefined && node !== null;

export class Queue {
  queue = null;

  enqueue(item) {
    if (notEmpty(queue)) {
      queue.appendToTail(queue);
    } else {
      queue = new Node(item);
    }
  }

  dequeue() {
    if (notEmpty(queue)) {
      const value = queue.data;
      queue = queue.next;
      return value;
    } else {
      return null;
    }
  }
}
