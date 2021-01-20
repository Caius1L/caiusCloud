##模版方法模式
模版方法是一种行为模式，它在超类中定义了一个算法的框架，允许子类在不修改结构的情况秀啊重写算法的特定步骤
，模版方法模式希望将算法分解为一系列步骤，然后将这些步骤改写为方法，最后在模版方法中一次调用这些方法，步
骤可以是抽象的，也可以有一些默认的实现，为了能够使用算法，客户端需要自行提供子类并实现所有的抽象步骤，如
有必要还需要重写一些步骤。
##模版方法模式的逻辑实现
首先需要将所有的步骤都申明为抽象类型，强制要求子类自行实现这些方法，在我们的例子中，子类中已有所有的必要
实现，因此我们只需要调整这些方法的签名，使之与超类的方法匹配：
1. 抽象步骤必须由各个子类去实现
2. 可选步骤已经有一些默认的实现，但仍可在需要的时候进行重写