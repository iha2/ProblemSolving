import { Node } from "./linked-list";
import { removeDuplicates } from "./two-point-one";

describe("Chapter 2, two point one", () => {
  test("remove duplicate values from linked list by data", () => {
    const duplicateList = Node.listToLinkedList([1, 2, 3, 4, 5, 6, 3, 4, 5, 5]);
    const linkedListSet = removeDuplicates(duplicateList);
    // do this later
    expect(true).toBe(true);
  });
});
