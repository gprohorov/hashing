package edu.chaos.hashing.service;
/*
@author   george
@project   hashing
@class  CelAutomatesService
@version  1.0.0
@since 31.01.2021 - 17.31
*/

import edu.chaos.hashing.interfaces.Rule;
import edu.chaos.hashing.util.RulesSet;
import org.springframework.stereotype.Service;

@Service
public class CelAutomatesService {

    public static boolean[] transformComposedVectorHorizontally(boolean[] booleans) {
        return makeHorizontalGeneration(booleans);
    }

    public static boolean[] transformBlockVertically(boolean[] block) {
        for (int row = 0; row < block.length; row++) {
            Rule rule = RulesSet.VERTICAL_RULES_ARRAY[row % RulesSet.VERTICAL_RULES_ARRAY.length];
            block = makeGeneration(block, rule);
        }
        return block;
    }

    public static boolean[] makeGeneration(boolean[] boolStart, Rule rule) {
        boolean[] boolResult = new boolean[boolStart.length];
        for (int i = 0; i < boolStart.length; i++) {
            processRule(boolStart, boolResult, i, rule);
        }
        return boolResult;
    }

    public static boolean[] makeHorizontalGeneration(boolean[] boolStart) {
        boolean[] boolResult = new boolean[boolStart.length];
        for (int i = 0; i < boolStart.length; i++) {
            Rule rule = RulesSet.HORIZONTAL_RULES_ARRAY[i % RulesSet.HORIZONTAL_RULES_ARRAY.length];

            processRule(boolStart, boolResult, i, rule);
        }
        return boolResult;
    }

    private static void processRule(boolean[] boolStart, boolean[] boolResult, int i, Rule rule) {
        if (i == 0) {
            boolResult[i] = rule.step(false, boolStart[i], boolStart[i + 1]);
        } else if (i == boolStart.length - 1) {
            boolResult[i] = rule.step(boolStart[i - 1], boolStart[i], false);
        } else {
            boolResult[i] = rule.step(boolStart[i - 1], boolStart[i], boolStart[i + 1]);
        }
    }


}
