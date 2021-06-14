package edu.chaos.hashing.util;
/*
  @author   george
  @project   hashing
  @class  - RulesSet
  @version  1.0.0 
  @since 31.01.2021 - 14.56
*/

import edu.chaos.hashing.interfaces.Rule;
import org.apache.tomcat.util.digester.Rules;

public class RulesSet {

    public static Rule[] VERTICAL_RULES_ARRAY = new Rule[]{
            RulesSet::rule90, RulesSet::rule150,
    };
    public static Rule[] HORIZONTAL_RULES_ARRAY = new Rule[]{
            RulesSet::rule30, RulesSet::rule105,
    };

    public static boolean rule30(boolean p, boolean q, boolean r) {
        return p ^ (q || r);
    }

    public static boolean rule90(boolean p, boolean q, boolean r) {
        return p ^ r;
    }

    public static boolean rule105(boolean p, boolean q, boolean r) {
        return p ^ q ^ (!r);
    }

    public static boolean rule150(boolean p, boolean q, boolean r) {
        return p ^ q ^ r;
    }


}
