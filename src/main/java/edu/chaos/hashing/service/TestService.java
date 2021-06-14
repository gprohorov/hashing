package edu.chaos.hashing.service;
/*
  @author   george
  @project   hashing
  @class  TestService
  @version  1.0.0
  @since 31.01.2021 - 17.31
*/
import edu.chaos.hashing.model.HashString;
import edu.chaos.hashing.model.RandomTest;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TestService {

    // Characters are arranged betwen FROM and TO borders  (usually from 48 to 122)
    private String getRandomString(int from, int to, int length){
        Random random = new Random();
        return  random.ints(from, to + 1)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    //  Hardcode:  from 48 to 122 character,  length = 100
    private Set<String> getSetOfRandomStringsDefaultValues(int amount){
        Set<String> set = new HashSet<>();
        for (int i = 0; i < 100 ; i++) {
            String string = getRandomString(48,122, 100);
            set.add(string);
        }
        return set;
    }

    private Set<String> transformToHashes(Set<String> set){
        Set<String> performanceSet = new HashSet<>();
        for (String string:set) {
            String hash = HashingService.getHashString(string);
            performanceSet.add(hash);
        }
        return  performanceSet;
    }

    public RandomTest getTestResult(int amount){
        RandomTest test = new RandomTest();
        Set<String> generatedSet = this.getSetOfRandomStringsDefaultValues(amount);
        int generatedSetSize = generatedSet.size();
        Set<String> transformedSet = this.transformToHashes(generatedSet);
        long transformedSetSize = transformedSet.size();
        test.setDistinctResultCount(transformedSetSize);
        return test;
    }

}
