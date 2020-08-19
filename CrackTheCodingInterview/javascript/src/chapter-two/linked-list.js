export class Node {
  next = null;

  constructor(data) {
    this.data = data;
  }

  appendToTail(data) {
    const end = new Node(data);
    let n = this;

    while (n.next != null && n.next != undefined) {
      n = n.next;
    }
    n.next = end;
  }

  printNode() {
    let display = "";
    let nextNode = this;
    while (nextNode !== null && nextNode !== undefined) {
      display = `${display} ${nextNode.data}`;
      nextNode = nextNode.next;
    }
    return display;
  }

  static listToLinkedList(list) {
    let linkedList = new Node(list[0]);
    list.slice(1).forEach((item) => {
      linkedList.appendToTail(item);
    });
    return linkedList;
  }
}

