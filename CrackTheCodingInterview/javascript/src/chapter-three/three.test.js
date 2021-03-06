import { StackOfStacks } from "./three-point-one";
import { Queue } from "./three-point-five.js";

describe("Chapter 3", () => {
  test("Add stack when nested stack if full", () => {
    const stackSet = new StackOfStacks(2);
    stackSet.push(1).push(2).push(3);
    expect(stackSet.printStack()).toEqual(" {1:  3} {2:  2 1}");
  });
  test("Remove from stack", () => {
    const stackSet = new StackOfStacks(2);
    stackSet.push(1).push(2).push(3);
    stackSet.pop();
    stackSet.pop();
    expect(stackSet.printStack()).toEqual(" {1:  1}");
  });

  test("Assess a queue using 2 stacks", () => {
    const queue = Queue.fromList([1, 2, 3, 4, 5]);
    console.log(queue.displayQueue());
  });
});
