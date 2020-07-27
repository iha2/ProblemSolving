const MbyMMatrix = [
  [1, 2, 2],
  [4, 0, 6],
  [7, 8, 9],
];

export const findZeroIndexes = (matrix) =>
  matrix.reduce((zeroIndexes, row, rIdx) => {
    const foundIndexes = row.reduce((zeroIndexesOfRow, columnValue, cIdx) => {
      return columnValue
        ? zeroIndexesOfRow
        : [...zeroIndexesOfRow, [rIdx, cIdx]];
    }, []);
    return foundIndexes.length
      ? [...zeroIndexes, ...foundIndexes]
      : zeroIndexes;
  }, []);

export const convertRowsAndColumns = (matrix, rIdx) => {
  const changedRow = matrix[rIdx[0]].map(() => {
    return 0;
  });
  const updatedMatrix = matrix
    .slice(0, rIdx[0])
    .concat([changedRow])
    .concat(matrix.slice(rIdx[0] + 1));

  const updatedWithColumn = updatedMatrix.map((row) => {
    const updatedRow = [...row];
    updatedRow[rIdx[1]] = 0;
    return updatedRow;
  });
  return updatedWithColumn;
};

export const convertMatrix = (matrix) => {
  return findZeroIndexes(matrix).reduce((currentMatrix, indexSet) => {
    const updatedMatrix = convertRowsAndColumns(currentMatrix, indexSet);
    return updatedMatrix;
  }, matrix);
};
