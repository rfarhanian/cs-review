package com.mnaseri.cs.homework.ch15.sp;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @see <a href="https://ocw.mit.edu/courses/6-006-introduction-to-algorithms-fall-2011/resources/lecture-20-dynamic-programming-ii-text-justification-blackjack/">
 * The text justification problem</a>
 */
public class TextJustificationMemoizedCalculator {

    private static final String SPACE = " ";
    private final CostFunction costFunction;
    private final int lineWidth;

    public TextJustificationMemoizedCalculator(CostFunction costFunction, int lineWidth) {
        this.costFunction = costFunction;
        this.lineWidth = lineWidth;
    }

    public static void main(String[] args) {
        int theWidth = 50;
        TextJustificationMemoizedCalculator calculator = new TextJustificationMemoizedCalculator(new DefaultCost(theWidth), theWidth);
        String text = "It was the best of times, it was the worst of times, it was the age of wisdom, it was the age of foolishness, it was the epoch of belief, it was the epoch of incredulity, it was the season of Light, it was the season of Darkness, it was the spring of hope, it was the winter of despair, we had everything before us, we had nothing before us, we were all going direct to Heaven, we were all going direct the other way—in short, the period was so far like the present period, that some of its noisiest authorities insisted on its being received, for good or for evil, in the superlative degree of comparison only.There were a king with a large jaw and a queen with a plain face, on the throne of England; there were a king with a large jaw and a queen with a fair face, on the throne of France. In both countries it was clearer than crystal to the lords of the State preserves of loaves and fishes, that things in general were settled for ever.It was the year of Our Lord one thousand seven hundred and seventy-five. Spiritual revelations were conceded to England at that favoured period, as at this. Mrs. Southcott had recently attained her five-and-twentieth blessed birthday, of whom a prophetic private in the Life Guards had heralded the sublime appearance by announcing that arrangements were made for the swallowing up of London and Westminster. Even the Cock-lane ghost had been laid only a round dozen of years, after rapping out its messages, as the spirits of this very year last past (supernaturally deficient in originality) rapped out theirs. Mere messages in the earthly order of events had lately come to the En- glish Crown and People, from a congress of British subjects in America: which, strange to relate, have proved more important to the human race than any communications yet received through any of the chickens of the Cock-lane brood.France, less favoured on the whole as to matters spiritual than her sister of the shield and trident, rolled with exceeding smoothness down hill, making paper money and spending it. Under the guidance of her Christian pastors, she entertained herself, besides, with such humane achievements as sentencing a youth to have his hands cut off, his tongue torn out with pincers, and his body burned alive, because he had not kneeled down in the rain to do honour to a dirty procession of monks";
        TextJustificationResult result = calculator.calculate(text);
        System.out.println("result = " + result);
        String justifiedText = TextJustificationResult.resolveJustifiedText(text, result);
        System.out.println("result = \n" + justifiedText);
    }

    /**
     * Following is the solution for the best text line wrap assuming that there are no words larger than the line width.
     *
     * @param text
     * @return
     */
    public TextJustificationResult calculate(String text) {
        if (text == null) {
            return null;
        }
        return calculate(0, text.split(SPACE), 1, new HashMap<>());
    }

    private TextJustificationResult calculate(int start, String[] text, int lineNumber, Map<Integer, TextJustificationResult> cache) {
        if (cache.containsKey(start)) {
            return cache.get(start);
        }
        //        System.out.println("start = " + start);
        if (isLastLine(start, text)) {
            return TextJustificationResult.emptyResult();
        }
        System.out.println("lineNumber = " + lineNumber);
//        System.out.println("text = " + text.length);
        int endIndex = findIndexOfLastWordInTheLine(start, text);
//        System.out.println("endIndex = " + endIndex);
        int startIndex = endIndex > 3 ? endIndex - 3 : endIndex - 1;
//        System.out.println("startIndex = " + startIndex);
        TextJustificationResult totalCost = new TextJustificationResult(BigInteger.valueOf(Integer.MAX_VALUE), null);
        for (int j = startIndex; j < endIndex; j++) {
//            System.out.println("j = " + j);
            TextJustificationResult remaining = calculate(j + 1, text, lineNumber + 1, cache);
            BigInteger localCost = BigInteger.valueOf(costFunction.compute(text, start, j)).add(remaining.getCost());
            if (localCost.compareTo(totalCost.getCost()) < 0) {
                System.out.println("A better totalCost: " + totalCost + " vs. localCost:" + localCost);
                ArrayList<Integer> indices = new ArrayList<>();
                indices.add(j);
                indices.addAll(remaining.getIndices());
                totalCost = new TextJustificationResult(localCost, indices);
            }
        }
        System.out.println("possible totalCost = " + totalCost);
        cache.put(start, totalCost);
        return totalCost;
    }

    private boolean isLastLine(int start, String[] words) {
        int total = 0;
        for (int i = start; i < words.length; i++) {
            int current = words[i].length();
            total += current;
        }
        return total < lineWidth;
    }

    private int findIndexOfLastWordInTheLine(int start, String[] words) {
        int length = 0, count = 0;
        for (int i = start; i < words.length; i++) {
            int current = words[i].length();
            if (length + current < lineWidth) {
                length += current;
                count++;
            }
        }
        int result = count + start;
        System.out.println("findLineNumberOfWords(start:" + start + ") = " + result);
        return result;
    }
}
