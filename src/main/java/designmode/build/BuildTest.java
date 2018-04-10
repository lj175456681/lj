package designmode.build;

/**
 * Title: BuildTest
 * Description:
 * author: liujie
 * date: 2017-02-28 下午12:26
 */
public class BuildTest {

    public static void main(String[] args) {
        NutritionFacts nu = new NutritionFacts.Builder(40,20,20)
                                                .salt(1)
                                                .minerals(2)
                                                .vitamin(2)
                                                .build();

        System.out.println(nu);
    }
}
