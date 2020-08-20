import { Node } from "./linked-list.js";

const notEmpty = (node) => node !== null && node !== undefined;

export const isPalindrome = (possiblePalindrome) => {
  if (!(possiblePalindrome && notEmpty(possiblePalindrome.next))) {
    return true;
  }

  let leader = possiblePalindrome;
  let follower = possiblePalindrome;
  let reversedList = null;

  // get leader to the end
  while (notEmpty(leader)) {
    if (notEmpty(leader.next)) {
      if (notEmpty(leader.next.next)) {
        leader = leader.next.next;
      } else {
        const next = new Node(follower.data);
        next.next = reversedList;
        reversedList = next;
        follower = follower.next;
        break;
      }

      if (notEmpty(reversedList)) {
        const next = new Node(follower.data);
        next.next = reversedList;
        reversedList = next;
      } else {
        reversedList = new Node(follower.data);
      }

      follower = follower.next;
    } else {
      follower = follower.next;
      break;
    }
  }

  let isPalindrome = true;
  while (notEmpty(reversedList)) {
    if (reversedList.data === follower.data) {
      reversedList = reversedList.next;
      follower = follower.next;
      continue;
    } else {
      isPalindrome = false;
      break;
    }
  }

  return isPalindrome;
};

const evenPalindrome = Node.listToLinkedList(["a", "b", "b", "a"]);
const oddPalindrome = Node.listToLinkedList([
  "R",
  "O",
  "T",
  "A",
  "T",
  "O",
  "R",
]);
const nonPalindrome = Node.listToLinkedList(["q", "f", "g", "h", "r", "j"]);
console.log(isPalindrome(evenPalindrome));
console.log(isPalindrome(oddPalindrome));
console.log(isPalindrome(nonPalindrome));
