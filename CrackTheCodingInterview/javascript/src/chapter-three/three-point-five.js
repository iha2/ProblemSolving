import { Stack } from "./stack.js";

export class Queue {
  first = new Stack();
  second = new Stack();

  enqueue(value) {
    this.second.push(value);
  }

  dequeue() {
    if (!this.first.peek()) {
      const newSecond = this.second.take(parseInt(this.second.length / 2));
      const newFirst = this.second.take(this.second.length);
      this.second = newSecond;
      this.first = newFirst;
      return this.first.pop();
    } else {
      return this.first.pop();
    }
  }

  static fromList(list) {
    const newQueue = new Queue();

    list.forEach((item) => {
      newQueue.enqueue(item);
    });

    return newQueue;
  }

  displayQueue() {
    return `${this.first.displayStack()} second: ${this.second.displayStack()}`;
  }
}

