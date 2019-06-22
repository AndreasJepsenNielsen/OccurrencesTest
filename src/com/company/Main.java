package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args)
    {

        sortAndPrint();

    }



    private static void countEachWord(Map<String, Integer> words)
    {


        Scanner file = null;
        try
        {
            file = new Scanner(new File("tempest.txt"));
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }


        while(file.hasNext()){
            String pattern = "[\\[:.,;?!]+";
            String word = file.next();
            word = word.toLowerCase();

            word = word.replaceAll("and,--do", "and");
            word = word.replaceAll(",--", "");
            word = word.replaceAll(pattern, "");



            if(words.containsKey(word)) {
                int occurences = words.get(word) + 1;
                words.put(word, occurences);
            }else{
                words.put(word, 1);
            }
        }
        file.close();


    }

    private static void sortAndPrint()
    {
        Map<String, Integer> wordsMap = new TreeMap<>();

        countEachWord(wordsMap);

        Map<String, Integer> wordsFinalMap = sortByValue(wordsMap);

        System.out.println(wordsFinalMap.toString());

    }

    private static Map<String, Integer> sortByValue(final Map<String, Integer> wordCounts) {
        return wordCounts.entrySet()
                .stream()
                .sorted((Map.Entry.<String, Integer>comparingByValue().reversed())).limit(10)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

    }
}
