public class Person {
    private int age;
    public String gender;
    public String address;
    
    public Person() {}
    
    public Person(int age, String gender, String address)    {
        this.age=age;
        this.gender=gender;
        this.address=address;
    }
    public int getAge() {return this.age;}
    public void setAge(int age)   {this.age=age;}
    public String getGender() {return this.gender;}
    public void setGender(String gender) {this.gender=gender;}
    public String getAddress() {return this.address;}
    public void setAddress(String address) {this.address = address;}
    
    public static void main(String[] args) {
        Person p1 = new Person(22, "male", "Faridabad");
        Person p2 = new Person();
        
        p2.setAge(20);
        p2.setGender("female");
        p2.setAddress("Delhi");
        
        System.out.println(p1);
        System.out.println(p2);
    }
    
    public String toString() {
        System.out.println("***********************");
        System.out.println( "Age: "+this.age);
        System.out.println("Gender: "+this.gender);
        System.out.println("Address: "+this.address);
        System.out.println("***********************");
    }
}
