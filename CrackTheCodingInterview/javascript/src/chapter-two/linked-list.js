export const createNode = (data, next) => ({
  data,
  next,
});

export const appendToTail = (node, data) => {
  const newNode = createNode(data);
  let n = node;
  while (n.next != undefined && n.next != null) {
    n = n.next;
  }
  n.next = newNode;
  return node;
};

export class NodeC {
  next = null;

  constructor(data) {
    this.data = data;
  }

  appendToTail(data) {
    const end = new NodeC(data);
    let n = this;

    while (n.next != null && n.next != undefined) {
      n = n.next;
    }
    n.next = end;
  }
}

