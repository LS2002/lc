package com.lc;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class Solution {

    /**
     * 1. Two Sum
     * https://leetcode.com/problems/two-sum/
     * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
     */
	public int[] twoSum(int[] nums, int target) {
    	int[] result = new int[2];
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                if((nums[i]+nums[j])==target){
                    result[0] = i;
                    result[1] = j;
                    break;
                }
            }
        }
        return result;
    }
    
    /**
     * 412. Fizz Buzz
     * https://leetcode.com/problems/fizz-buzz/
     * Write a program that outputs the string representation of numbers from 1 to n.
     * But for multiples of three it should output “Fizz” instead of the number and for 
     * the multiples of five output “Buzz”. 
     * For numbers which are multiples of both three and five output “FizzBuzz”.     
     */
    public List<String> fizzBuzz(int n) {
    	List<String> result = new ArrayList<String>();
    	for(int i=1;i<=n;i++){
    		if(i%(3*5)==0)
    			result.add("FizzBuzz");
    		else if(i%3==0)
    			result.add("Fizz");
    		else if(i%5==0)
    			result.add("Buzz");
    		else
    			result.add(String.valueOf(i));
    	}
    	return result;
    }

    /**
     * 344. Reverse String
     * https://leetcode.com/problems/reverse-string/
     * Write a function that takes a string as input and returns the string reversed.
     */
    public String reverseString(String s) {
    	StringBuffer sb = new StringBuffer();
    	for(int i=s.length();i>0;i--){
    		sb.append(s.substring(i-1,i));
    	}
    	return sb.toString();
    }

    /**
     * Tesla 2016-11-17
     * Given a string, find out the most often characters
     */  
    public String mostOftenCharacter(String testString){
    	HashMap<String,Integer> results = new HashMap<String,Integer>();// character, counter of each character
    	String currentCharacter = new String();
    	for(int i=0;i<testString.length()-1;i++){
    		currentCharacter = testString.substring(i,i+1);
    		if(results.containsKey(currentCharacter)){
    			results.put(currentCharacter, results.get(currentCharacter)+1);
    		}else{
    			results.put(currentCharacter, 1);
    		}
    	}
    	
    	int maxCounter=0;
    	for(String currentKey:results.keySet()){
    		if(results.get(currentKey)>maxCounter){
    			maxCounter=results.get(currentKey);
    			currentCharacter=currentKey;
    		}
    	}
    	
    	return currentCharacter;
    }
    
    /**
     * 292. Nim Game
     * https://leetcode.com/problems/nim-game/
     * You are playing the following Nim Game with your friend: 
     * There is a heap of stones on the table, each time one of you take turns to 
     * remove 1 to 3 stones. The one who removes the last stone will be the winner. 
     * You will take the first turn to remove the stones.
     * Both of you are very clever and have optimal strategies for the game. 
     * Write a function to determine whether you can win the game given the number of stones in the heap.
     * 
     * For example, if there are 4 stones in the heap, then you will never 
     * win the game: no matter 1, 2, or 3 stones you remove, the last stone 
     * will always be removed by your friend.
     */
    public boolean canWinNim(int n) {
    	if((n>4&&n%4!=0) || n<=3) return true;
    	else return false;
    	//4 - false
    	//5 - true
    	//6 - true
    	//7 - true
    	//8 - false
    }
    
    /**
     * 136. Single Number
     * https://leetcode.com/problems/single-number/
     * Given an array of integers, every element appears twice except for one. Find that single one.
     */
    public int singleNumber(int[] nums) {
    	if(nums.length<=2) return nums[0];
    	
    	HashMap<Integer,Integer> results = new HashMap<Integer,Integer>();//the integer, counter of the integer
    	for(int i=0;i<nums.length;i++){
    		if(results.containsKey(nums[i])){
    			results.put(nums[i], results.get(nums[i])+1);
    		}else{
    			results.put(nums[i],1);
    		}
    	}
    	
    	for(int key:results.keySet()){
    		if(results.get(key)==1)
    			return key;
    	}
    	return 0;
    }  
    
