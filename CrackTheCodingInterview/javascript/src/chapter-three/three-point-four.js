import { Stack } from "./stack.js";

const firstTower = new Stack().fromListToStack([5, 4, 3, 2, 1]);
const secondTower = new Stack();
const thirdTower = new Stack();

function HanoiTower(size, fromTower, toTower, auxTower) {
  if (size == 1) {
    console.log(
      `Move disk 1 from ${fromTower.displayStack()} to ${toTower.displayStack()}`
    );
    toTower.push(fromTower.pop());
    return;
  }

  HanoiTower(size - 1, fromTower, auxTower, toTower);

  console.log(
    `Move disk ${size} from tower ${fromTower.displayStack()} to tower ${toTower.displayStack()}`
  );
  toTower.push(fromTower.pop());
  HanoiTower(size - 1, auxTower, toTower, fromTower);
}
HanoiTower(5, firstTower, thirdTower, secondTower);
