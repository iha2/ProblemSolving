import { Node } from "../chapter-two/linked-list.js";

const notEmpty = (node) => node !== undefined && node !== null;

export class Queue {
  queue = null;

  enqueue(item) {
    if (notEmpty(this.queue)) {
      this.queue.appendToTail(this.queue);
    } else {
      this.queue = new Node(item);
    }
  }

  dequeue() {
    if (notEmpty(this.queue)) {
      const value = this.queue.data;
      this.queue = this.queue.next;
      return value;
    } else {
      return null;
    }
  }

  displayQueue() {
    return `${this.queue ? this.queue.printNode() : ""}`;
  }
}
