import { Node } from "./linked-list";
import { removeNode } from "./two-point-three";

describe("Chapter 3, two point three", () => {
  test("remove node from linked list", () => {
    let linkedList = Node.listToLinkedList(["a", "b", "c", "d", "e"]);
    const cleanedNode = removeNode(linkedList, new Node("c"));

    expect(
      cleanedNode.isEqual(Node.listToLinkedList(["a", "b", "d", "e"]))
    ).toBe(true);
  });

  test("remove node from single linked list", () => {
    let singleItemList = new Node("a");
    const cleanedSingleNode = removeNode(singleItemList, new Node("a"));
    expect(cleanedSingleNode.isEqual(Node.listToLinkedList(["a"]))).toBe(false);
  });
});
