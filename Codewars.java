import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.*;

public class Codewars {

    public static void main(String[] args) throws Exception {
        
    }

    public static boolean isValid(char[] walk) {
        if (walk.length != 10) {
            return false;
        }
        int currXposition = 0;
        int currYposition = 0;
        for (int i = 0; i < 10; i++) {
            char currDirection = walk[i];
            if (currDirection == 'n') {
                currYposition++;
            } else if (currDirection == 's') {
                currYposition--;
            } else if (currDirection == 'e') {
                currXposition++;
            } else if (currDirection == 'w') {
                currXposition--;
            }
        }
        if (currXposition == 0 && currYposition == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static String solution(String str) {
        StringBuilder stringBuilder = new StringBuilder(str);
        return stringBuilder.reverse().toString();
    }

    public static String getMiddle(String word) {
        if (word.length() % 2 == 0) {
            return word.substring(word.length() / 2 - 1, word.length() / 2 + 1);
        } else {
            return word.charAt(word.length() / 2) + "";
        }
    }

    public static int[] minMax(int[] arr) {
        int[] minMax = {Integer.MAX_VALUE, Integer.MIN_VALUE};
        for (int i = 0; i < arr.length; i++) {
            int curr = arr[i];
            minMax[0] = Math.min(minMax[0], curr);
            minMax[1] = Math.max(minMax[1], curr);
        }
        return minMax;
    }

    public static String maskify(String str) {
        //throw new UnsupportedOperationException("Unimplemented");
        if (str.length() < 4) {
            return str;
        }
        String hashesToAdd = "";
        for (int i = 0; i < str.length() - 4; i++) {
            hashesToAdd += "#";
        }
        return hashesToAdd + str.substring(str.length() - 4, str.length());
    }

    public static int calculateYears(double principal, double interest, double tax, double desired) {

        if (principal >= desired) {
            return 0;
        }
        double currSum = principal * (1 + interest);
        int currYear = 1;
        while (currSum < desired) {
            double toAdd = currSum * interest;
            toAdd -= toAdd * tax;
            currSum += toAdd;
            currYear++;
        }
        return currYear;
    }

    private static Fighter currAttacker = null;
    private static Fighter currBeingAttacked = null;

    public static String declareWinner(Fighter fighter1, Fighter fighter2, String firstAttacker) {
        if (fighter1.name.equals(firstAttacker)) {
            currAttacker = fighter1;
            currBeingAttacked = fighter2;
        } else {
            currAttacker = fighter2;
            currBeingAttacked = fighter1;
        }
        while (fighter1.health > 0 && fighter2.health > 0) {
            currBeingAttacked.health -= currAttacker.damagePerAttack;
            swapFighters();
        }
        return currBeingAttacked.name;
    }

    private static void swapFighters() {
        Fighter temp = currAttacker;
        currAttacker = currBeingAttacked;
        currBeingAttacked = temp;
    }

    public static long findNb(long m) {
        double n = (-1 + Math.sqrt(1 + 8 * Math.sqrt(m))) / 2;
        double temp2 = Math.floor(n);
        if (n != temp2) {
            return -1;
        }
        long nLong = (long) n;
        long check = ((nLong * nLong + nLong) / 2) * ((nLong * nLong + nLong) / 2);
        if (check == m) {
            return nLong;
        } else {
            return -1;
        }
    }

    public static int solveSuperMarketQueueSlowSolution(int[] customers, int n) {
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < customers.length; i++) {
            queue.add(customers[i]);
        }
        int[] tills = new int[n];
        int currTime = 0;
        boolean isEmptyTills = false;
        while (!queue.isEmpty() || !isEmptyTills) {
            isEmptyTills = true;
            for (int i = 0; i < n; i++) {
                if (tills[i] == 0 && !queue.isEmpty()) {
                    tills[i] = queue.poll();
                    isEmptyTills = false;
                }
                if (tills[i] != 0) {
                    isEmptyTills = false;
                }
                tills[i]--;
            }
            if (tills[0] != -1) {
                currTime++;
            }
        }
        return currTime;
    }

    public static int solveSuperMarketQueue(int[] customers, int n) {
        if (customers.length == 0) {
            return 0;
        }
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < customers.length; i++) {
            queue.add(customers[i]);
        }
        int[] tills = new int[n];
        for (int i = 0; i < Math.min(n, customers.length); i++) {
            tills[i] = queue.poll();
        }
        int currTime = 0;
        boolean isEmptyTills = false;
        while (!queue.isEmpty() || !isEmptyTills) {
            isEmptyTills = true;
            int toSubtract = minExceptZeros(tills);
            for (int i = 0; i < n; i++) {
                if (tills[i] == toSubtract) {
                    if (!queue.isEmpty()) {
                        tills[i] = queue.poll();
                        isEmptyTills = false;
                    } else {
                        tills[i] = 0;
                    }
                } else if (tills[i] > toSubtract) {
                    tills[i] -= toSubtract;
                    isEmptyTills = false;
                }
            }
            currTime += toSubtract;

        }
        return currTime;
    }

