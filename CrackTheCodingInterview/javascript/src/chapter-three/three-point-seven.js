import { Queue } from "./queue.js";

export const createAnimal = (type, name) => ({
  type,
  name,
});

export class AnimalShelter {
  dogs = new Queue();
  cats = new Queue();
  index = 0;

  getOldest(type = undefined) {
    if (!type) {
      const dg = { ...this.dogs.dequeue() };
      const ct = { ...this.cats.dequeue() };
      if (dg.index > ct.index) {
        console.log("dog leaves");
        this.dogs.enqueue(dg);
        this.index--;
        return ct;
      } else {
        console.log("cat leaves");
        this.cats.enqueue(ct);
        this.index--;
        return dg;
      }
    } else {
      switch (type) {
        case "CAT":
          return this.cats.dequeue();
        case "DOG":
          return this.dogs.dequeue();
      }
    }
  }

  push(animal) {
    this.index++;
    switch (animal.type) {
      case "CAT":
        this.cats.enqueue({ ...animal, index: this.index });
        break;
      case "DOG":
        this.dogs.enqueue({ ...animal, index: this.index });
        break;
    }
  }

  displayShelter() {
    return `cats: ${this.cats.displayQueue()} dogs: ${this.dogs.displayQueue()}`;
  }
}

