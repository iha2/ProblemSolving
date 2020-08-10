import { convertMatrix } from "./one-point-seven";

const MbyMMatrix = [
  [1, 2, 2],
  [4, 0, 6],
  [7, 8, 9],
];
const MbyMMatrixTop = [
  [0, 2, 2],
  [4, 6, 6],
  [7, 8, 9],
];
describe("Chapter 1, one point seven", () => {
  test("check that a matrix with a center zero is converted", () => {
    const convertedMatrix = convertMatrix(MbyMMatrix);
    expect(convertedMatrix[0][1]).toBe(0);
    expect(convertedMatrix[1][1]).toBe(0);
    expect(convertedMatrix[2][1]).toBe(0);
    expect(convertedMatrix[1][0]).toBe(0);
    expect(convertedMatrix[1][2]).toBe(0);
  });
  test("check that a matrix with on the top right", () => {
    const convertedMatrix = convertMatrix(MbyMMatrixTop);
    expect(convertedMatrix[0][0]).toBe(0);
    expect(convertedMatrix[0][1]).toBe(0);
    expect(convertedMatrix[0][2]).toBe(0);
    expect(convertedMatrix[1][0]).toBe(0);
    expect(convertedMatrix[2][0]).toBe(0);
  });
});
