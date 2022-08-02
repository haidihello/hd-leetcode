//package com.leetcode.editor.effectivejava.builder;
//
///**
// * @Author: HaiDi
// * @Date: 2022/3/24 12:29
// */
////Builder Pattern
//public class NutritionFacts {
//    private final int servingSize;
//    private final int servings;
//    private final int calories;
//    private final int fat;
//    private final int sodium;
//    private final int carbohydrate;
//
//
//    public static class Builder{
//        private final int servingSize;
//        private final int servings;
//        private int calories = 0;
//        private int fat = 0;
//        private int sodium = 0;
//        private int carbohydrate = 0;
//
//        public Builder(int servingSize, int servings) {
//            this.servingSize = servingSize;
//            this.servings = servings;
//        }
//
//        public Builder calories(int var) {
//            calories = var;
//            return this;
//        }
//
//        public NutritionFacts build(){
//            return new NutritionFacts(this);
//        }
//    }
//
//    private NutritionFacts(Builder builder){
//        servings = builder.servingSize;
//    }
//
//    public static void main(String[] args) {
//        NutritionFacts nutritionFacts = new NutritionFacts();
//        nutritionFacts.calories
//        this.calories();
//    }
//
//}
