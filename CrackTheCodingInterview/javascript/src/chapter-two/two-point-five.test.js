import { Node } from "./linked-list";
import { addNumberLists } from "./two-point-five";

describe("Chapter 2, two point five", () => {
  test("add two numbered linked lists", () => {
    const firstList = Node.listToLinkedList([7, 6, 5, 8]);
    const secondList = Node.listToLinkedList([4, 5, 3, 1, 2]);
    expect(addNumberLists(firstList, secondList)).toBe(29921);
  });
});
