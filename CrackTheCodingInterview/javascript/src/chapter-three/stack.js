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
    return this.top.data;
  }

  push(item) {
    this.length += 1;
    const newNode = new Node(item);
    newNode.next = this.top;
    this.top = newNode;
  }

  peek() {
    return !this.top ? this.top : this.top.data;
  }

  fromListToStack(list) {
    const newStack = new Stack();

    return list.reduce((stack, item) => {
      const newerStack = stack;
      stack.push(item);
      return newerStack;
    }, newStack);
  }

  take(size) {
    if (size <= this.length) {
      const stack = new Stack();

      for (let index = 0; index < size; index++) {
        stack.push(this.top.data);
        this.top = this.top.next;
        this.length -= 1;
      }
      return stack;
    } else {
      console.log("Size too large");
    }
  }

  displayStack() {
    if (this.top) return this.top.printNode();
    return "";
  }
}
