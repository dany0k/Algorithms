package ru.vsu.cs.zmaev;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextVisual {

    public static int amountBehindFixedEls(boolean[] fixed) {
        int row = 0;
        for (int i = fixed.length - 1; i > 0; i--) {
            if (fixed[i]) {
                row++;
            } else {
                break;
            }
        }
        return row;
    }

    public static int amountForwardFixedEls(boolean[] fixed) {
        int row = 0;
        for (boolean b : fixed) {
            if (b) {
                row++;
            } else {
                break;
            }
        }
        return row;
    }

    public static int checkFixedRow(int index, boolean[] arr) {
        int fixedRow = 0;
        if (arr[index]) {
            for (int k = index; arr[k]; k++) {
                fixedRow++;
            }
        }
        return fixedRow;
    }

    public static  List<SortState> visualizationSort(int[] data, boolean[] fixed) {
        List<SortState> states = new ArrayList<>();
        int fixedJ = 0;
        int amountFixedForward = amountForwardFixedEls(fixed);
        int amountBehindFixed = amountBehindFixedEls(fixed);
        states.add(new SortState(SortState.Type.State, data, fixed,0, fixedJ, amountFixedForward, amountBehindFixed, 0, 0));
        for (int i = amountFixedForward; i < data.length - amountBehindFixed; i++) {
            int fixedRow = 0;
            if (fixed[i]) {
                fixedRow = checkFixedRow(i, fixed);
                states.add(new SortState(SortState.Type.Skip, data, i, fixedRow));
                i += fixedRow;
            }

            int value = data[i];

            int j = i - 1 - fixedRow;

            if (j > 0 && fixed[j]) {
                continue;
            }
            for (; j >= amountFixedForward; j--) {

                for (int k = 0; fixed[j]; k++) {
                    j--;
                    fixedJ++;
                }

                if (value < data[j]) { // Начало сравнения
                    states.add(new SortState(SortState.Type.Compare, data, fixed, fixedRow, fixedJ, amountFixedForward, amountBehindFixed, i, j));
                    if (!fixed[j + 1]) {
                        data[j + 1] = data[j];
                        states.add(new SortState(SortState.Type.Change, data, fixed, fixedRow, fixedJ, amountFixedForward, amountBehindFixed, j + 1, j));
                    } else if (fixed[j + 1] && fixedJ != 0) {
                        data[j + fixedJ + 1] = data[j];
                        states.add(new SortState(SortState.Type.Change, data, fixed, fixedRow, fixedJ, amountFixedForward, amountBehindFixed, j + 1 + fixedJ, j));
                    } else {
                        data[j + fixedRow + 1] = data[j];
                        states.add(new SortState(SortState.Type.Change, data, fixed, fixedRow, fixedJ, amountFixedForward, amountBehindFixed, j + 1 + fixedRow, j));
                    }
                } else {
                    break;
                }
            }

            if (!fixed[j + 1]) {
                data[j + 1] = value;
                states.add(new SortState(SortState.Type.ChangeBack, data, fixed, fixedRow, fixedJ, amountFixedForward, amountBehindFixed, j + 1, i));
            } else if (fixed[j + 1] && fixedJ > 0) {
                data[j + fixedJ + 1] = value;
                states.add(new SortState(SortState.Type.ChangeBack, data, fixed, fixedRow, fixedJ, amountFixedForward, amountBehindFixed, j + 1 + fixedJ, i));
            }
        }
        return states;
    }

    public static void appendState(StringBuilder sb, SortState ss) {
        int[] arr = ss.getArray();
        boolean[] fixed = ss.getFixed();
        sb.append("Массив находится в состоянии: ");
        if (arr.length > 0) {
            sb.append(Arrays.toString(arr)).append("\n").append("Массив fixed элементов: \t  ").append(Arrays.toString(fixed)).append('\n');
//        sb.append("\n");
            sb.append("Отступ слева: ")
                    .append(ss.getAmountFixedForward())
                    .append("\nОтступ спарва: ")
                    .append(ss.getGetAmountFixedBehind())
                    .append("\n");
        }
    }

    public static void appendCompare(StringBuilder sb, SortState ss) {
        int valA = ss.getArray()[ss.getA()];
        int valB = ss.getArray()[ss.getB()];
        sb.append("\nСравнение элементво под идексом ")
                .append(ss.getA()).append(" (равный ")
                .append(valA).append(") ");
        sb.append("с элементом под идексом ")
                .append(ss.getB()).append(" (равный ")
                .append(valB).append(")\n");
        sb.append("arr[").append(ss.getA()).append("]")
                .append((valA == valB) ? "=" : (valA < valB) ? "<" : ">").append("arr[").append(ss.getB()).append("] ");
        if (valA < valB) {
            sb.append("Запоминаем элемент под индексом: ").append(ss.getA()).append(" (равный ").append(valA).append(")\n");
        }
    }

    private static void appendChange(StringBuilder sb, SortState ss) {
        int[] arr = ss.getArray();
        int valA = ss.getArray()[ss.getA()];
        int valB = ss.getArray()[ss.getB()];
        sb.append("Элемент под индексом ")
                .append(ss.getA());
        sb.append(" заменяем на элемент под индексом  ")
                .append(ss.getB()).append('\n');
        sb.append("Массив имеет вид: ").append(Arrays.toString(arr)).append("\n");
    }

    private static void changeBack(StringBuilder sb, SortState ss) {
        int[] arr = ss.getArray();
        int valA = ss.getArray()[ss.getA()];
        int valB = ss.getArray()[ss.getB()];
        sb.append("Элемент под индексом ")
                .append(ss.getA());
        sb.append(" заменяем на элемент, который запомнили раннее.\n");
        sb.append("Массив имеет вид: ").append(Arrays.toString(arr)).append("\n");
    }

    private static void skip(StringBuilder sb, SortState ss) {
        if (ss.getFixedRow() > 1) {
            sb.append("Пропускаем ").append(ss.getFixedRow()).append(" элементов начиная с ").append(ss.getA()).append(" индекса.");
        } else {
            sb.append("Пропускаем ").append(ss.getA()).append(" элемент, т.к. он зафиксирован.");
        }
    }

    public static String createText(List<SortState> states) {
        StringBuilder sb = new StringBuilder();
        for (SortState ss : states) {
            switch (ss.getType()) {
                case State: {
                    appendState(sb, ss);
                } break;
                case Compare: {
                    appendCompare(sb, ss);
                } break;
                case Change: {
                    appendChange(sb, ss);
                } break;
                case ChangeBack: {
                    changeBack(sb, ss);
                } break;
                case Skip : {
                    skip(sb, ss);
                } break;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int[] arr = {0, 0, 3, 2, 0, 0, 2, 3};
        boolean[] fxArr = {true, true, false, false, true, true, false, false};
        List<SortState> states = visualizationSort(arr, fxArr);
        String str = createText(states);
        System.out.println(str);
    }
}