//    public int singleNumber2(int[] nums){
//    	if(nums.length<=2) return nums[0];
//    	
//    }
    
    /**
     * 371. Sum of Two Integers
     * https://leetcode.com/problems/sum-of-two-integers/
     * Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.
     */
    public int getSum(int a, int b) {
    	if(b==0) return a;
    	int sum,carry;
    	sum=a^b;
    	carry=(a&b)<<1;
    	return getSum(sum,carry);
    }
    
    public int getSum2(int a, int b) {
    	if(b==0) return a;
    	int sum,carry;
    	sum=a^b;
    	carry=(a&b)<<1;
    	while(carry!=0){
    		a=sum;
    		b=carry;
    		sum=a^b;
    		carry=(a&b)<<1;
    	}
    	return sum;
    }

    public int getMinus(int a, int b){
    	//a-b=a+(~b+1)
    	return getSum2(a,getSum2(~b,1));
    }
    
    public void swap(int a,int b){
    	System.out.println(String.format("%d %d -before swap", a,b));
    	a^=b;
    	b^=a;
    	a^=b;
    	System.out.println(String.format("%d %d -after swap", a,b));
    }
    
    public int abs(int a){
//    	return a>=0?a:(~a+1);
    	int sign=a>>31;
    	return sign==0?a:(~a+1);
    }
    
    /**
     * 2016-11-15 Daqri Interview
     * Web page crawler 
     */
    HashMap<String,String> results = new HashMap<String,String>();
    public void Daqri_Web_Crawler(String url){
    	String[] urlList = parsedUrls(url);
    	for(int i=0;i<urlList.length;i++){
    		if(!results.containsKey(urlList[i]) && urlList[i].contains("www.daqri.com")){
    			Daqri_Web_Crawler(urlList[i]);
    		}
    	}
    }
    public void Daqri_Web_Crawler2(String url){
    	String[] currentUrlList = parsedUrls(url);
    	HashMap<String,Integer> results = new HashMap<String,Integer>();//url, visited flag
    	results.put(url, 1);//parent url is visited
    	while(results.containsValue(0)){
    		for(int i=0;i<currentUrlList.length;i++){
    			if(!results.containsKey(currentUrlList[i]) && currentUrlList[i].contains("www.daqri.com")){
    				results.put(currentUrlList[i], 0);//not visited
    			}else{
    				results.put(currentUrlList[i], 1);//visited
    			}
    		}
			
    		for(String tempUrl:results.keySet()){
    			if(results.get(tempUrl)==0){//as long as an url not visited, re-assign currentUrlList with the parsed url results
    				currentUrlList=parsedUrls(tempUrl);
    				break;
    			}
    		}
    	}
    	
    }
    public String[] parsedUrls(String url){
    	//TO-DO: parse all urls within the page
    	return new String[100];
    }
    /**
     * 455. Assign Cookies
     * https://leetcode.com/problems/assign-cookies/
     * Assume you are an awesome parent and want to give your children some cookies. 
     * But, you should give each child at most one cookie. 
     * Each child i has a greed factor gi, which is the minimum size of a cookie that the 
     * child will be content with; and each cookie j has a size sj. If sj >= gi, 
     * we can assign the cookie j to the child i, and the child i will be content. Y
     * our goal is to maximize the number of your content children and output the maximum number.
     * 
     * Note:
     * You may assume the greed factor is always positive. 
     * You cannot assign more than one cookie to one child.
     * 
	*/   
    public int findContentChildren(int[] greedyfactor, int[] cookiesize) {
    	
    	List<Integer> childrens = new ArrayList<Integer>();
    	List<Integer> satisfied = new ArrayList<Integer>();
    	
    	Arrays.sort(greedyfactor);
    	for(int i=0;i<greedyfactor.length;i++){
    		childrens.add(greedyfactor[i]);//not satisfied
    	}
    	Arrays.sort(cookiesize);
    	for(int i=0;i<cookiesize.length;i++){
    		satisfied.add(cookiesize[i]);//unused cookie
    	}

    	int totalSatisified=0;
    	Iterator<Integer> itC = childrens.iterator();
    	Iterator<Integer> itS = satisfied.iterator();
    	int cKey,sKey;
    	while(itC.hasNext()){
    		cKey = itC.next();
    		while(itS.hasNext()){
    			sKey=itS.next();
    			if(sKey>=cKey){
    				itC.remove();//remove satisfied children from list
    				itS.remove();//remove used cookie from list
    				totalSatisified++;
    				break;//once a satisfied happen, stop the loop and continue on next satisfiable cookie
    			}
    		}
    	}
    	
    	return totalSatisified;
    }
    

    /**
     * 104. Maximum Depth of Binary Tree
     * https://leetcode.com/problems/maximum-depth-of-binary-tree/
     * Given a binary tree, find its maximum depth.
     * The maximum depth is the number of nodes along the longest 
     * path from the root node down to the farthest leaf node.
     * 
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */ 
    public static class TreeNode{
    	int val;
    	TreeNode left;
    	TreeNode right;
    	TreeNode(int x) {val=x;}
    }
    
    public int maxDepth(TreeNode root) {
    	if(root==null) return 0;
    	int maxLeft = maxDepth(root.left);
    	int maxRight = maxDepth(root.right);
    	return Math.max(maxLeft, maxRight) +1;
    }
    
    /**
     * 389. Find the Difference
     * https://leetcode.com/problems/find-the-difference/
     * Given two strings s and t which consist of only lower case letters.
     * String t is generated by random shuffling string s and then add one more letter at a random position.
     * Find the letter that was added in t.
     */
    public char findTheDifference(String s, String t) {
    	int sumCharS=0,sumCharT=0;
        for(char cS:s.toCharArray()){
        	sumCharS+=cS;
        }
        for(char cT:t.toCharArray()){
        	sumCharT+=cT;
        }
        
        return (char)(sumCharT-sumCharS);
    }

    /**
     * 258. Add Digits
     * https://leetcode.com/problems/add-digits/
     * Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.
     * 
     * For example:
     * Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.
     */
    public int addDigits(int num) {
    	int result=0;
    	for(char s:String.valueOf(num).toCharArray()){
    		result+=Integer.valueOf(String.valueOf(s));
    	}
    	
    	if(String.valueOf(result).length()>1){
    		return addDigits(result);
    	}else{
    		return result;
    	}
    }
    

    /**
     * 226. Invert Binary Tree
     * https://leetcode.com/problems/invert-binary-tree/
     *         4
     *   	 /   \
     *   	2     7
	 *	   / \   / \
	 *	  1   3 6   9
	 *
	 * to
	 *	       4
     *		 /   \
     *		7     2
     *	   / \   / \
     *    9   6 3   1
	 * 
     */
    public TreeNode invertTree(TreeNode root) {
    	if(root==null) {
    		return root;
    	}else{
	    	TreeNode temp=root.left;
	    	root.left=root.right;
	    	root.right=temp;
    	}
    	invertTree(root.left);
    	invertTree(root.right);
    	return root;
    }
    
    /**
     * 283. Move Zeroes
     * https://leetcode.com/problems/move-zeroes/
     * 
     * Given an array nums, write a function to move all 0's to the end of it while
     *  maintaining the relative order of the non-zero elements.
     *  
     * For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].
     * You must do this in-place without making a copy of the array.
	 * Minimize the total number of operations.
     */
    public void moveZeroes(int[] nums) {
    	int temp=0;
    	for(int i=0;i<nums.length;i++){
    		if(nums[i]==0){
    			for(int k=i;k<nums.length;k++){
    				if (nums[k]!=0){//switch array index position when encounter 0
    					temp=nums[k];
    					nums[k]=0;
    					nums[i]=temp;
    					break;
    				}
    			}
    		}
    	}
    }
    
    /**
     * 2014-09-20 Google HR
     * Print pascal triangle
     *            
     *            1
     *          1   1
     *        1   2   1
     *      1   3   3   1
     *    1   4   6   4   1     
     */
    public void printPascalTriangel(int row){
    	HashMap<Integer,ArrayList<Integer>> results = new HashMap<Integer,ArrayList<Integer>>();//row index, row array content
    	ArrayList<Integer> previousRow = new ArrayList<Integer>();
    	for(int i=1;i<=row;i++){
        	ArrayList<Integer> currentRow = new ArrayList<Integer>();    	
    		for(int j=1;j<=i;j++){
    			if(j==1||j==i){
    				currentRow.add(1);
    			}else{
    				currentRow.add(previousRow.get(j-2)+previousRow.get(j-1));
    			}
    		}
    		previousRow=new ArrayList<Integer>(currentRow);
    		results.put(i, currentRow);
    	}
    	
    	int spaceCounter = 0;
    	int spacer = 5;//each digits occupy width of 3 character5
    	for(int key:results.keySet()){
    		ArrayList<Integer> currentRow = results.get(key);
    		spaceCounter= (row - currentRow.size())*spacer/2;
    		for(int i=0;i<spaceCounter;i++) System.out.print(" "); //print left spaces
    		for(int value:currentRow){
    			String assembledString = "";
    			int spaceCounterEachCharacter = (spacer-String.valueOf(value).length())/2;
    			for(int i=0;i<spaceCounterEachCharacter;i++) assembledString+=" ";
    			assembledString+=value;
    			for(int i=0;i<spaceCounterEachCharacter;i++) assembledString+=" ";
    			System.out.print(assembledString);
    		}
    		for(int i=0;i<spaceCounter;i++) System.out.print(" "); //print right spaces
    		System.out.println();
    	}
    }
    

    /**
     * 453. Minimum Moves to Equal Array Elements
     * https://leetcode.com/problems/minimum-moves-to-equal-array-elements/
     * Given a non-empty integer array of size n, find the minimum number of moves 
     * required to make all array elements equal, where a move is incrementing n - 1 elements by 1.
     */
    public int minMoves(int[] nums) {
    	int minValue=Integer.MAX_VALUE, move=0;
    	
    	for(int value:nums) 
    		minValue=Math.min(minValue, value);
    	
    	for(int value:nums)
    		move+=value-minValue;
    	
    	return move;
    	/*
    	int[] maxValue={0,0};//maxValue, maxValue's position
    	double sum=0;

    	for(int i=0;i<nums.length;i++){
    		sum+=nums[i];
    		if(maxValue[0]<nums[i]){
    			maxValue[0]=nums[i];
    			maxValue[1]=i;
    		}
//    		if(sum>=Integer.MAX_VALUE || sum<=Integer.MIN_VALUE) return 0;
    	}

    	
    	int move=0;
    	while(Double.compare(maxValue[0]*nums.length,sum)!=0){
    		move++;
    		        	
    		for(int i=0;i<nums.length;i++){
    			if(i!=maxValue[1]){
    				nums[i] = nums[i]+1;
    			}
    		}
    		
        	for(int i=0;i<nums.length;i++){
        		if(maxValue[0]<nums[i]){
        			maxValue[0]=nums[i];
        			maxValue[1]=i;
        		}
        	}

    		sum=0;
    		for(int num:nums){
            	sum+=num;
//            	if(sum>Integer.MAX_VALUE || sum<Integer.MIN_VALUE) break;
    		}
    		
    		
    	} 
        return move;
        */
    }

    /**
     * Tesla @ 2016-12-02
     * Find the longest common string in 2 strings
     */
    
    public String longestCommonString(String s1, String s2){
    	//TC1: null s1 or s2
    	if(s1==null || s2==null) return "not found";
    	
    	//TC2: s1, s2 contains only 1 identical/different character
    	if(s1.length()<=1 && s2.length()<=1) {
    		if (s1.equals(s2)) 
    			return s1;
    		else 
    			return "not found";
    	}
    	
    	//TC3: s1 is substring of s2, or s2 is substring of s1
    	if(s1.contains(s2)) return s2;
    	if(s2.contains(s1)) return s1;
    	
    	//TC4: s1 contains only spaces, s2 contains only alphanumeric characters
    	
    	//TC5: normal scenarios
    	int maxLength=0;
    	String maxString = "";
    	for(int i=0;i<s1.length();i++){
    		for(int j=i;j<s1.length();j++){
    			String temp = s1.substring(i,j+1);
    			if(s2.contains(temp) && temp.length()>maxLength){
    				maxLength=temp.length();
    				maxString=temp;
    			}
    		}
    	}
    	
    	return maxString;
    }
    
    /**
     * 463. Island Perimeter
     * https://leetcode.com/problems/island-perimeter/
     * 
     * You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water. 
     * Grid cells are connected horizontally/vertically (not diagonally). 
     * The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells). 
     * The island doesn't have "lakes" (water inside that isn't connected to the water around the island). 
     * One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. 
     * Determine the perimeter of the island.
     * 
     */
    
    public int islandPerimeter(int[][] grid) {
    	int perimeter=0;
    	for(int i=0;i<grid.length;i++){
    		for(int j=0;j<grid[i].length;j++){
    			if(grid[i][j]==1){
    				//top boarder
    				if(i==0) perimeter+=1;
    				//bottom boarder
    				if(i==grid.length-1) perimeter+=1;
    				//left border
    				if(j==0) perimeter+=1;
    				//right border
    				if(j==grid[i].length-1) perimeter+=1;
    				//top cell is 0
    				if(i-1>=0&&grid[i-1][j]==0) perimeter+=1;
    				//top cell is 0
    				if(i+1<grid.length&&grid[i+1][j]==0) perimeter+=1;
    				//left cell is 0
    				if(j-1>=0&&grid[i][j-1]==0) perimeter+=1;
    				//right is 0 cell
    				if(j+1<grid[i].length&&grid[i][j+1]==0) perimeter+=1;
    			}
    		}
    	}
    	return perimeter;
    }

    
    /**
     * Tesla Phone Interview: find largest 2 numbers in an array
     */
    
    public int[] findLargestTwoNumbers(int[] source){
    	
    	return null;
    }
    
    /**
     * Daqri Onsite: find smallest common numbers in 2 arrays
     * E.g. Array1 [15,22,9,7,13], Array2 [4,9,1]....so the smallest common number is 9
     */
    
    public int findSmallestCommonNumber(int[] a1, int[] a2){
    	return 0;
    }
    
    /**
     * Given 4 numbers, use each number once and + - x ÷ to produce target value
     */
    public int calcTwo(int a, int b, int computeType){
    	switch(computeType){
    	case 0: return a+b;
    	case 1: return a-b;
    	case 2: return a*b;
    	case 3: return a/b;
    	default: return a+b;
    	}
    }
    
    public String getCalcSymbol(int computeType){
    	switch(computeType){
    	case 0: return " + ";
    	case 1: return " - ";
    	case 2: return " x ";
    	case 3: return " ÷ ";
    	default: return " + ";
    	}
    }
    
    public boolean computeTargetValue(int a, int b, int c, int d, int target){
    	for(int i=0;i<4;i++){
        	for(int j=0;j<4;j++){
            	for(int k=0;k<4;k++){
            		if (calcTwo(calcTwo(calcTwo(a,b,i),c,j),d,k)==target){
                		System.out.println(a+getCalcSymbol(i)+b+getCalcSymbol(j)+c+getCalcSymbol(k)+d+" = "+target);
            			return true;
            		}
            	}
        	}
    	}
    	
    	return false;
    }
    
    
    /**
     * 2017-01-10 Markov @ Sonya Liang - AI for microwave algorithm
     * Game of life: Given a grid of value 1 or 0, calculate the next generation of the grid 
     * with the following rules:  if the cell value is 1 and if the neighbor cells’ value sum 
     * up to be 2 or 3, the cell value will become 1, otherwise, it will become 0. If the cell 
     * value is 0, if the neighbor cells’ value sum up to be 1, it will become 0, otherwise, 
     * it will become 1.
     * 
     * 1 0 1
     * 0 1 0
     * 1 0 0
     * 
     * Expected Result:
     * 0 1 0
     * 1 1 1
     * 0 1 0
     * 
     */
    
    public int[][] calcNeighbor(int[][] input){
        int row = input.length;
        int col = input[0].length;
        int[][] result = new int[row][col];
        
        for(int i=0;i<input.length;i++){
          for(int j=0;j<input[i].length;j++){
            int sumOfNeighbor=0;
            
            for(int m=-1;m<=1;m++){
              for(int n=-1;n<=1;n++){
                if(i+m>=0&&i+m<col&&j+n>=0&&j+n<row&&(m!=0&&n!=0)){
                  sumOfNeighbor+=input[i+m][j+n];
                }
              }
            }
            
            /*
            //top-left
            if(i-1>=0 && j-1>=0) sumOfNeighbor+=input[i-1][j-1];
            //top
            if(i-1>=0) sumOfNeighbor+=input[i-1][j];
            //top-right
            if(i-1>=0 && j+1<col) sumOfNeighbor+=input[i-1][j+1];
            //left
            if(j-1>=0) sumOfNeighbor+=input[i][j-1];
            //right
            if(j+1<col) sumOfNeighbor+=input[i][j+1];
            //bottom-left
            if(i+1<row && j-1>=0) sumOfNeighbor+=input[i+1][j-1];
            //bottom
            if(i+1<row) sumOfNeighbor+=input[i+1][j];
            //bottom-right
            if(i+1<row && j+1<col) sumOfNeighbor+=input[i+1][j+1];
            
            */
            
            if (input[i][j]==1){
              if(sumOfNeighbor==2 || sumOfNeighbor==3){
                result[i][j]=1;
              }else{
                result[i][j]=0;
              }
            }
            
            if (input[i][j]==0){
              if(sumOfNeighbor==1){
                result[i][j]=0;
              }else{
                result[i][j]=1;
              }
              
            }
          }
        }
        
        return result;
      }
    
    
    /**
     * 2017-01-10 Markov @ Sonya Liang - Microwave AI
     * 
     * Depth first in-order traversal of a binary tree
     * 
     *     1
     *   2   3   
     *  4 5 6 7      
     *       
     *  Expected output: 4 2 5 1 6 3 7     
     */  
    
    public void traversal(TreeNode tree){
        if(tree==null) {
          System.out.print("");
        }else{
          if(tree.left!=null) traversal(tree.left);
          System.out.print(tree.val);
          if(tree.right!=null) traversal(tree.right);
        }
      }
      
