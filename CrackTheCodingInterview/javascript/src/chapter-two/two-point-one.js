import { Node } from "./linked-list.js";

const notEmpty = (node) => node !== null && node !== undefined;

export const removeDuplicates = (linkedList) => {
  const nodeHashTable = {};

  if (!notEmpty(linkedList.next)) return linkedList;

  let resultLinkedList = new Node(linkedList.data);
  let currentList = linkedList.next;

  while (notEmpty(currentList)) {
    if (nodeHashTable[currentList.data] === undefined) {
      nodeHashTable[currentList.data] = currentList.data;
      resultLinkedList.appendToTail(currentList.data);
    }
    currentList = currentList.next;
  }

  return resultLinkedList;
};

