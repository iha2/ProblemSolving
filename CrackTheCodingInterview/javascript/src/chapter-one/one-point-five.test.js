import { stringCompressor } from "./one-point-five";

describe("Chapter 1, one point five", () => {
  test("has compressed string", () => {
    expect(stringCompressor("qweqweeqweeeeertggggggfdsa")).toBe(
      "q3w3e8r1t1g6f1d1s1a1"
    );
  });
});

