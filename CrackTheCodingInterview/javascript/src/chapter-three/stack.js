import { Node } from "../chapter-two/linked-list.js";

const notEmpty = (node) => node !== undefined && node !== null;

export class Stack {
  top = undefined;
  length = 0;

  pop() {
    this.length -= 1;
    if (notEmpty(this.top)) {
      const value = this.top.data;
      this.top = this.top.next;
      return value;
    }
    return null;
  }

  push(item) {
    this.length += 1;
    const newNode = new Node(item);
    newNode.next = this.top;
    this.top = newNode;
  }

  peek() {
    return this.top.data;
  }
}
