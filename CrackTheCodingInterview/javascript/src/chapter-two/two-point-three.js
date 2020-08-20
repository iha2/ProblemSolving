import { Node } from "./linked-list.js";

const notEmpty = (node) => node !== null && node !== undefined;

export const removeNode = (linkedList, node) => {
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

