const notEmpty = (node) => node !== null && node !== undefined;

export const calculateList = (linkedList) => {
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

export const addNumberLists = (firstList, secondList) =>
  calculateList(firstList) + calculateList(secondList);
