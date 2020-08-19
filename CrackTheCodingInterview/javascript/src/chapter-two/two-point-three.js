import { Node } from "./linked-list.js";

const notEmpty = (node) => node !== null && node !== undefined;

const removeNode = (linkedList, node) => {
  const trimmedNode = new Node(linkedList.data);
  let currentNode = undefined;

  if (notEmpty(linkedList.next)) {
    currentNode = linkedList.next;
  } else {
    if (trimmedNode.data === node.data) {
      linkedList = undefined;
      return new Node(null);
    }
  }

  while (notEmpty(currentNode)) {
    if (currentNode.data !== node.data) {
      trimmedNode.appendToTail(currentNode.data);
    }
    currentNode = currentNode.next;
  }
  linkedList = trimmedNode;
  return trimmedNode;
};

let singleItemList = new Node("a");
let linkedList = Node.listToLinkedList(["a", "b", "c", "d", "e"]);
const cleanedSingleNode = removeNode(singleItemList, new Node("a")).printNode();
const cleanedNode = removeNode(linkedList, new Node("c")).printNode();
console.log(cleanedNode, cleanedSingleNode);
