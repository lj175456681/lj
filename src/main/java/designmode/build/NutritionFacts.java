package designmode.build;

/**
 * Title: NutritionFacts
 * Description:
 * author: liujie
 * date: 2017-02-28 上午11:24
 */
public class NutritionFacts {

    private int fat;
    private int salt;
    private int carbohydrate;
    private int protein;
    private int minerals;
    private int vitamin;

    private NutritionFacts(Builder builder){
        this.fat = builder.fat;
        this.salt = builder.salt;
        this.carbohydrate = builder.carbohydrate;
        this.protein = builder.protein;
        this.minerals = builder.minerals;
        this.vitamin = builder.vitamin;
    }


    public static class Builder{
        private int fat;
        private int salt = 0;
        private int carbohydrate;
        private int protein;
        private int minerals = 0;
        private int vitamin = 0;

        public Builder(int carbohydrate,int fat ,int protein){
            this.fat = fat;
            this.carbohydrate = carbohydrate;
            this.protein = protein;
        }

        public Builder salt(int salt){
            this.salt = salt;
            return this;
        }

        public Builder minerals(int minerals){
            this.minerals = minerals;
            return this;
        }

        public Builder vitamin(int vitamin){
            this.vitamin = vitamin;
            return this;
        }

        public NutritionFacts build(){
            return new NutritionFacts(this);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("fat=");
        sb.append(fat);
        sb.append(",salt=");
        sb.append(salt);
        sb.append(",carbohydrate=");
        sb.append(carbohydrate);
        sb.append(",protein=");
        sb.append(protein);
        sb.append(",minerals=");
        sb.append(minerals);
        sb.append(",vitamin=");
        sb.append(vitamin);
        return sb.toString();
    }
}
