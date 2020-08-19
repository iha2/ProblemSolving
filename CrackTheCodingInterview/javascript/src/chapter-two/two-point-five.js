import { Node } from "./linked-list.js";

const notEmpty = (node) => node !== null && node !== undefined;

const calculateList = (linkedList) => {
  let nestedLinkedList = linkedList;
  let placeValue = 0;
  let value = 0;

  while (notEmpty(nestedLinkedList)) {
    value += nestedLinkedList.data * Math.pow(10, placeValue);
    placeValue += 1;
    nestedLinkedList = nestedLinkedList.next;
  }

  return value;
};

const firstList = Node.listToLinkedList([7, 6, 5, 8]);
const secondList = Node.listToLinkedList([4, 5, 3, 1, 2]);
export const addNumberLists = (firstList, secondList) =>
  calculateList(firstList) + calculateList(secondList);

const newValue = addNumberLists(firstList, secondList);

console.log(newValue);
