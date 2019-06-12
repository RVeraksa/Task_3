package by.javatr.bean;

public class Gem {
    private String  preciousness;
    private String name;
    private String colour;
    private int clarity;
    private int border;
    private int value;
    private String country;


    public String getPreciousness() {
        return preciousness;
    }

    public String getName() {
        return name;
    }

    public String getColour() {
        return colour;
    }

    public int getClarity() {
        return clarity;
    }

    public int getBorder() {
        return border;
    }

    public int getValue() {
        return value;
    }

    public String getCountry() {
        return country;
    }

    public void setPreciousness(String preciousness) {
        this.preciousness = preciousness;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public void setClarity(int clarity) {
        this.clarity = clarity;
    }

    public void setBorder(int border) {
        this.border = border;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public static class Builder{
        private Gem gem;

        public Builder(){
            this.gem = new Gem();
        }

        public Builder setPreciousness(String preciousness){
            gem.preciousness = preciousness;
            return this;
        }

        public Builder setName(String name){
            gem.name = name;
            return this;
        }

        public Builder setColour(String colour){
            gem.colour = colour;
            return this;
        }

        public Builder setClarity(int clarity){
            gem.clarity = clarity;
            return this;
        }

        public Builder setBorder(int border){
            gem.border = border;
            return this;
        }

        public Builder setValue(int value){
            gem.value = value;
            return this;
        }

        public Builder setCountry(String country){
            gem.country = country;
            return this;
        }

        public Gem build(){
            return gem;
        }
    }

    @Override
    public String toString() {
        return "Gem{" +
                "preciousness=" + preciousness +
                ", name='" + name + '\'' +
                ", colour='" + colour + '\'' +
                ", clarity=" + clarity +
                ", border=" + border +
                ", value=" + value +
                ", country='" + country + '\'' +
                '}';
    }
}
