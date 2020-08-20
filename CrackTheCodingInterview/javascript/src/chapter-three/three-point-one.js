import { Stack } from "./stack.js";

export class StackOfStacks {
  size;
  stacks = [];

  constructor(size) {
    this.size = size;
  }

  push(item) {
    if (this.stacks.length) {
      const head = this.stacks[0];
      if (head.length === this.size) {
        const newStack = new Stack();
        newStack.push(item);
        this.stacks.unshift(newStack);
      } else {
        head.push(item);
        this.stacks[0] = head;
      }
    } else {
      const newStack = new Stack();
      newStack.push(item);

      this.stacks.push(newStack);
    }
    return this;
  }

  pop() {
    if (this.stacks.length) {
      const head = this.stacks[0];
      const value = head.pop();
      if (!head.length) {
        this.stacks.splice(0, 1);
      }

      return value;
    } else {
      return null;
    }
  }

  printStack() {
    return this.stacks.reduce(
      (display, stack, index) =>
        `${display} {${index + 1}: ${stack.top.printNode()}}`,
      ""
    );
  }
}

