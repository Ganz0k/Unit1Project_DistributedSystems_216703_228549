/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package template;

/**
 * Class that represents a person. The parameters for this object are the
 * person's name, weight (kgs), height (mts), BMI and the result of said BMI.
 *
 * @author Jesús Ricardo Ortega Sánchez | 00000216703 Luis Gonzalo Cervantes
 * Rivera | 00000228549
 */
public class Person {

    private String name;
    private float weight;
    private float height;
    private Float bmi;
    private String result;

    /**
     * Constructor that initializes the fields name, weight and height.
     *
     * @param name name of the person
     * @param weight weight of the person in kgs
     * @param height height of the person in mts
     */
    public Person(String name, float weight, float height) {
        this.name = name;
        this.weight = weight;
        this.height = height;
    }

    /**
     * Returns the name of the person.
     *
     * @return name of the person
     */
    public String getName() {
        return name;
    }

    /**
     * Sets a new name for the person.
     *
     * @param name new name for the person
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the weight (kgs) of the person.
     *
     * @return weight (kgs) of the person.
     */
    public float getWeight() {
        return weight;
    }

    /**
     * Sets a new weight (kgs) for the person.
     *
     * @param weight new weight (kgs) for the person
     */
    public void setWeight(float weight) {
        this.weight = weight;
    }

    /**
     * Returns the height (mts) of the person.
     *
     * @return height (mts) of the person
     */
    public float getHeight() {
        return height;
    }

    /**
     * Sets a new height (mts) for the person.
     *
     * @param height new height (mts) for the person.
     */
    public void setHeight(float height) {
        this.height = height;
    }

    /**
     * Returns the BMI of the person.
     *
     * @return BMI of the person
     */
    public Float getBmi() {
        return bmi;
    }

    /**
     * Sets a new BMI for the person.
     *
     * @param bmi new BMI for the person.
     */
    public void setBmi(Float bmi) {
        this.bmi = bmi;
    }

    /**
     * Returns the health result of the person.
     *
     * @return health result of the person
     */
    public String getResult() {
        return result;
    }

    /**
     * Sets a new health result for the person.
     *
     * @param result new health result for the person
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * Returns a String that represents the object Person
     *
     * @return String that represents the object Person
     */
    @Override
    public String toString() {
        return "Person{" + "name=" + name + ", weight=" + weight + ", height=" + height + ", bmi=" + bmi + ", result=" + result + '}';
    }
}
