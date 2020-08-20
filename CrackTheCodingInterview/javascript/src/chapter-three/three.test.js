import { StackOfStacks } from "./three-point-one";

describe("Chapter 3", () => {
  test("Add stack when nested stack if full", () => {
    const stackSet = new StackOfStacks(2);
    stackSet.push(1).push(2).push(3);
    expect(stackSet.printStack()).toEqual(" {1:  3} {2:  2 1}");
  });
});
