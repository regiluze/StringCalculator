package org.egi.mu.dojo

class Calculator {

    private static final String NEW_LINE = "\\n"
    private static final String DEFAULT_SPLITTER = ","
    private static final String DEFINE_SPLITTER_TOKEN = "//"
    private static final String INVALID_CHAR_ASC = '*'
    private static final String INVALID_CHAR_DOT = '.'
    private static final String SPLITTER_START_DEL = '['
    private static final String SPLITTER_END_DEL = ']'

    static def add(number){

        (number.isEmpty()) ? 0 : compute(number)

    }

    private static def compute(numbers) {
        String normalyzedInput = replaceWrongChars(numbers)
        String splitter = (normalyzedInput.startsWith(DEFINE_SPLITTER_TOKEN)) ?
                                                                        getSplitter(normalyzedInput) : DEFAULT_SPLITTER
        def normalizedNumbers =  extractNumbers(normalyzedInput).replace(NEW_LINE,splitter)
        calculate(normalizedNumbers,splitter)
    }



    private static String replaceWrongChars(numbers) {
        String normalyzedInput = numbers.replace(INVALID_CHAR_ASC, DEFAULT_SPLITTER)
                .replace(INVALID_CHAR_DOT, DEFAULT_SPLITTER)
        normalyzedInput
    }

    private static String getSplitter(numbers) {
        if (numbers.charAt(2) == SPLITTER_START_DEL && numbers.charAt(numbers.indexOf(NEW_LINE)-1) == SPLITTER_END_DEL){
            String globlaSpl = numbers.substring(3,numbers.indexOf(NEW_LINE)-1)
            (globlaSpl.contains("][")) ? convertToRegex(globlaSpl) : globlaSpl
        }else numbers.charAt(2)
    }

    static String convertToRegex(String chain) {
         chain.replace("][","|")
    }

    private static def checkNegativeNumbers(String normalizedNumbers, String splitter) {
        def negativeNumbers = normalizedNumbers.split(splitter).findAll {it.toInteger() < 0}
        if (negativeNumbers.size()) throw new Exception(negativeNumbers.toString())
    }

    private static def String extractNumbers(numbers) {
        (numbers.startsWith(DEFINE_SPLITTER_TOKEN)) ? numbers.substring(numbers.indexOf(NEW_LINE)+2,numbers.size()) : numbers
    }

    private static int calculate(normalizedNumbers,splitter) {
        checkNegativeNumbers(normalizedNumbers, splitter)
        (normalizedNumbers.size()) ? sumNumbers(normalizedNumbers, splitter) : normalizedNumbers.toInteger()
    }

    private static int sumNumbers(String normalizedNumbers, String splitter) {
        normalizedNumbers.split(splitter)
                .collect { it.toInteger() }.findAll { it <= 1000 }.sum()
    }

}