    private static int minExceptZeros(int[] A) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < A.length; i++) {
            if (A[i] != 0) {
                min = Math.min(min, A[i]);
            }
        }
        return min;
    }

    public static boolean validate(String n) {
        int remainder = 1;
        if (n.length() % 2 == 0) {
            remainder = 0;
        }
        int sum = 0;
        for (int i = n.length() - 1; i >= 0; i--) {
            int currDigit = n.charAt(i) - '0';
            if (i % 2 == remainder) {
                currDigit *= 2;
            }
            if (currDigit > 9) {
                currDigit -= 9;
            }
            sum += currDigit;
        }
        if (sum % 10 == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static double[] cartesianNeighborsDistance(int x, int y, int range) {
        Set<Double> distances = new HashSet<>();
        for (int i = 0; i <= range; i++) {
            for (int j = 0; j <= range; j++) {
                //point = (i,j)
                if (i == 0 && j == 0) {
                    continue;
                }
                distances.add(distance(x + i, y + j, x, y));
            }
        }
        double[] out = new double[distances.size()];
        Iterator<Double> iterator = distances.iterator();
        int currArrayIndex = 0;
        while (iterator.hasNext()) {
            out[currArrayIndex] = iterator.next();
            currArrayIndex++;
        }

        return out;
    }

    private static double distance(int x1, int y1, int x2, int y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    public static int findIt(int[] A) {
        HashMap<Integer, Integer> occurrences = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            Integer currOcurrences = occurrences.get(A[i]);
            if (currOcurrences == null) {
                occurrences.put(A[i], 1);
            } else {
                occurrences.put(A[i], currOcurrences + 1);
            }
        }
        for (Map.Entry<Integer, Integer> occurrencesOfNumber : occurrences.entrySet()) {
            if (occurrencesOfNumber.getValue() % 2 == 1) {
                return occurrencesOfNumber.getKey();
            }
        }
        return 0;
    }

    public static String expandedForm(int num) {
        int currPower = 0;
        String expandedForm = "";
        while (num != 0) {
            int currDigit = num % 10;
            num /= 10;
            if (currDigit == 0) {
                currPower++;
                continue;
            }
            expandedForm = " + " + (int) (currDigit * Math.pow(10, currPower)) + expandedForm;
            currPower++;
        }
        return expandedForm.substring(3, expandedForm.length());
    }

    public static List<Integer> sqInRect(int lng, int wdth) {
        if (lng == wdth) {
            return null;
        }
        ArrayList<Integer> squares = new ArrayList<Integer>();
        while (lng != 0 && wdth != 0) {
            if (wdth < lng) {
                int temp = wdth;
                wdth = lng;
                lng = temp;
            }
            int countOfFittingSquares = wdth / lng;
            wdth -= countOfFittingSquares * lng;
            for (int i = 0; i < countOfFittingSquares; i++) {
                squares.add(lng);
            }
        }
        return squares;
    }

    public static Map<String, Integer> interpret(String[] program) {
        Map<String, Integer> register = new HashMap<>();
        for (int i = 0; i < program.length; i++) {
            String[] currLine = program[i].split(" ");
            String currCommand = currLine[0];
            String currFirstArgument = currLine[1];
            String currSecondArgument = "";
            if (currLine.length > 2) {
                currSecondArgument = currLine[2];
            }
            Integer valueInRegister = register.get(currFirstArgument);
            //charAt(4)-'0' returns first argument charAt(5)-'0' returns second argument
            if (currCommand.equals("mov")) {
                int valueToMov = 0;
                try {
                    valueToMov = Integer.parseInt(currSecondArgument);
                    register.put(currFirstArgument, valueToMov);
                } catch (Exception e) {
                    valueToMov = register.get(currSecondArgument);
                    register.put(currFirstArgument, valueToMov);
                }
            } else if (currCommand.equals("inc")) {
                valueInRegister++;
                register.put(currFirstArgument, valueInRegister);
            } else if (currCommand.equals("dec")) {
                valueInRegister--;
                register.put(currFirstArgument, valueInRegister);
            } else if (currCommand.equals("jnz")) {
                int secondArgumentParsed = Integer.parseInt(currSecondArgument);
                try {
                    if (valueInRegister != 0) {
                        i += Integer.parseInt(currSecondArgument);
                        i--;
                    }
                } catch (NullPointerException e) {
                    if (Integer.parseInt(currFirstArgument) == 0) {
                        continue;
                    } else {
                        i += Integer.parseInt(currSecondArgument);
                        i--;
                    }
                }
            }
        }
        return register;
    }

    public static int isInteresting(int number, int[] awesomePhrases) {
        for (int i = 0; i <= 2; i++) {
            if (number + i <= 99) {
                continue;
            }
            String numberString = (number + i) + "";
            boolean isFollowedByZeroes = true;
            boolean isDecrementing = true;
            boolean isIncrementing = true;
            boolean isSameDigits = true;
            boolean isPalindrome = isPalindrome(numberString);
            boolean isAwesomePhrase = isAwesomePhrase(number + i, awesomePhrases);
            for (int j = 0; j < numberString.length(); j++) {
                if (numberString.charAt(j) != '0' && j != 0) {
                    isFollowedByZeroes = false;
                }
                if (j != numberString.length() - 1 && numberString.charAt(j) - numberString.charAt(j + 1) != 1) {
                    isDecrementing = false;
                }
                if (j != numberString.length() - 1 && numberString.charAt(j + 1) - numberString.charAt(j) != 1) {
                    if (numberString.charAt(j + 1) == '0' && numberString.charAt(j) == '9') {
                        continue;
                    }
                    isIncrementing = false;
                }
                if (j != numberString.length() - 1 && numberString.charAt(j + 1) != numberString.charAt(j)) {
                    isSameDigits = false;
                }
            }
            if ((isFollowedByZeroes || isDecrementing || isIncrementing || isSameDigits || isPalindrome || isAwesomePhrase)) {
                if (i == 0) {
                    return 2;
                } else {
                    return 1;
                }
            }
        }
        return 0;
    }

    private static boolean isAwesomePhrase(int number, int[] awesomePhrases) {
        for (int i = 0; i < awesomePhrases.length; i++) {
            if (number == awesomePhrases[i]) {
                return true;
            }
        }
        return false;
    }

    private static boolean isPalindrome(String numberString) {
        StringBuilder stringBuilder = new StringBuilder(numberString);
        if (stringBuilder.reverse().toString().equals(numberString)) {
            return true;
        } else {
            return false;
        }
    }

    public static String solution(int n) {
        if (n > 3000) {
            return "";
        }
        String out = "";
        int[] digits = new int[4];
        int currDigit = 0;
        while (n != 0) {
            digits[currDigit] = n % 10;
            n /= 10;
            currDigit++;
        }


        String[] symbols = {"I", "V", "X", "L", "C", "D", "M"};
        for (int i = 0; i < 4; i++) {
            currDigit = digits[i];
            if (currDigit <= 3) {
                for (int j = 0; j < currDigit; j++) {
                    out = symbols[i * 2] + out;
                }
            }
            if (currDigit == 4) {
                out = symbols[i * 2] + symbols[i * 2 + 1] + out;
            }
            if (currDigit >= 5 && currDigit <= 8) {
                String toAdd = symbols[i * 2 + 1];
                for (int j = 0; j < currDigit - 5; j++) {
                    toAdd += symbols[i * 2];
                }
                out = toAdd + out;
            }
            if (currDigit == 9) {
                out = symbols[i * 2] + symbols[i * 2 + 2] + out;
            }
        }
        return out;
    }

    public static boolean scramble(String str1, String str2) {
        int[] lettersOccurredCount1 = new int[26];
        int[] lettersOccurredCount2 = new int[26];
        for (int i = 0; i < str1.length(); i++) {
            lettersOccurredCount1[str1.charAt(i) - 'a']++;
        }
        for (int i = 0; i < str2.length(); i++) {
            lettersOccurredCount2[str2.charAt(i) - 'a']++;
        }

        for (int i = 0; i < str2.length(); i++) {
            if (lettersOccurredCount1[str2.charAt(i) - 'a'] < lettersOccurredCount2[str2.charAt(i) - 'a']) {
                return false;
            }
        }
        return true;
    }

    public static int dblLinear(int n) {
        //TODO Not completed https://www.codewars.com/kata/5672682212c8ecf83e000050/train/java
        return -1;
    }

    public static String lcs(String x, String y) {
        //TODO This solution is wrong (uncompleted)
        int[] letterOccurrencesCountX = new int[256];
        int[] letterOccurrencesCountY = new int[256];

        for (int i = 0; i < x.length(); i++) {
            letterOccurrencesCountX[x.charAt(i)]++;
        }
        for (int i = 0; i < y.length(); i++) {
            letterOccurrencesCountY[y.charAt(i)]++;
        }
        String out = "";
        for (int i = 0; i < 256; i++) {
            int lettersToAddCount = Math.min(letterOccurrencesCountX[i], letterOccurrencesCountY[i]);
            for (int j = 0; j < lettersToAddCount; j++) {
                out += new Character((char) i).toString();
            }
        }
        return out;
    }

    public static boolean isValid(String braces) {
        Stack<Character> bracesStack = new Stack<>();
        for (int i = 0; i < braces.length(); i++) {
            Character currBrace = braces.charAt(i);
            if (currBrace == '(' || currBrace == '[' || currBrace == '{') {
                bracesStack.add(currBrace);
                continue;
            }
            if (bracesStack.isEmpty()) {
                return false;
            }
            Character topOfStack = bracesStack.pop();
            if (currBrace == ')' && topOfStack != '(') {
                return false;
            }
            if (currBrace == ']' && topOfStack != '[') {
                return false;
            }
            if (currBrace == '}' && topOfStack != '{') {
                return false;
            }
        }
        if (!bracesStack.isEmpty()) {
            return false;
        }
        return true;
    }

    public static String stripComments(String text, String[] commentSymbols) {
        //TODO doesnt work dont know why Strip Comments
        String out = "";
        String[] lines = text.split("\n");
        linesLoop:
        for (int i = 0; i < lines.length; i++) {
            String currLine = lines[i];
            boolean commentFound = false;
            for (int j = 0; j < currLine.length(); j++) {
                if (isCommentSymbol(currLine.charAt(j) + "", commentSymbols)) {
                    String toAdd = trimEndOfLine(currLine.substring(0, j));
                    if (toAdd.isEmpty()) {
                        continue linesLoop;
                    }
                    out += toAdd;
                    commentFound = true;
                }
            }
            if (!commentFound) {
                out += currLine;
            }
            if (i != lines.length - 1) {
                out += "\n";
            }
        }
        return out;
    }

    private static boolean isCommentSymbol(String symbol, String[] commentSymbols) {
        for (int i = 0; i < commentSymbols.length; i++) {
            if (symbol.equals(commentSymbols[i])) {
                return true;
            }
        }
        return false;
    }

    private static String trimEndOfLine(String line) {
        for (int i = line.length() - 1; i >= 0; i--) {
            if (line.charAt(i) != ' ') {
                return line.substring(0, i + 1);
            }
        }
        return "";
    }

    public static boolean check(int[][] sudoku) {
        //Check horizontal grid lines
        for (int i = 0; i < 9; i++) {
            if (!checkBlockGridLine(sudoku[i])) {
                return false;
            }
            int[] currVerticalGridLine = new int[9];
            for (int j = 0; j < 9; j++) {
                currVerticalGridLine[j] = sudoku[j][i];
            }
            if (!checkBlockGridLine(currVerticalGridLine)) {
                return false;
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int[] currBlock = new int[9];
                for (int k = 0; k < 9; k++) {
                    int y = k / 3;
                    int x = k % 3;
                    currBlock[k] = sudoku[x + 3 * i][y + 3 * j];
                }
                if (!checkBlockGridLine(currBlock)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean checkBlockGridLine(int[] blockGridLine) {
        boolean[] numbersOccurred = new boolean[10];

        for (int i = 0; i < 9; i++) {
            if (blockGridLine[i] == 0) {
                return false;
            }
            numbersOccurred[blockGridLine[i]] = true;
        }
        return checkIfAllNumbersOccurred(numbersOccurred);
    }

    private static boolean checkIfAllNumbersOccurred(boolean[] numbersOccurred) {
        for (int i = 1; i <= 9; i++) {
            if (!numbersOccurred[i]) {
                return false;
            }
        }
        return true;
    }

    public static int[] snail(int[][] array) {
        if (array[0].length==0){
            return new int[0];
        }
        int n = array.length;
        ArrayList<Integer> snail = new ArrayList<>(n * n);
        int numberOfTrims = n / 2;
        for (int i = 0; i < numberOfTrims; i++) {
            int[] topRow = getRow(array, 0);
            addToArrayListOmitLastElement(snail, topRow);

            int[] rightColumn=getColumn(array,array.length-1);
            addToArrayListOmitLastElement(snail,rightColumn);

            int[] bottomRow=getRow(array,array.length-1);
            addToArrayListOmitLastElement(snail,reverse(bottomRow));

            int[]leftColumn=getColumn(array,0);
            addToArrayListOmitLastElement(snail,reverse(leftColumn));

            array=trimOuterEdges(array);
        }
        if (array.length == 1) {
            snail.add(array[0][0]);
            return snail.stream().mapToInt(h -> h).toArray();
        }
        return snail.stream().mapToInt(h -> h).toArray();
    }

    private static int[] getRow(int[][] array, int row) {
        return array[row];
    }

    private static int[] getColumn(int[][] array, int column) {
        int[] columnValues = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            columnValues[i] = array[i][column];
        }
        return columnValues;
    }

    private static int[][] trimOuterEdges(int[][] array) {
        int n = array.length;
        int[][] trimmed = new int[n - 2][n - 2];
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                trimmed[i - 1][j - 1] = array[i][j];
            }
        }
        return trimmed;
    }

    private static void addToArrayListOmitLastElement(ArrayList<Integer> arrayList, int[] toAdd) {
        for (int i = 0; i < toAdd.length - 1; i++) {
            arrayList.add(toAdd[i]);
        }
    }

    private static int[] reverse(int[] array) {
        int[] out = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            out[array.length - i - 1] = array[i];
        }
        return out;
    }
}
