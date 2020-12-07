package com.lancheng.caiusCloud.GA;

import java.util.Random;

/**
 * 实现简单的遗传算法
 */
public class SimpleDemoGA {

    Population population = new Population();
    Individual fittest;
    Individual secondFittest;
    int generationCount = 0;

    public static void main(String[] args) {
        Random rn = new Random();
        SimpleDemoGA demo = new SimpleDemoGA();
        //初始化种群
        demo.population.initializePopulation(10);
        //计算每个个体的适应度
        demo.population.calculateFitness();
        System.out.println("Generation: " + demo.generationCount + " Fittest: " + demo.population.fittest);
        //种群获得适应度最高的个体
        while (demo.population.fittest < 5) {
            ++demo.generationCount;
            //选择运算
            demo.selection();
            //交叉运算
            demo.crossover();
            //以随机概率进行变异
            if (rn.nextInt() % 7 < 5) {
                demo.mutation();
            }
            //向种群添加适应度最高的后代
            demo.addFittestOffspring();
            //计算新的适应度值
            demo.population.calculateFitness();
            System.out.println("Generation: " + demo.generationCount + " Fittest: " + demo.population.fittest);
        }
        System.out.println("\nSolution found in generation " + demo.generationCount);
        System.out.println("Fitness: " + demo.population.getFittest().fitness);
        System.out.print("Genes: ");
        for (int i = 0; i < 5; i++) {
            System.out.print(demo.population.getFittest().genes[i]);
        }
    }


    //选择运算
    void selection() {
        //选择适应度最高的个体
        fittest = population.getFittest();
        //选择适应度第二高的个体
        secondFittest = population.getSecondFittest();
    }

    //交叉运算
    void crossover() {
        Random rn = new Random();

        //选择一个随机交叉点
        int crossOverPoint = rn.nextInt(population.individuals[0].geneLength);

        //交换父体中的值
        for (int i = 0; i < crossOverPoint; i++) {
            int temp = fittest.genes[i];
            fittest.genes[i] = secondFittest.genes[i];
            secondFittest.genes[i] = temp;

        }

    }


    //变异运算
    void mutation() {
        Random rn = new Random();
        //选择一个随机变异点
        int mutationPoint = rn.nextInt(population.individuals[0].geneLength);
        //在变异点改动值
        if (fittest.genes[mutationPoint] == 0) {
            fittest.genes[mutationPoint] = 1;
        } else {
            fittest.genes[mutationPoint] = 0;
        }
        mutationPoint = rn.nextInt(population.individuals[0].geneLength);
        if (secondFittest.genes[mutationPoint] == 0) {
            secondFittest.genes[mutationPoint] = 1;
        } else {
            secondFittest.genes[mutationPoint] = 0;
        }
    }

    //获取适应度最高的后代
    Individual getFittestOffspring() {
        if (fittest.fitness > secondFittest.fitness) {
            return fittest;
        }
        return secondFittest;
    }


    //将适应度最差的个体替换为适应度最高的后代
    void addFittestOffspring() {
        //更新后代的适应度值
        fittest.calcFitness();
        secondFittest.calcFitness();
        //获取适应度最差的个体的索引
        int leastFittestIndex = population.getLeastFittestIndex();
        //将适应度最差的个体替换为适应度最高的后代
        population.individuals[leastFittestIndex] = getFittestOffspring();
    }

}


