package com.lancheng.caiusCloud.GA;/** * 种群 */public class Population {    int popSize = 10;    Individual[] individuals = new Individual[10];    int fittest = 0;    //初始化种群    public void initializePopulation(int size) {        for (int i = 0; i < individuals.length; i++) {            individuals[i] = new Individual();        }    }    //获取适应度最高的个体    public Individual getFittest() {        int maxFit = Integer.MIN_VALUE;        int maxFitIndex = 0;        for (int i = 0; i < individuals.length; i++) {            if (maxFit <= individuals[i].fitness) {                maxFit = individuals[i].fitness;                maxFitIndex = i;            }        }        fittest = individuals[maxFitIndex].fitness;        return individuals[maxFitIndex];    }    //获取适应度第二高的个体    public Individual getSecondFittest() {        int maxFit1 = 0;        int maxFit2 = 0;        for (int i = 0; i < individuals.length; i++) {            if (individuals[i].fitness > individuals[maxFit1].fitness) {                maxFit2 = maxFit1;                maxFit1 = i;            } else if (individuals[i].fitness > individuals[maxFit2].fitness) {                maxFit2 = i;            }        }        return individuals[maxFit2];    }    //获取适应度最差的个体的索引    public int getLeastFittestIndex() {        int minFitVal = Integer.MAX_VALUE;        int minFitIndex = 0;        for (int i = 0; i < individuals.length; i++) {            if (minFitVal >= individuals[i].fitness) {                minFitVal = individuals[i].fitness;                minFitIndex = i;            }        }        return minFitIndex;    }    //计算每个个体的适应度    public void calculateFitness() {        for (int i = 0; i < individuals.length; i++) {            individuals[i].calcFitness();        }        getFittest();    }}