//     class TreeNode{
//        int value;
//        TreeNode left;
//        TreeNode right;
//        TreeNode (int val) {
//          value=val;
//        }
//      }
    
    
    /**
     * 2017-01-15 Blend Labs (Mortgage Lending Financing Company)
     * Read in a file and print out missing elements
     * 
     */
    public void findMissingDocsForEachDocType(File file) throws FileNotFoundException{
    	Scanner reader = new Scanner(file);
    	int currentLine = 0;
    	
    	HashMap<String,ArrayList<Integer>> result = new HashMap<String,ArrayList<Integer>>();
    	while(reader.hasNextLine()){
    		String currentLineContent = reader.nextLine();	
    		if(currentLine!=0){
    			String docType = currentLineContent.split(",")[2];
    			int applicationId = Integer.valueOf(currentLineContent.split(",")[3]);
    			ArrayList<Integer> currentList;
    			if(result.containsKey(docType)){
    				currentList = result.get(docType);
    			}else{
    				currentList = new ArrayList<Integer>();
    			}
				currentList.add(applicationId);
				result.put(docType, currentList);
    		}
    		currentLine++;
    	}
    	
    	Iterator it = result.entrySet().iterator();
    	String[] docTypes = new String[result.size()];
    	int k=0, largestApplicationId=0;
    	for(Map.Entry<String, ArrayList<Integer>> entry: result.entrySet()){
    		docTypes[k]=entry.getKey();
    		ArrayList<Integer> temp = entry.getValue();
    		for(int i=0;i<temp.size();i++){
    			if(temp.get(i)>largestApplicationId){
    				largestApplicationId=temp.get(i);
    			}
    		}
    		k++;
    	}
    	Arrays.sort(docTypes);
    	
    	for(int m=0;m<docTypes.length;m++){
    		for(int n=0;n<result.get(docTypes[m]).size();n++){
    			//TO-DO
    			//if find missing number then print docType and missing ids
    		}
    	} 	
    }
    
    /**
     * 2017-01-17 CollectiveHealth @ Director of Engineering
     * Anagram of 2 strings
     * Unit test a random number function
     */
    public boolean anagram(String a, String b){
    	if(a.length()!=b.length()) return false;
    	
    	HashMap<String,Integer> resultA = new HashMap<String,Integer>();
    	HashMap<String,Integer> resultB = new HashMap<String,Integer>();
    	
    	for(char c:a.toCharArray()){
    		if(resultA.containsKey(String.valueOf(c))){
    			resultA.put(String.valueOf(c), resultA.get(String.valueOf(c))+1);
    		}else{
    			resultA.put(String.valueOf(c), 1);
    		}
    	}
    	for(char c:a.toCharArray()){
    		if(resultB.containsKey(String.valueOf(c))){
    			resultB.put(String.valueOf(c), resultB.get(String.valueOf(c))+1);
    		}else{
    			resultB.put(String.valueOf(c), 1);
    		}
    	}
    	
    	if(resultA.size()==resultB.size()){
    		for(String key:resultA.keySet()){
    			if(resultA.get(key)!=resultB.get(key)){
    				return false;
    			}
    		}
    	}else{
    		return false;
    	}
    	
    	return true;
    }
    
    
    public void six_sided_dice(){
    	
    	//1: Normal value test - shall within range btw 1 and 6
    	//2: Range test - test 10, 1000, 65535 times and ensure all results within the range
    	//3: Distribution test - test 10000 times to make sure all numbers are evenly distributed
    	//4: Load test - Integer.MAX_VALUE
    	//5: Documentation test - ensure all necessary comments are added 
    	
    }
    
    
    /**
     * 2017-01-18 Cisco @ Palo Alto
     * 
     */
    public int power1(int x, int y){
    	if(x==0) return 0;
    	if(y==0) return 1;
    	int result = 1;
    	for(int k=0;k<y;k++){
    		result *= x;
    	}
    	return result;
    }
    
    public int power(int x, int y){
    	if(x==0) return 0;
    	if(y==0) return 1;
    	
    	if (y==1) 
    		return x;
    	else
    		return x*power(x,y-1);
    }
    
    
}
