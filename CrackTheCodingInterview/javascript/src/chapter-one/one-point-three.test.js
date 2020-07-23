import { checkPermutation } from "./one-point-three";

describe("Chapter 1, one point three", () => {
  test("checks for that string to value validates a permutation", () => {
    expect(checkPermutation("normal", "larmon")).toBe(true);
    expect(checkPermutation("lester", "reestl")).toBe(true);
    expect(checkPermutation("leszter", "reestl")).toBe(false);
  });
});
