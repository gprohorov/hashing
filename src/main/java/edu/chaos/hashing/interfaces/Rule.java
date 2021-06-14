package edu.chaos.hashing.interfaces;

/*
@author   george
@project   hashing
@class interface  Rule
@version  1.0.0
@since 31.01.2021 - 17.31
*/
@FunctionalInterface
public interface Rule {
	public boolean step(boolean left, boolean middle, boolean right);
}
